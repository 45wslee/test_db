package xyz.rootlab.app.auth.dao;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import xyz.rootlab.app.auth.vo.AuthVO;

import java.util.List;
import java.util.Map;

public interface AuthDAO {
    List<?> selectUserAuthList(AuthVO vo) throws Exception;

    int selectUserAuthListCnt(AuthVO vo);

    int upsertUserAuth(AuthVO vo) throws Exception;

    List<EgovMap> selectUserAuthDetail(AuthVO vo) throws Exception;

    int insertUserAuthDetail(AuthVO vo) throws Exception;

    int deleteUserAuthDetail(AuthVO vo) throws Exception;

    Map<String, Object> selectUserAuthInfo(AuthVO vo) throws Exception;

    List<EgovMap> selectMenuDepth(AuthVO vo) throws Exception;

    String createRoleCd() throws Exception;
}