package xyz.rootlab.app.menu.service;


import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import xyz.rootlab.app.member.enums.AuthCode;
import xyz.rootlab.app.menu.dto.MenuDto;
import xyz.rootlab.app.menu.vo.MenuVO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MenuService {
    List<MenuDto> getMenu(HttpSession session) throws Exception;

    List<AuthCode> getMenuAuth(String uri) throws Exception;

    List<EgovMap> getMenuList(MenuVO menuVO) throws Exception;

    int upsertMenu(MenuVO menuVO) throws Exception;

    List<EgovMap> getMenuClsList(MenuVO menuVO) throws Exception;

    EgovMap getMenuInfo(MenuVO menuVO) throws Exception;

    void deleteMenu(MenuVO menuVO) throws Exception;
}
