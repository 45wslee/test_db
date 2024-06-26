package xyz.rootlab.common.ext.filter;

import xyz.rootlab.common.utils.CommonFnc;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP 메시지의 body 데이터는 stream 으로 읽어온다.
 * stream 은 한번 읽으면 이후에 다시 읽지 못하기 때문에 wrapper 로 감싸서 body 데이터를 이후에도 읽을 수 있도록 한다.
 */
@WebFilter(urlPatterns = "/ext/api/*")
public class ExtApiFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");

        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json");

        ExtApiFilterRequestWrapper extApiFilterRequestWrapper = new ExtApiFilterRequestWrapper((HttpServletRequest) servletRequest);
        ExtApiFilterResponseWrapper apiFilterResponseWrapper = new ExtApiFilterResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(extApiFilterRequestWrapper, apiFilterResponseWrapper);

        CommonFnc.logging(extApiFilterRequestWrapper, apiFilterResponseWrapper);

        String dataStreamToString = apiFilterResponseWrapper.getDataStreamToString();
        servletResponse.getWriter().write(dataStreamToString);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {

    }
}
