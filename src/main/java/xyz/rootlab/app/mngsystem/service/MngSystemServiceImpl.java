package xyz.rootlab.app.mngsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.rootlab.app.mngsystem.dao.MngSystemDAO;
import xyz.rootlab.app.mngsystem.vo.MngSystemVO;
import xyz.rootlab.common.paging.PagingHelper;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;

@Service("MngSystemService")
@RequiredArgsConstructor
public class MngSystemServiceImpl extends EgovAbstractServiceImpl implements MngSystemService {

    private final MngSystemDAO mngSystemDAO;

    @Override
    public Response memberList(HttpServletRequest request, MngSystemVO mngSystemVO) throws Exception {
        Map<String, Object> rtn = new HashMap<>();

        int totalCnt = mngSystemDAO.memberListCnt(mngSystemVO);

        PaginationInfo paginationInfo = PagingHelper.getDefaultPaginationInfo(mngSystemVO);
        mngSystemVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        List<EgovMap> resultList = mngSystemDAO.selectMemberList(mngSystemVO);
        paginationInfo.setTotalRecordCount(totalCnt);

        rtn.put("paginationInfo", paginationInfo);
        rtn.put("resultList", resultList);
        rtn.put("totalCnt", totalCnt);
        return ResponseHandler.success(rtn);
    }
}
