package xyz.rootlab.app.notice.dao;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import xyz.rootlab.app.notice.dto.NoticeDto;
import xyz.rootlab.app.notice.vo.NoticeVO;

import java.util.List;

/**
 * 공지사항 관리를 위한 DAO 인터페이스
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
public interface NoticeDAO {
    /**
     * 공지사항 등록
     *
     * @param noticeVO - vo
     */
    void insertNotice(NoticeVO noticeVO);

    /**
     * 공지사항 조회
     *
     * @param ntcSn - 공지사항 일련번호
     * @return
     */
    NoticeDto selectNotice(Long ntcSn);

    int tableListCnt(NoticeVO noticeVO);

    List<EgovMap> tableList(NoticeVO noticeVO);

    EgovMap tableDetail(NoticeVO noticeVO);
}
