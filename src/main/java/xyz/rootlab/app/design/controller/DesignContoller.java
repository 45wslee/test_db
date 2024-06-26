package xyz.rootlab.app.design.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.common.utils.LoginUtils;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DesignContoller {

    // HOME
    @RequestMapping(value = "/spt/dashboard")
    public String dashboard(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        Member member = LoginUtils.getAuthenticatedUser();

       /* List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        System.out.println("======================== allPrincipals.size = " + allPrincipals.size() + " ========================");
        List<SessionInformation> allSessions = sessionRegistry.getAllSessions(authentication.getPrincipal(), true);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        *//*if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }*//*
        // 각 Principal(사용자)에 대한 세션 정보를 가져옵니다.
        for (Object principal : allSessions) {
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                // 현재 사용자의 모든 세션 정보를 가져옵니다.
                List<SessionInformation> sessions = sessionRegistry.getAllSessions(userDetails, false);
                System.out.println(userDetails.getUsername());
                sessions.forEach(s -> System.out.println(userDetails.getUsername()));
            }
        }*/

       /*
        * if (authentication != null) { UserDetails userDetails = (UserDetails)
        * authentication.getPrincipal();
        * System.out.println("authentication userDetails Name = " +
        * userDetails.getUsername()); System.out.println("auth userDetails Name = " +
        * userDetails.getUsername()); } else {
        * System.out.println("authentication is Null"); }
        */
        if (member != null) {
            System.out.println("member Id = " + member.getMbrId());
        } else {
            System.out.println("Session User is Null");
        }
        System.out.println("===================== dashboard =====================");
        return "admin/design/dashboard";
    }

    // LAYOUTS & PAGES
    @RequestMapping(value = "/table/tableList")
    public String tableList(HttpServletRequest request, ModelMap model) throws Exception {
        return "admin/design/pages_table_list";
    }

    @RequestMapping(value = "/table/tableForm")
    public String tableForm(HttpServletRequest request, ModelMap model) throws Exception {
        return "admin/design/pages_table_form";
    }

    @RequestMapping(value = "/table/tableEditorForm")
    public String tableEditorForm(HttpServletRequest request, ModelMap model) throws Exception {
        return "admin/design/pages_table_form_ckeditor";
    }

    @RequestMapping(value = "/table/tableView")
    public String tableView(HttpServletRequest request, ModelMap model) throws Exception {
        return "admin/design/pages_table_view";
    }

    @RequestMapping(value = "/spt/components/{type}")
    public String components(HttpServletRequest request, ModelMap model, @PathVariable String type) throws Exception {
        String url = "admin/design/components_";
        if (StringUtils.isNotEmpty(type)) {
            url = url.concat(type);
        }
        return url;
    }
}
