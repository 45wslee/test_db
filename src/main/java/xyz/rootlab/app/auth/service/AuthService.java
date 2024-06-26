package xyz.rootlab.app.auth.service;


import xyz.rootlab.app.auth.vo.AuthVO;
import xyz.rootlab.common.response.Response;

import javax.servlet.http.HttpSession;

public interface AuthService {
    Response getUserAuthList(AuthVO vo) throws Exception;

    Response upsertUserAuth(AuthVO vo) throws Exception;

    Response updateUserAuthDetail(AuthVO authVO, HttpSession session) throws Exception;
    Response getUserAuthDetail(AuthVO authVO) throws Exception;
}