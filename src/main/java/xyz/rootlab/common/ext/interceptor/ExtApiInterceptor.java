package xyz.rootlab.common.ext.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import xyz.rootlab.common.ext.filter.ExtApiFilterResponseWrapper;
import xyz.rootlab.common.ext.log.entity.ExtApiLog;
import xyz.rootlab.common.ext.log.service.ExtApiLogService;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;
import xyz.rootlab.common.utils.CommonFnc;
import xyz.rootlab.common.utils.JsonUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
public class ExtApiInterceptor extends WebContentInterceptor {

    @Resource(name = "apiLogService")
    private ExtApiLogService extApiLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        // TODO 권한 / 인증 처리
        if ("인증/권한이 실패했을 때".equals("")) {
            try {
                // 응답 메시지 생성
                Response res = ResponseHandler.notAuthenticated();
                String resBody = JsonUtils.toJson(res);

                // 로그 기록
                ExtApiLog extApiLog = createApiLog(request, resBody);
                extApiLogService.saveLog(extApiLog);
                response.getWriter().write(resBody);
                return false;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ExtApiLog extApiLog = createApiLog(request, response);
        extApiLogService.saveLog(extApiLog);
    }

    private ExtApiLog createApiLog(HttpServletRequest request, String resBody) {
        String reqBody = CommonFnc.getRequestBody(request);

        return ExtApiLog.builder()
                .clientIp(CommonFnc.getClientIp(request))
//                .clientNm() TODO : 프로젝트에 맞게 설정
                .reqUri(request.getRequestURI())
                .method(request.getMethod())
                .queryString(request.getQueryString())
                .reqBody(reqBody)
                .rgtrDt(LocalDateTime.now())
                .resBody(resBody)
                .build();
    }

    private ExtApiLog createApiLog(HttpServletRequest request, HttpServletResponse response) {
        String clientIp = CommonFnc.getClientIp(request);
        String requestURI = request.getRequestURI();
        String reqBody = CommonFnc.getRequestBody(request);
        String method = request.getMethod();
        String resBody = "";
        if (response instanceof ExtApiFilterResponseWrapper) {
            resBody = ((ExtApiFilterResponseWrapper) response).getDataStreamToString();
        }

        return ExtApiLog.builder()
                .clientIp(clientIp)
//                .clientNm() TODO : 프로젝트에 맞게 설정
                .reqUri(requestURI)
                .method(method)
                .queryString(request.getQueryString())
                .reqBody(reqBody)
                .rgtrDt(LocalDateTime.now())
                .resBody(resBody)
                .build();
    }
}
