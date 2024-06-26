package xyz.rootlab.app.auth.dao;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;
import xyz.rootlab.app.auth.vo.AuthVO;
import xyz.rootlab.app.code.vo.CodeVO;
import xyz.rootlab.common.dao.SqlComAbstractDAO;

import java.util.List;
import java.util.Map;

@Repository("AuthDAO")
public class AuthDAOImpl extends SqlComAbstractDAO implements AuthDAO {
    @Override
    public List<?> selectUserAuthList(AuthVO vo) throws Exception {
        return selectList("AuthDAO.selectUserAuthList", vo);
    }

    @Override
    public int selectUserAuthListCnt(AuthVO vo) {
        return selectOne("AuthDAO.selectUserAuthListCnt", vo);
    }

    @Override
    public int upsertUserAuth(AuthVO vo) throws Exception {
        return insert("AuthDAO.upsertUserAuth", vo);
    }

    @Override
    public List<EgovMap> selectUserAuthDetail(AuthVO vo) throws Exception {
        return selectList("AuthDAO.selectUserAuthDetail", vo);
    }

    @Override
    public int insertUserAuthDetail(AuthVO vo) throws Exception {
        return insert("AuthDAO.insertUserAuthDetail", vo);
    }

    @Override
    public int deleteUserAuthDetail(AuthVO vo) throws Exception {
        return delete("AuthDAO.deleteUserAuthDetail", vo);
    }

    @Override
    public Map<String, Object> selectUserAuthInfo(AuthVO vo) throws Exception {
        return selectOne("AuthDAO.selectUserAuthInfo", vo);
    }

    @Override
    public List<EgovMap> selectMenuDepth(AuthVO vo) throws Exception {
        return selectList("AuthDAO.selectMenuDepth", vo);
    }

    @Override
    public String createRoleCd() throws Exception {
        return selectOne("AuthDAO.createRoleCd");
    }
}