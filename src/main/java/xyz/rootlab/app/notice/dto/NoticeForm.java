package xyz.rootlab.app.notice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import xyz.rootlab.app.notice.entity.Notice;
import xyz.rootlab.app.notice.vo.NoticeVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 공지사항 등록 정보를 담기 위한 DTO 클래스
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
@Getter
@Setter
@Builder
public class NoticeForm {
    /** 공지사항 일련번호 */
    private Long ntcSn;

    /** 공지사항 제목 */
    private String ntcTtl;

    /** 공지사항 내용 */
    private String ntcCn;

    /** 공지사항 등록자 */
    private String createdBy;

    /** 공지사항 첨부파일 목록 */
    private Object files;

    /** 삭제할 파일 일련번호 목록 */
    private List<Long> deleteFileSnList;

    /**
     * DTO 를 Entity 로 변환하기 위한 메소드
     * @return Notice entity
     */
    public Notice toEntity() {
        return Notice.builder()
                .ntcTtl(ntcTtl)
                .ntcCn(ntcCn)
//                .author(member)
                .build();
    }

    /**
     * DTO 를 VO 로 변환하기 위한 메소드
     * @param mbrSeq
     * @return - NoticeVO
     */
    public NoticeVO toVo(Long mbrSeq) {
        LocalDateTime now = LocalDateTime.now();
        NoticeVO noticeVO = new NoticeVO();
        noticeVO.setNtcTtl(ntcTtl);
        noticeVO.setNtcCn(ntcCn);
        noticeVO.setMbrSn(mbrSeq);
        noticeVO.setRgtrSn(mbrSeq);
        noticeVO.setRgtrDt(now);
        noticeVO.setMdfrDt(now);
        return noticeVO;
    }
}
