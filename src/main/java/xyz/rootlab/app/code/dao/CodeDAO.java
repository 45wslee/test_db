package xyz.rootlab.app.code.dao;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import xyz.rootlab.app.code.vo.CodeVO;

import java.util.List;

public interface CodeDAO {
    List<?> selectCodeList(CodeVO vo) throws Exception;

    void updateSubCodePriority(CodeVO vo) throws Exception;

    int insertCode(CodeVO vo) throws Exception;

    void updateCode(CodeVO vo) throws Exception;

    EgovMap selectCodeInfo(CodeVO vo) throws Exception;

    int upsertCode(CodeVO vo) throws Exception;
}