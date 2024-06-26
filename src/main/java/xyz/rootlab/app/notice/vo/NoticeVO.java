package xyz.rootlab.app.notice.vo;

import lombok.Getter;
import lombok.Setter;
import xyz.rootlab.common.vo.ApiDefaultVO;
import xyz.rootlab.common.vo.SearchVO;

/**
 * 공지사항 등록 정보를 담고 있는 VO 클래스(Mybatis 용)
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
public class NoticeVO extends SearchVO {
    /** 공지사항 일련번호 */
    private Long ntcSn;

    /** 공지사항 제목 */
    private String ntcTtl;

    /** 공지사항 내용 */
    private String ntcCn;

    /** 회원 일련번호 */
    private Long mbrSn;
}
