package xyz.rootlab.app.code.service;


import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import xyz.rootlab.app.code.vo.CodeVO;

import java.util.List;

public interface CodeService {
    List<?> getCodeList(CodeVO vo) throws Exception;

    List<?> updateSubCodePriority(CodeVO vo) throws Exception;

    List<?> addParntCode(CodeVO vo) throws Exception;

    void registSubCode(CodeVO vo) throws Exception;

    List<?> upsertCode(CodeVO vo, boolean isParent) throws Exception;

    EgovMap getCodeInfo(CodeVO vo) throws Exception;
}