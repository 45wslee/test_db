package xyz.rootlab.app.notice.entity;

import lombok.*;
import xyz.rootlab.app.notice.dto.NoticeForm;
import xyz.rootlab.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 공지사항 관리를 위한 Entity 클래스
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
@Table(name = "tb_ntc")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Notice extends BaseEntity {
    /** 공지사항 일련번호 */
    @Id
    @GeneratedValue
    @Column(name = "NTC_SN")
    private Long ntcSn;

    /** 공지사항 제목 */
    @Column
    private String ntcTtl;

    /** 공지사항 내용 */
    @Column
    private String ntcCn;

    /**
     * 공지사항의 내용을 변경하기 위한 메소드
     * @param noticeForm - 변경할 내용
     */
    public void updateNotice(NoticeForm noticeForm) {
        this.ntcTtl = noticeForm.getNtcTtl();
        this.ntcCn = noticeForm.getNtcCn();
    }
}
