package xyz.rootlab.app.mngsystem.service;

import javax.servlet.http.HttpServletRequest;

import xyz.rootlab.app.mngsystem.vo.MngSystemVO;
import xyz.rootlab.common.response.Response;

public interface MngSystemService {

    Response memberList(HttpServletRequest request, MngSystemVO mngSystemVO) throws Exception;

}
