package xyz.rootlab.app.member.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.rootlab.app.member.service.MemberService;
import xyz.rootlab.common.response.Response;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    @Autowired
    Environment env;

    private final MemberService memberService;

    /**
     * 회원가입/로그인 RSA Key 발급
     *
     * @param request
     * @return response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/api/member/loginAuth", method = RequestMethod.POST)
    public Response loginAuth(HttpServletRequest request) throws Exception {
        return memberService.loginAuth(request);
    }
}
