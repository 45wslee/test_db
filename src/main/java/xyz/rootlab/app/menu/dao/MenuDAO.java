package xyz.rootlab.app.menu.dao;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import xyz.rootlab.app.menu.vo.MenuVO;

import java.util.List;

public interface MenuDAO {
    List<EgovMap> getMenuList(MenuVO menuVO) throws Exception;

    int upsertMenu(MenuVO menuVO);

    List<EgovMap> getMenuClsList(MenuVO menuVO) throws Exception;

    EgovMap getMenuInfo(MenuVO menuVO) throws Exception;

    void deleteMenu(MenuVO menuVO) throws Exception;
}
