package xyz.rootlab.app.code.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.rootlab.app.code.service.CodeService;
import xyz.rootlab.app.code.vo.CodeVO;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CodeController {

    /**
     * @param request
     * @return page
     * @throws Exception
     */
    @RequestMapping(value = "/spt/code/codeList", method = RequestMethod.GET)
    public String commonCodeList(HttpServletRequest request, ModelMap model) throws Exception {
        return "/admin/code/codeList";
    }
}
