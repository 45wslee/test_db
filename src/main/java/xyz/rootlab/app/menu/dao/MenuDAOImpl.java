package xyz.rootlab.app.menu.dao;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;
import xyz.rootlab.app.menu.vo.MenuVO;
import xyz.rootlab.common.dao.SqlComAbstractDAO;

import java.util.List;

@Repository
public class MenuDAOImpl extends SqlComAbstractDAO implements MenuDAO {
    @Override
    public List<EgovMap> getMenuList(MenuVO menuVO) throws Exception {
        return selectList("MenuDAO.getMenuList", menuVO);
    }

    @Override
    public int upsertMenu(MenuVO menuVO) {
        return insert("MenuDAO.upsertMenu", menuVO);
    }

    @Override
    public List<EgovMap> getMenuClsList(MenuVO menuVO) {
        return selectList("MenuDAO.getMenuClsList", menuVO);
    }

    @Override
    public EgovMap getMenuInfo(MenuVO menuVO) throws Exception {
        return selectOne("MenuDAO.getMenuInfo", menuVO);
    }

    @Override
    public void deleteMenu(MenuVO menuVO) throws Exception {
        update("MenuDAO.deleteMenu", menuVO);
    }
}
