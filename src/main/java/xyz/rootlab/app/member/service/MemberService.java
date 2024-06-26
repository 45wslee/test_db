package xyz.rootlab.app.member.service;

import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.common.response.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 회원정보 관리를 위한 Service 인터페이스
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
public interface MemberService {
    void join(Member member);

    Member login(String mbrId, String mbrPwd) throws Exception;

    Member getLoginMember(HttpSession session) throws Exception;

    Member login(String insertedId);

    Response loginAuth(HttpServletRequest request) throws Exception;
}
