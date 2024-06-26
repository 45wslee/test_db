package xyz.rootlab.app.member.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;


/**
 * JPA 에서 기본정보(등록자, 등록일, 수정자, 수정일) 을 자동으로 입력해주기 위한 Aware 클래스
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
@Component
@RequiredArgsConstructor
public class LoginUserAuditorAware implements AuditorAware<Long> {

    private final HttpSession session;

    /**
     * 세션에서 회원정보를 조회하여 등록자, 수정자를 설정하는 메소드
     *
     * @return 회원 일련번호(Optional)
     */
    @Override
    public Optional<Long> getCurrentAuditor() {
        Object mbrSn = session.getAttribute("mbrSn");
        if (mbrSn == null) {
            return Optional.empty();
        }

        return Optional.of(Long.parseLong(mbrSn.toString()));
    }
}
