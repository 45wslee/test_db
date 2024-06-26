package xyz.rootlab.app.notice.dao;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;
import xyz.rootlab.app.notice.dto.NoticeDto;
import xyz.rootlab.app.notice.vo.NoticeVO;
import xyz.rootlab.common.dao.SqlComAbstractDAO;

import java.util.List;

/**
 * 공지사항 관리를 위한 DAO 클래스
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
@Repository
public class NoticeDAOImpl extends SqlComAbstractDAO implements NoticeDAO {
    /**
     * 공지사항 등록
     *
     * @param noticeVO - vo
     */
    @Override
    public void insertNotice(NoticeVO noticeVO) {
        insert("NoticeDAO.insertNotice", noticeVO);
    }

    /**
     * 공지사항 조회
     *
     * @param ntcSn - 공지사항 일련번호
     * @return 조회 결과
     */
    @Override
    public NoticeDto selectNotice(Long ntcSn) {
        return selectOne("NoticeDAO.selectNotice", ntcSn);
    }

    @Override
    public int tableListCnt(NoticeVO noticeVO) {
        return selectOne("NoticeDAO.noticeListCnt", noticeVO);
    }

    @Override
    public List<EgovMap> tableList(NoticeVO noticeVO) {
        return selectList("NoticeDAO.noticeList", noticeVO);
    }

    @Override
    public EgovMap tableDetail(NoticeVO noticeVO) {
        return selectOne("NoticeDAO.tableDetail", noticeVO);
    }
}
