package xyz.rootlab.app.notice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 공지사항 검색 정보를 담기 위한 DTO 클래스
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
@Setter
@Getter
public class NoticeSearch {
    /** 검색 종류 */
    private String searchCnd;

    /** 검색어 */
    private String searchWrd;
}
