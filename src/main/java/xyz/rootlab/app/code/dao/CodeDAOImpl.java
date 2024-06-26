package xyz.rootlab.app.code.dao;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;
import xyz.rootlab.app.code.vo.CodeVO;
import xyz.rootlab.common.dao.SqlComAbstractDAO;

import java.util.List;

@Repository("CodeDAO")
public class CodeDAOImpl extends SqlComAbstractDAO implements CodeDAO {
    @Override
    public List<?> selectCodeList(CodeVO vo) throws Exception {
        return selectList("CodeDAO.selectCodeList", vo);
    }

    @Override
    public void updateSubCodePriority(CodeVO vo) throws Exception {
        update("CodeDAO.updateCodePriority", vo);
    }

    @Override
    public int insertCode(CodeVO vo) throws Exception {
        return insert("CodeDAO.insertCode", vo);
    }

    @Override
    public void updateCode(CodeVO vo) throws Exception {
        update("CodeDAO.updateCode", vo);
    }

    @Override
    public EgovMap selectCodeInfo(CodeVO vo) throws Exception {
        return selectOne("CodeDAO.selectCodeInfo", vo);
    }

    @Override
    public int upsertCode(CodeVO vo) throws Exception {
        return insert("CodeDAO.upsertCode", vo);
    }
}