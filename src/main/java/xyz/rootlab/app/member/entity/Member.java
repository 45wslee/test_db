package xyz.rootlab.app.member.entity;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Comment;
import xyz.rootlab.common.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 회원 관리 Entity 클래스
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
@Entity
@Getter
@Table(name = "tb_mbr")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MBR_SN")
    @Comment("회원 번호")
    private Long mbrSn;

    @Column
    @NotNull
    @Comment("아이디")
    private String mbrId;

    @Column
    @NotNull
    @Comment("비밀번호")
    private String mbrPswd;

    @Column
    @NotNull
    @Comment("회원 이름")
    private String mbrNm;

    @Column
    @NotNull
    @Comment("휴대폰번호")
    private String mbrTelno;

    @Column
    @NotNull
    @Comment("이메일")
    private String mbrEmail;

    @Column
    @Comment("난수값")
    private String salt;

    @Column
    @Comment("로그인 실패 횟수")
    private int loginFailCnt;

    @Column
    @Comment("잠금 여부")
    private String lockYn;

    @Column
    @Comment("사용 여부")
    private String useYn;

    @Column
    @Comment("룰")
    private String Role;

    @Column
    @Comment("권한")
    private String mbrAuth;

    public void loginFail() {
        this.loginFailCnt++;
    }

    public void loginSuccess() {
        this.loginFailCnt = 0;
    }

    @PrePersist
    public void prePersist() {
        this.lockYn = StringUtils.isEmpty(this.lockYn) ? "N" : this.lockYn;
        this.useYn = StringUtils.isEmpty(this.useYn) ? "Y" : this.useYn;
    }
}
