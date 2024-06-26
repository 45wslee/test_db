package xyz.rootlab.app.code.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;
import xyz.rootlab.app.code.service.CodeService;
import xyz.rootlab.app.code.vo.CodeVO;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Transactional
public class CodeApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeApiController.class);
    private final CodeService codeService;

    /**
     * 공통코드 전체 목록 조회
     *
     * @param request
     * @return ResponseHandler
     * @throws Exception
     */
    @RequestMapping(value = "/spt/code/selectCommonCodeList", method = RequestMethod.GET)
    public Response selectCommonCodeList(HttpServletRequest request, CodeVO vo) throws Exception {
        List<Map<String, Object>> result = (List<Map<String, Object>>) codeService.getCodeList(vo);
        if (result.isEmpty()) {
            return ResponseHandler.noData();
        } else {
            return ResponseHandler.success(result);
        }
    }

    /**
     * 하위 공통 코드 위치 이동
     *
     * @param codeVO
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/code/chgCodeSort", method = RequestMethod.POST)
    public Response chgCodeSort(CodeVO codeVO, HttpServletRequest request, HttpSession session) throws Exception {
        // TODO : 권한 확인 필요
        // LoginVO loginVO = LoginUtil.getAuthenticatedUser();


        if (codeVO.getCodeList() == null || codeVO.getCodeList().size() == 0) {
            return ResponseHandler.failToProcess();
        }

        for (int i = 0; i < codeVO.getCodeList().size(); i++) {
            // codeVO.getCodeList().get(i).setSysmodidx(loginVO.getIdx());
        }

        List<EgovMap> resultList = null;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        try {
            resultList = (List<EgovMap>) codeService.updateSubCodePriority(codeVO);
        } catch (Exception e) {
            return ResponseHandler.failToProcess();
        }

        return ResponseHandler.success(resultList);
    }

    /**
     * 상위 공통코드 추가
     *
     * @param codeVO
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/code/addParntCode", method = RequestMethod.POST)
    public Response addParntCode(CodeVO codeVO, HttpServletRequest request, HttpSession session) throws Exception {
        // TODO : 권한 확인 필요
        // LoginVO loginVO = LoginUtil.getAuthenticatedUser();
//        if (loginVO == null || !UTY_TECH_MBR.equals(loginVO.getUserType()) || !GRADE_ADMIN.equals(loginVO.getUserGrade())) {
//            return ResponseHandler.responseFail("Not auth");
//        }

        if (StringUtils.isEmpty(codeVO.getMainCd()) || StringUtils.isEmpty(codeVO.getCodeNm())) {
            return ResponseHandler.invalidParam("Invalid Parameter(mainCd or codeNm)");
        }

        codeVO.setSubCd("000");
        codeVO.setTotalCd(codeVO.getMainCd() + codeVO.getSubCd());
        codeVO.setUseYn("N"); // 상위 카테고리는 노출하지 않도록 N으로 설정
        // codeVO.setSysregidx(loginVO.getIdx());

        List<EgovMap> resultList = null;

        try {
            resultList = (List<EgovMap>) codeService.addParntCode(codeVO);
            if (resultList == null) {
                return ResponseHandler.failToInsert();
            }
        } catch (Exception e) {
            LOGGER.error("error message : {}", e.getMessage());
            return ResponseHandler.failToInsert();
        }
        return ResponseHandler.success(resultList);
    }

    /**
     * 하위 공통코드 등록 or 수정
     *
     * @param codeVO
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/code/updateSubCode", method = RequestMethod.POST)
    public Response updateSubCode(CodeVO codeVO, HttpServletRequest request, HttpSession session) throws Exception {
        // TODO : 권한 확인 필요
        //LoginVO loginVO = LoginUtil.getAuthenticatedUser();
//        if (loginVO == null || !UTY_TECH_MBR.equals(loginVO.getUserType()) || !GRADE_ADMIN.equals(loginVO.getUserGrade())) {
//            return ResponseHandler.responseFail("Not auth");
//        }

        // 필수 값 여부 확인
        if ((StringUtils.isEmpty(codeVO.getMainCd()) || StringUtils.isEmpty(codeVO.getCodeNm()) || StringUtils.isEmpty(codeVO.getUseYn())) || StringUtils.isEmpty(codeVO.getTotalCd())) {
            return ResponseHandler.invalidParam("Invalid Parameter(mainCd or CodeNm or useYn");
        }

        try {
            List<?> list = codeService.upsertCode(codeVO, false);
            return ResponseHandler.success(list);
        } catch (Exception e) {
            LOGGER.error("error message : {}", e.getMessage());
            return ResponseHandler.failToProcess();
        }
    }

    /**
     * 하위 공통코드 상세정보
     *
     * @param codeVO
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/code/getCodeInfo", method = RequestMethod.GET)
    public Response addParntCode(CodeVO codeVO, HttpServletRequest request) throws Exception {
        EgovMap result = codeService.getCodeInfo(codeVO);
        return ResponseHandler.success(result);
    }

    /**
     * 상위 코드 중복체크
     *
     * @param codeVO
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/code/duplicateCode", method = RequestMethod.POST)
    public Response duplicateCode(CodeVO codeVO, HttpServletRequest request) throws Exception {
        CodeVO vo = new CodeVO();
        BeanUtils.copyProperties(codeVO, vo);
        EgovMap result = codeService.getCodeInfo(vo);
        if (result == null) {
            return ResponseHandler.success();
        } else {
            return ResponseHandler.existInfo();
        }
    }

    /**
     * 상위 공통코드 등록 or 수정
     *
     * @param codeVO
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/code/updateCode", method = RequestMethod.POST)
    public Response updateCode(CodeVO codeVO, HttpServletRequest request, HttpSession session) throws Exception {
        // TODO : 권한 확인 필요
        //LoginVO loginVO = LoginUtil.getAuthenticatedUser();
//        if (loginVO == null || !UTY_TECH_MBR.equals(loginVO.getUserType()) || !GRADE_ADMIN.equals(loginVO.getUserGrade())) {
//            return ResponseHandler.responseFail("Not auth");
//        }

        // 필수 값 여부 확인
        if (StringUtils.isEmpty(codeVO.getCodeNm()) || StringUtils.isEmpty(codeVO.getUseYn()) || StringUtils.isEmpty(codeVO.getTotalCd())) {
            return ResponseHandler.invalidParam("Invalid Parameter(mainCd or CodeNm or useYn");
        }

        try {
            List<?> list = codeService.upsertCode(codeVO, true);
            // int rtn = codeService.upsertCode(codeVO);
            return ResponseHandler.success(list);
        } catch (Exception e) {
            LOGGER.error("error message : {}", e.getMessage());
            return ResponseHandler.failToProcess();
        }
    }
}
