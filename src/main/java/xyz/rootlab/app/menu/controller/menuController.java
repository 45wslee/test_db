package xyz.rootlab.app.menu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class menuController {

    @RequestMapping(value = "/spt/menu/menuList", method = RequestMethod.GET)
    public String menuList(HttpServletRequest request, ModelMap model) throws Exception {
        return "admin/menu/menuList";
    }

    @RequestMapping(value = "/spt/menu/menuWrite", method = RequestMethod.GET)
    public String menuWrite(HttpServletRequest request, ModelMap model) throws Exception {
        return "admin/menu/menuWrite";
    }

    @RequestMapping(value = "/spt/menu/menuView", method = RequestMethod.GET)
    public String menuView(HttpServletRequest request, ModelMap model) throws Exception {
        return "admin/menu/menuView";
    }
}
