package xyz.rootlab.app.design.service;

import lombok.RequiredArgsConstructor;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.rootlab.app.notice.dao.NoticeDAO;
import xyz.rootlab.app.notice.vo.NoticeVO;
import xyz.rootlab.common.paging.PagingHelper;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("designService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DesignServiceImpl implements DesignService {

    private final NoticeDAO noticeDAO;


    @Override
    public Response tableList(HttpServletRequest request, NoticeVO noticeVO) {
        Map<String, Object> rtn = new HashMap<>();

        int totalCnt = noticeDAO.tableListCnt(noticeVO);

        PaginationInfo paginationInfo = PagingHelper.getDefaultPaginationInfo(noticeVO);
        noticeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        List<EgovMap> resultList = noticeDAO.tableList(noticeVO);
        paginationInfo.setTotalRecordCount(totalCnt);

        rtn.put("paginationInfo", paginationInfo);
        rtn.put("resultList", resultList);
        rtn.put("totalCnt", totalCnt);

        // throw new RuntimeException();
        return ResponseHandler.success(rtn);
    }

    @Override
    public Response tableDetail(HttpServletRequest request, NoticeVO noticeVO) {
        Map<String, Object> rtn = new HashMap<>();

        EgovMap result = noticeDAO.tableDetail(noticeVO);
        rtn.put("result", result);
        return ResponseHandler.success(rtn);
    }
}
