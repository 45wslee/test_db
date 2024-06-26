package xyz.rootlab.app.member.web;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.app.member.service.MemberService;
import xyz.rootlab.app.member.vo.MemberDto;
import xyz.rootlab.common.crypto.RsaAlgorithm;
import xyz.rootlab.common.exception.InvalidParamException;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseCode;
import xyz.rootlab.common.response.ResponseHandler;
import xyz.rootlab.common.utils.LoginUtils;
import xyz.rootlab.common.utils.Validator;

/**
 * 회원정보 관리를 위한 Controller 클래스
 *
 * @author 김태형
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 * 수정일         수정자      수정내용
 * -----------  -------   ----------------------
 * 2023.01.25   김태형      최초생성
 *
 *
 * </pre>
 * @since 2023.01.25
 */
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 로그인 화면
     *
     * @param request - 요청
     * @return view
     * @throws Exception
     */
    @GetMapping("/member/login")
    public String loginPage(HttpServletRequest request) throws Exception {
        System.out.println("================== /member/login ==================");
        RsaAlgorithm.initRsa(request);
        /*String property = EgovProperties.getProperty("aaa");
        System.out.println("property = " + property);*/
        return "admin/member/login";
    }

    /**
     * 로그인 API
     *
     * @param memberDto - 회원정보가 담긴 dto
     * @param session   - 세션
     * @return Map
     * @throws Exception
     */
    @PostMapping("/login")
    @ResponseBody
    public Response login(@ModelAttribute MemberDto memberDto, HttpSession session) throws Exception {
        // 1. RSA 복호화
        PrivateKey privateKey = (PrivateKey) session.getAttribute(RsaAlgorithm.RSA_WEB_KEY);
        if (privateKey == null) {
            return ResponseHandler.failToProcess();
        }

        String userId = RsaAlgorithm.decryptHexStr(memberDto.getMbrId(), privateKey);
        String userPw = RsaAlgorithm.decryptHexStr(memberDto.getMbrPswd(), privateKey);

        /*memberDto.setMbrId(RsaAlgorithm.decrypt(memberDto.getMbrId(), privateKey));
        memberDto.setMbrPswd(RsaAlgorithm.decrypt(memberDto.getMbrPswd(), privateKey));*/

        // 2. 파라미터 검증
        String validateParams = "mbrId:{" + Validator.REQUIRED + "};" + "mbrPswd:{" + Validator.REQUIRED + "};";
        String invalidParams = Validator.validate(memberDto, validateParams);
        if (!invalidParams.isEmpty()) {
            throw new InvalidParamException(invalidParams, ResponseCode.INVALID_PARAM);
        }

        Member loginUser = memberService.login(userId, userPw);
        if (loginUser == null) {
            return ResponseHandler.noData();
        }

        session.setAttribute("loginVO", loginUser);

        Map<String, Object> rtn = new HashMap<>();
        rtn.put("returnUrl","/spt/dashboard");
        return ResponseHandler.success(rtn);
    }

    /**
     * 회원가입 화면
     *
     * @param request - 요청
     * @param model   - 모델
     * @return view
     * @throws Exception
     */
    @GetMapping("/join")
    public String joinPage(HttpServletRequest request, ModelMap model) throws Exception {
        RsaAlgorithm.initRsa(request);
        return "admin/member/join";
    }

    /**
     * 회원가입 API
     *
     * @param memberDto - 회원정보가 담긴 dto
     * @param session   - 세션
     * @return Map
     * @throws Exception
     */
    @PostMapping(value = "/join")
    @ResponseBody
    public Response join(MemberDto memberDto, HttpSession session) throws Exception {

        // 1. RSA 복호화
        /*String privateKeyStr = RsaAlgorithm.getPrivateKey(session);
        if (StringUtils.isEmpty(privateKeyStr)) {
            throw new BizException("rsa privateKey is null", ResponseCode.NO_SESSION);
        }

        memberDto.setMbrId(RsaAlgorithm.decrypt(memberDto.getMbrId(), privateKeyStr));
        memberDto.setMbrNm(RsaAlgorithm.decrypt(memberDto.getMbrNm(), privateKeyStr));
        memberDto.setMbrPswd(RsaAlgorithm.decrypt(memberDto.getMbrPswd(), privateKeyStr));
        memberDto.setMbrTelno(RsaAlgorithm.decrypt(memberDto.getMbrTelno(), privateKeyStr));

        // 2. 파라미터 검증
        String validateParams = "mbrId:{" + Validator.REQUIRED + "};" + "mbrPswd:{" + Validator.REQUIRED + "};" + "mbrNm:{" + Validator.REQUIRED + "};" + "mbrTelno:{" + Validator.REQUIRED + "};";
        String invalidParams = Validator.validate(memberDto, validateParams);
        if (!invalidParams.isEmpty()) {
            throw new InvalidParamException(invalidParams);
        }

        // 3. Entity 변환
        Member member = memberDto.toEntity();
        memberService.join(member);*/

        return ResponseHandler.success();
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public Response logout(@ModelAttribute MemberDto memberDto, HttpSession session) throws Exception {

        Member loginUser = LoginUtils.getAuthenticatedUser();
        session.removeAttribute("loginVO");

        Map<String, Object> rtn = new HashMap<>();
        rtn.put("returnUrl","/member/login");
        return ResponseHandler.success(rtn);
    }

}
