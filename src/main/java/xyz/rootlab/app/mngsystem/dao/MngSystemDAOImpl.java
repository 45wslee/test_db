package xyz.rootlab.app.mngsystem.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import xyz.rootlab.app.mngsystem.vo.MngSystemVO;
import xyz.rootlab.common.dao.SqlComAbstractDAO;

@Repository
public class MngSystemDAOImpl extends SqlComAbstractDAO implements MngSystemDAO {

    @Override
    public int memberListCnt(MngSystemVO mngSystemVO) throws Exception {
        return selectOne("MngSystemDAO.memberListCnt", mngSystemVO);
    }

    @Override
    public List<EgovMap> selectMemberList(MngSystemVO mngSystemVO) throws Exception {
        return selectList("MngSystemDAO.selectMemberList", mngSystemVO);
    }
}
