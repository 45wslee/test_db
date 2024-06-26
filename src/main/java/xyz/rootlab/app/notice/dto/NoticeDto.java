package xyz.rootlab.app.notice.dto;

import lombok.*;
import xyz.rootlab.app.notice.entity.Notice;
import xyz.rootlab.common.file.dto.FileDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 공지사항 정보를 담기 위한 DTO 클래스
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
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
    /** 공지사항 일련번호 */
    private Long ntcSn;

    /** 공지사항 제목 */
    private String ntcTtl;

    /** 공지사항 내용 */
    private String ntcCn;

    /** 공지사항 작성자 */
    private String author;

    /** 공지사항 최종수정일 */
    private LocalDateTime lastModifiedDate;

    /** 공지사항 첨부파일 목록 */
    private List<FileDto> fileList;

    /** 등록자 일련번호 */
    private Long rgtrSn;

    /**
     * Entity 를 DTO 로 변환하는 메소드
     * @param notice - Entity
     * @param author - 작성자
     * @return
     */
    public static NoticeDto fromEntity(Notice notice, String author) {
        return NoticeDto.builder()
                .ntcSn(notice.getNtcSn())
                .ntcTtl(notice.getNtcTtl())
                .author(author)
                .ntcCn(notice.getNtcCn())
                .lastModifiedDate(notice.getMdfrDt())
                .build();
    }
}
