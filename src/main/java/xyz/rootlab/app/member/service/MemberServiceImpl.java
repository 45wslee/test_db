package xyz.rootlab.app.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.app.member.repository.MemberRepository;
import xyz.rootlab.common.crypto.RsaAlgorithm;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;
import xyz.rootlab.common.utils.LoginUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 회원정보 관리를 위한 Service 클래스
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
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용일 경우 JPA에서 최적화를 수행한다.
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     *
     * @param member - 회원 Entity
     */
    @Transactional
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    /**
     * 로그인
     *
     * @param mbrId  - 회원 id
     * @param mbrPwd - 회원 비밀번호
     * @return Member
     * @throws Exception
     */
    @Override
    @Transactional
    public Member login(String mbrId, String mbrPwd) throws Exception {
        Member findMember = memberRepository.findByMbrId(mbrId);

        if (findMember == null) {
            return null;
        }

        if (!LoginUtils.comparePassword(findMember.getMbrPswd(), mbrPwd, findMember.getSalt())) {
            findMember.loginFail();
            return null;
        }

        findMember.loginSuccess();
        return findMember;
    }

    /**
     * 로그인 된 회원 정보 조회
     *
     *
     * @param session - 세션
     * @return Member
     * @throws Exception
     */
    @Override
    public Member getLoginMember(HttpSession session) throws Exception {
        if (session.getAttribute("mbrSn") == null) {
            throw new SecurityException();
        }
        Long mbrSeq = Long.parseLong(session.getAttribute("mbrSn").toString());
        return memberRepository.findById(mbrSeq).orElseThrow(SecurityException::new);
    }

    @Override
    public Member login(String mbrId) {
        return memberRepository.findByMbrId(mbrId);
    }

    @Override
    public Response loginAuth(HttpServletRequest request)  throws  Exception{
        Map<String, String> resultMap =  RsaAlgorithm.initRsaMap(request);
        return ResponseHandler.success(resultMap);
    }
}
