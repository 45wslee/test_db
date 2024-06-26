package xyz.rootlab.app.member.vo;

import lombok.Getter;
import lombok.Setter;
import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.common.utils.LoginUtils;

/**
 * 회원 정보를 담기 위한 DTO 클래스
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
@Getter
@Setter
public class MemberDto {
    /** 회원 아이디 */
    private String mbrId;
    /** 회원 비밀번호 */
    private String mbrPswd;
    /** 회원 이름 */
    private String mbrNm;
    /** 회원 휴대전화번호 */
    private String mbrTelno;

    /**
     * DTO 를  entity 로 변환하기 위한 메소드
     *
     * @return Member entity
     * @throws Exception
     */
    public Member toEntity() throws Exception {
        String generatedSalt = LoginUtils.getGeneratedSalt();
        String encryptedPassword = LoginUtils.makeEncryptPassword(mbrPswd, generatedSalt);
        return Member.builder()
                .mbrId(mbrId)
                .mbrPswd(encryptedPassword)
                .mbrNm(mbrNm)
                .mbrTelno(mbrTelno)
                .salt(generatedSalt)
                .build();
    }
}
