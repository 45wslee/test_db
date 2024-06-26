package xyz.rootlab.app.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.rootlab.app.member.entity.Member;

/**
 * 회원정보 관리를 위한 Repository 인터페이스
 *
 * @author 김태형
 * @version 1.0
 * @since 2023.01.25
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 수정일         수정자      수정내용
 * -----------  -------   ----------------------
 * 2023.01.25   김태형      최초생성
 *
 *
 * </pre>
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    /**
     * id 를 통해 회원 조회
     * @param mbrId - 회원 id
     * @return Member
     */
    Member findByMbrId(String mbrId);
}
