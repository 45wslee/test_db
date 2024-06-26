package xyz.rootlab.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import xyz.rootlab.common.ext.interceptor.ExtApiInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author : 윤주호
 * @version : 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자               수정내용
 *  -------------  ------------   ---------------------
 *   2021. 7. 20    윤주호               최초 생성
 * </pre>
 * @ClassName : EgovConfigWebDispatcherServlet.java
 * @Description : DispatcherServlet 설정
 * @since : 2021. 7. 20
 */
@Configuration
@ComponentScan(basePackages = "xyz.rootlab", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
})
public class EgovConfigWebDispatcherServlet implements WebMvcConfigurer {

    final static String RESOLVER_DEFAULT_ERROR_VIEW = "error";

    final static int URL_BASED_VIEW_RESOLVER_ORDER = 1;
    final static String URL_BASED_VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    final static String URL_BASED_VIEW_RESOLVER_SUFFIX = ".jsp";

    private final String[] CORS_ORIGIN_SERVER_URLS = {"http://127.0.0.1:3000", "http://localhost:8070"};

    // =====================================================================
    // RequestMappingHandlerMapping 설정
    // =====================================================================
    // -------------------------------------------------------------
    // RequestMappingHandlerMapping 설정 - Interceptor 추가
    // -------------------------------------------------------------

    @Bean
    public ExtApiInterceptor apiLogInterceptor() {
        return new ExtApiInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLogInterceptor())
                .addPathPatterns("/ext/api/**/*");
    }

    // -------------------------------------------------------------
    // RequestMappingHandlerMapping 설정 View Controller 추가
    // -------------------------------------------------------------
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/cmmn/validator.do")
                .setViewName("cmmn/validator");
        registry.addViewController("/").setViewName("forward:/index.jsp");
    }

    // -------------------------------------------------------------
    // HandlerExceptionResolver 설정 (thkim : CommonExceptionHandler 클래스로 변경)
    // -------------------------------------------------------------
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
//
//        simpleMappingExceptionResolver.setExcludedExceptions(IllegalArgumentException.class, SecurityException.class);
//        simpleMappingExceptionResolver.setDefaultErrorView(RESOLVER_DEFAULT_ERROR_VIEW);
//
//        Properties mappings = new Properties();
//        mappings.setProperty("org.springframework.dao.DataAccessException", "cmm/error/dataAccessFailure");
//        mappings.setProperty("org.springframework.transaction.TransactionException", "cmm/error/transactionFailure");
//        mappings.setProperty("org.egovframe.rte.fdl.cmmn.exception.EgovBizException", "cmm/error/egovError");
//        mappings.setProperty("org.springframework.security.AccessDeniedException", "cmm/error/accessDenied");
//
//        simpleMappingExceptionResolver.setExceptionMappings(mappings);
//
//        exceptionResolvers.add(simpleMappingExceptionResolver);
//
//    }

    // -------------------------------------------------------------
    // View Resolver 설정
    // -------------------------------------------------------------
    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setOrder(URL_BASED_VIEW_RESOLVER_ORDER);
        urlBasedViewResolver.setViewClass(JstlView.class);
        urlBasedViewResolver.setPrefix(URL_BASED_VIEW_RESOLVER_PREFIX);
        urlBasedViewResolver.setSuffix(URL_BASED_VIEW_RESOLVER_SUFFIX);
        return urlBasedViewResolver;
    }

    // -------------------------------------------------------------
    // CORS 설정 추가
    // -------------------------------------------------------------
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("====================== addCorsMappings ====================== ");
        // Access-Control-Allow-Origin
        HttpServletRequest request = getCurrentHttpRequest();
        String origin = "";
        if (request != null) {
            origin = request.getHeader("Origin");
        }


        if (origin != null && !origin.isEmpty()) {
            origin = origin.replace("\r", "").replace("\n", "");// Security - Potential HTTP Response Splitting 분할응답 조치
        }

        registry.addMapping("/**")
                .allowedOriginPatterns(origin)
                .allowedMethods("POST", "GET", "OPTIONS", "DELETE")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "X-CSRF-TOKEN")
                .allowCredentials(true)
                .maxAge(3600)
        ;
    }

    public static HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request;
        }
        // logger.debug("Not called in the context of an HTTP request");
        return null;
    }*/
}
