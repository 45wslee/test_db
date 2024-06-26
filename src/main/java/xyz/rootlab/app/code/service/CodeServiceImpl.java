package xyz.rootlab.app.code.service;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;
import xyz.rootlab.app.code.dao.CodeDAO;
import xyz.rootlab.app.code.vo.CodeVO;

import javax.annotation.Resource;
import java.util.List;

@Service("CodeService")
public class CodeServiceImpl implements CodeService {
    @Resource(name = "CodeDAO")
    private CodeDAO codeDAO;

    @Override
    public List<?> getCodeList(CodeVO vo) throws Exception {
        return codeDAO.selectCodeList(vo);
    }

    @Override
    public List<?> updateSubCodePriority(CodeVO vo) throws Exception {
        codeDAO.updateSubCodePriority(vo);
        return codeDAO.selectCodeList(vo);
    }

    @Override
    public List<?> addParntCode(CodeVO vo) throws Exception {
        System.out.println(vo.toString());
        int rtn = codeDAO.insertCode(vo);
        if (rtn > 0) {
            vo.setMainCd("");
            vo.setCodeNm("");
        } else {
            return null;
        }
        return codeDAO.selectCodeList(vo);
    }

    @Override
    public void registSubCode(CodeVO vo) throws Exception {
        EgovMap resultVO = codeDAO.selectCodeInfo(vo);

        vo.setTotalCd(resultVO.get("totalCd").toString());
        vo.setMainCd(resultVO.get("parntMainCd").toString());
        vo.setSubCd(resultVO.get("subCd").toString());
        // vo.setSort(resultVO.get("codeCnt").toString());

        codeDAO.insertCode(vo);
    }

    @Override
    public List<?> upsertCode(CodeVO vo, boolean isParent) throws Exception {
        int rtn = codeDAO.upsertCode(vo);
        CodeVO codeVO = new CodeVO();
        if (rtn > 0) {
            if (isParent) {
                codeVO.setSubCd("000");
                return codeDAO.selectCodeList(codeVO);
            } else {
                codeVO.setMainCd(vo.getMainCd());
                return codeDAO.selectCodeList(codeVO);
            }
        } else {
            return null;
        }
    }


    @Override
    public EgovMap getCodeInfo(CodeVO vo) throws Exception {
        return codeDAO.selectCodeInfo(vo);
    }
}