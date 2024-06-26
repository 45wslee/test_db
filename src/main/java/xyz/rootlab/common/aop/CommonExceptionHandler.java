package xyz.rootlab.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.rootlab.common.exception.AuthException;
import xyz.rootlab.common.exception.BizException;
import xyz.rootlab.common.exception.InvalidParamException;
import xyz.rootlab.common.response.ResponseHandler;
import xyz.rootlab.common.utils.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;

@ControllerAdvice
@Controller
@Slf4j
public class CommonExceptionHandler {
    private static final String[] AJAX_KEY = {"x-requested-with", "ajax"};

    private static final String[] AJAX_VALUE = {"xmlhttprequest", "true"};

    private final static String RESOLVER_DEFAULT_ERROR_VIEW = "error";

    /**
     * InvalidParamException 이 발생한 경우 적절한 응답을 리턴한다.
     * @param e InvalidParamException
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ResponseHandler Object
     * @throws Exception Exception
     */
    @ExceptionHandler(value = {InvalidParamException.class})
    @ResponseBody
    public Object handleIllegalArgumentException(InvalidParamException e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(isNotAjaxRequest(request)) {
            response.sendRedirect("/err");
            return RESOLVER_DEFAULT_ERROR_VIEW;
        }

        return ResponseHandler.errorWithData(e.getParam(), e.getResponseCode());
    }

    /**
     * AuthException 이 발생한 경우 적절한 응답을 리턴한다.
     * @param e
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {AuthException.class})
    @ResponseBody
    public Object handleSecurityException(AuthException e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(isNotAjaxRequest(request)) {
            response.sendRedirect("/err");
            return RESOLVER_DEFAULT_ERROR_VIEW;
        }

        return ResponseHandler.error(e.getResponseCode());
    }

    /**
     * BindException 이 발생한 경우 적절한 응답을 리턴한다.
     * @param e
     * @param bindingResult
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public Object handleBindException(Exception e, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(isNotAjaxRequest(request)) {
            response.sendRedirect("/err");
            return RESOLVER_DEFAULT_ERROR_VIEW;
        }

        return ResponseHandler.invalidParam(Validator.getErrorMsg(bindingResult));
    }


    @ExceptionHandler(value = {BizException.class})
    @ResponseBody
    public Object handleBizException(BizException e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(isNotAjaxRequest(request)) {
            response.sendRedirect("/err");
            return RESOLVER_DEFAULT_ERROR_VIEW;
        }

        if (StringUtils.isEmpty(e.getStr())) {
            return ResponseHandler.error(e.getResponseCode());
        }
        return ResponseHandler.errorWithData(e.getStr(), e.getResponseCode());
    }

    /**
     * 위에서 정의되지 않은 예외가 발생한 경우 적절한 응답을 리턴한다.
     * @param e
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.error("UNKNOWN EXCEPTION", e);
        if(isNotAjaxRequest(request)) {
            response.sendRedirect("/err");
            return RESOLVER_DEFAULT_ERROR_VIEW;
        }

        return ResponseHandler.unknownError();
    }

    /**
     * ajax 요청인지 판별
     * @param request request
     * @return true / false
     */
    private boolean isNotAjaxRequest(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            if(Arrays.asList(AJAX_KEY).contains(key)) {
                String value = request.getHeader(key);
                if(Arrays.asList(AJAX_VALUE).contains(value)) {
                    return false;
                }
            }
        }

        String contentType = request.getContentType();
        if(contentType == null) {
            return false;
        }
        return !contentType.equals(MediaType.APPLICATION_JSON_VALUE);
    }

    // ================ EgovConfigWebDispatcherServlet.configureHandlerExceptionResolvers 설정 =============== //

//    @ExceptionHandler(value = {DataAccessException.class})
//    public String handleDataAccessException(Exception e) {
////        return "cmm/error/dataAccessFailure";
//        return RESOLVER_DEFAULT_ERROR_VIEW;
//    }
//
//    @ExceptionHandler(value = {TransactionException.class})
//    public String handleTransactionException(Exception e) {
////        return "cmm/error/transactionFailure";
//        return RESOLVER_DEFAULT_ERROR_VIEW;
//    }
//
//    @ExceptionHandler(value = EgovBizException.class)
//    public String handleEgovBizException(Exception e) {
////        return "cmm/error/egovError";
//        return RESOLVER_DEFAULT_ERROR_VIEW;
//    }
//
//    @ExceptionHandler(value = AccessDeniedException.class)
//    public String handleAccessDeniedException(Exception e) {
////        return "cmm/error/accessDenied";
//        return RESOLVER_DEFAULT_ERROR_VIEW;
//    }
}
