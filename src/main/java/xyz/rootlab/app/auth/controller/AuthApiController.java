package xyz.rootlab.app.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.rootlab.app.auth.service.AuthService;
import xyz.rootlab.app.auth.vo.AuthVO;
import xyz.rootlab.common.response.Response;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthService authService;

    @RequestMapping(value = "/spt/auth/userAuthList", method = RequestMethod.GET)
    public Response userAuthList(HttpServletRequest request, AuthVO authVO) throws Exception {
        return authService.getUserAuthList(authVO);
    }

    @RequestMapping(value = "/spt/auth/upsertUserAuth", method = RequestMethod.POST)
    public Response upsertUserAuth(HttpServletRequest request, AuthVO authVO) throws Exception {
        return authService.upsertUserAuth(authVO);
    }

    @RequestMapping(value = "/spt/auth/updateUserAuthDetail", method = RequestMethod.POST)
    public Response updateUserAuthDetail(HttpServletRequest request, AuthVO authVO) throws Exception {
        return authService.updateUserAuthDetail(authVO, request.getSession());
    }

    @RequestMapping(value = "/spt/auth/getUserAuthDetail", method = RequestMethod.GET)
    public Response getUserAuthDetail(HttpServletRequest request, AuthVO authVO) throws Exception {
        return authService.getUserAuthDetail(authVO);
    }
}
