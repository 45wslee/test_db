package xyz.rootlab.app.mngsystem.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MngSystemController {

    @RequestMapping(value = "/spt/mngSystem/memberList", method = RequestMethod.GET)
    public String memberList(HttpServletRequest request, ModelMap model) throws Exception {
        return "admin/mngsystem/mngSystemList";
    }
}
