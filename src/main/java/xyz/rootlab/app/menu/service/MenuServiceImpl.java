package xyz.rootlab.app.menu.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;
import xyz.rootlab.app.member.enums.AuthCode;
import xyz.rootlab.app.menu.dao.MenuDAO;
import xyz.rootlab.app.menu.dto.MenuDto;
import xyz.rootlab.app.menu.entity.Menu;
import xyz.rootlab.app.menu.repository.MenuAuthrtRepository;
import xyz.rootlab.app.menu.repository.MenuRepository;
import xyz.rootlab.app.menu.vo.MenuVO;
import xyz.rootlab.common.utils.CommonFnc;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("menuService")
@RequiredArgsConstructor
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuAuthrtRepository menuAuthrtRepository;
    private final MenuDAO menuDAO;

    /**
     * header 메뉴 조회
     */
    @Override
    public List<MenuDto> getMenu(HttpSession session) throws Exception {
        String mbrAuthCdStr = CommonFnc.getSessionAttributeStr("mbrAuthCd", session);
        AuthCode mbrAuthCd = AuthCode.findByCode(mbrAuthCdStr);
        if (mbrAuthCd == null) {
            mbrAuthCd = AuthCode.LEANER;
        }

        List<Menu> menuList = menuRepository.findAllByAuthrtCd(mbrAuthCd);

        /*if (!mbrAuthCd.equals(AuthCode.LEANER)) {
            String instTypeStr = CommonFnc.getSessionAttributeStr("instType", session);
            InstitutionType instType = InstitutionType.findByCode(instTypeStr);
            if (instType == null) {
                instType = InstitutionType.USE;
            }

            String exceptFlag = ""; // 제외할 url keyword

            if (instType.equals(InstitutionType.COLLEGE)) { // 원격대학인 경우 practice 제외
                exceptFlag = "practice";
            } else if (instType.equals(InstitutionType.PRACTICE)) { // 현장실습기관인 경우 college 제외
                exceptFlag = "college";
            }

            exceptUnAuthorizedMenu(menuList, exceptFlag);
        }*/

        return MenuDto.getMenus(menuList);
    }

    private void exceptUnAuthorizedMenu(List<Menu> menuList, String flag) throws Exception {
        if (StringUtils.isEmpty(flag)) {
            return;
        }
        menuList.removeIf(menu -> menu.getMenuCls() == 1 && menu.getMenuUrl().contains(flag));
    }

    /**
     * 메뉴 권한 조회
     */
    @Override
    public List<AuthCode> getMenuAuth(String uri) throws Exception {
        return menuAuthrtRepository.findByMenuUrl(uri);
    }

    @Override
    public List<EgovMap> getMenuList(MenuVO menuVO) throws Exception {
        return menuDAO.getMenuList(menuVO);
    }

    @Override
    public int upsertMenu(MenuVO menuVO) throws Exception {
        return menuDAO.upsertMenu(menuVO);
    }

    @Override
    public List<EgovMap> getMenuClsList(MenuVO menuVO) throws Exception {
        return menuDAO.getMenuClsList(menuVO);
    }

    @Override
    public EgovMap getMenuInfo(MenuVO menuVO) throws Exception {
        return menuDAO.getMenuInfo(menuVO);
    }
    
    @Override
    public void deleteMenu(MenuVO menuVO) throws Exception {
        menuDAO.deleteMenu(menuVO);
    }
}
