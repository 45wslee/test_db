package xyz.rootlab.common.dao;

import org.springframework.stereotype.Repository;
import xyz.rootlab.app.code.vo.CodeVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommonDAO extends SqlComAbstractDAO {

    /**
     * 공통코드 전체 목록 조회
     *
     * @param
     * @return
     */
    public List<Map<String, Object>> selectCommonCodeList(CodeVO vo) throws Exception {
        return selectList("CommonDAO.selectCommomCodeList", vo);
    }

    /**
     * 공통코드 목록 조회
     * 메인코드에 해당되는 전체 코드 목록 조회
     * 여러개 일 경우 콤마(,)로 구분
     *
     * @param
     * @return
     */
    public Map<String, Object> selectCommonCodeListByString(String mainCdStr) throws Exception {
        CodeVO vo = new CodeVO();
        Map<String, Object> map = new HashMap<>();

        String[] codeArr = mainCdStr.split(",");
        for (String code : codeArr) {
            vo.setMainCd(code);
            map.put(code, selectList("CommonDAO.selectCommomCodeList", vo));
        }

        return map;
    }

/*    *//**
     * 개인정보처리방침 및 이용약관 조회
     * foorter 또는 플랫폼 전체 영역에서 공통으로 호출
     *
     * @param
     * @return
     *//*
    public List<Map<String, Object>> selectTermsInfo() throws Exception {
        return selectList("CommonDAO.selectTermsInfo");
    }

    *//**
     * 개인정보 수집 및 이용 동의 및 이용약관 동의
     * 회원가입 및 특정 메뉴에서 필요 시 호출
     *
     * @param
     * @return
     *//*
    public List<Map<String, Object>> selectTermsInfoJoin() throws Exception {
        return selectList("CommonDAO.selectTermsInfoJoin");
    }

    public List<String> selectMenuAuthUrl(RoleVO vo) throws SQLException {
        return selectList("CommonDAO.selectMenuAuthUrl", vo);
    }*/
}
