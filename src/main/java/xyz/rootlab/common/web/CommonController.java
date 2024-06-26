package xyz.rootlab.common.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.rootlab.common.dao.CommonDAO;
import xyz.rootlab.common.utils.LocaleMessageSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

@Controller
@RequiredArgsConstructor
public class CommonController {
    final static String RESOLVER_DEFAULT_ERROR_VIEW = "error";

    private final LocaleMessageSource messageSource;

    private final CommonDAO commonDAO;

    @RequestMapping("/err")
    public String error() {
        return RESOLVER_DEFAULT_ERROR_VIEW;
    }

    @RequestMapping("/session")
    public String session() {
        return "session";
    }

    @RequestMapping("/ckeditorSample")
    public String ckeditorSample() {
        return "/ckeditorTest";
    }

    /**
     * JS에서 다국어 사용을 위한 Message 함수 생성
     *
     * @param locale
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/js/message.js", method = RequestMethod.GET)
    public void message(Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Properties properties = messageSource.getProperties(locale);
        if (properties == null)
            return;

        String category = request.getParameter("category");
        response.resetBuffer();
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        StringBuilder builder = new StringBuilder();
        if (category != null && !category.isEmpty())
            builder.append("get" + category.substring(0, 1).toUpperCase() + category.substring(1) + "Message = function(id) {\r\n");
        else
            builder.append("getMessage = function(id) {\r\n");

        Iterator iterator = properties.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();

            if ("login".equals(category)) {
                if (!key.contains("info.")) {
                    if ((category != null && !category.isEmpty()) && !key.contains(category)) {
                        continue;
                    }
                }
            } else {
                if ((category != null && !category.isEmpty()) && !key.contains(category)) {
                    continue;
                }
            }

            String value = (String) properties.get(key);
            builder.append("    if (id == \"" + key + "\") {\r\n");
            builder.append("        return \"" + value + "\";\r\n");
            builder.append("    }\r\n");
        }
        builder.append("    return '';\r\n");
        builder.append("}\r\n");

        out.println(builder.toString());

        response.flushBuffer();
    }
}
