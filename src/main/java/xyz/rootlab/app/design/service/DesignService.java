package xyz.rootlab.app.design.service;

import xyz.rootlab.app.notice.vo.NoticeVO;
import xyz.rootlab.common.response.Response;

import javax.servlet.http.HttpServletRequest;

public interface DesignService {

    Response tableList(HttpServletRequest request, NoticeVO noticeVO);

    Response tableDetail(HttpServletRequest request, NoticeVO noticeVO);
}
