package xyz.rootlab.app.auth.service;

import lombok.RequiredArgsConstructor;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.rootlab.app.auth.dao.AuthDAO;
import xyz.rootlab.app.auth.vo.AuthVO;
import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.common.paging.PagingHelper;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;
import xyz.rootlab.common.utils.LoginUtils;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service("AuthService")
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthDAO authDAO;

    @Override
    public Response getUserAuthList(AuthVO vo) throws Exception {
        Map<String, Object> rtn = new HashMap<>();

        int totalCnt = authDAO.selectUserAuthListCnt(vo);

        PaginationInfo paginationInfo = PagingHelper.getDefaultPaginationInfo(vo);
        vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
        List<EgovMap> resultList = (List<EgovMap>) authDAO.selectUserAuthList(vo);
        paginationInfo.setTotalRecordCount(totalCnt);

        rtn.put("paginationInfo", paginationInfo);
        rtn.put("resultList", resultList);
        rtn.put("totalCnt", totalCnt);

        return ResponseHandler.success(rtn);
    }

    @Override
    public Response upsertUserAuth(AuthVO vo) throws Exception {
        Map<String, Object> rtn = new HashMap<>();
        vo.setRgtrSn(1L);
        if (authDAO.upsertUserAuth(vo) > 0) {
            return ResponseHandler.success();
        } else {
            return ResponseHandler.failToUpsert();
        }
    }

    @Override
    public Response updateUserAuthDetail(AuthVO authVO, HttpSession session) throws Exception {
        Member member = LoginUtils.getAuthenticatedUser();
        Map<String, Object> rtn = new HashMap<>();
        AuthVO vo = new AuthVO();

        if (member == null) {
            return ResponseHandler.notAuthenticated();
        }

        String menuCd = authVO.getMenuCd();
        String[] menuCdList = menuCd.split(",");
        Stream<String> strStream = Arrays.stream(menuCdList);
        BeanUtils.copyProperties(authVO, vo);
        boolean insertFlg = authVO.getRoleCd().isEmpty();

        try {
            vo.setRgtrSn(member.getMbrSn());
            // 권한 등록/수정
            int cnt = authDAO.upsertUserAuth(vo);
            if (cnt == 0) {
                return ResponseHandler.failToUpsert();
            }

            if (insertFlg) {
                // 등록인 경우 권한코드 신규생성
                String roleCd = authDAO.createRoleCd();
                vo.setRoleCd(roleCd);
            } else {
                // 수정인 경우 권한 상세내용 전체삭제 후 재등록
                int delCnt = authDAO.deleteUserAuthDetail(vo);
                if (delCnt == 0) {
                    return ResponseHandler.failToUpsert();
                }
            }
            vo.setAuthrtCd(vo.getRoleCd());
            strStream.forEach(name ->
            {
                vo.setMenuCd(name);
                try {
                    int insertCnt = authDAO.insertUserAuthDetail(vo);
                    if (insertCnt == 0) {
                        throw new RuntimeException();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.getMessage();
            return ResponseHandler.failToUpsert();
        }
        return ResponseHandler.success(rtn);
    }

    @Override
    public Response getUserAuthDetail(AuthVO authVO) throws Exception {

        Map<String, Object> rtn = new HashMap<>();
        List<EgovMap> menuDepth1 = authDAO.selectMenuDepth(authVO);
        Map<String, Object> menuDepth2 = new HashMap<>();

        List<EgovMap> resultList = authDAO.selectUserAuthDetail(authVO);
        Map<String, Object> resultInfo = authDAO.selectUserAuthInfo(authVO);

        for (EgovMap map : menuDepth1) {
            List<EgovMap> list = (List<EgovMap>) map.get("depth");
            for (EgovMap e : list) {
                AuthVO vo = new AuthVO();
                String menuCd = (String) e.get("menuCd");
                vo.setParntMenuCd(menuCd);
                vo.setMenuCd(menuCd);
                List<EgovMap> depth2 = authDAO.selectMenuDepth(vo);
                if (!depth2.isEmpty()) {
                    menuDepth2.put(menuCd, depth2);
                }
            }
        }

        rtn.put("menuDepth1", menuDepth1);
        rtn.put("menuDepth2", menuDepth2);
        rtn.put("resultInfo", resultInfo);
        rtn.put("resultList", resultList);
        return ResponseHandler.success(rtn);
    }
}