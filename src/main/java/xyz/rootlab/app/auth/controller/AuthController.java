package xyz.rootlab.app.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class AuthController {

    @RequestMapping(value = "/spt/auth/authList", method = RequestMethod.GET)
    public String authList(HttpServletRequest request, ModelMap model) throws Exception {
        return "admin/auth/authList";
    }

    @RequestMapping(value = {"/spt/auth/authView/{roleCd}", "/spt/auth/authWrite"}, method = RequestMethod.GET)
    public String authWrite(HttpServletRequest request, ModelMap model, @PathVariable(value = "roleCd", required = false) String roleCd) throws Exception {
        model.addAttribute("roleCd", roleCd);
        return "admin/auth/authView";
    }
}
