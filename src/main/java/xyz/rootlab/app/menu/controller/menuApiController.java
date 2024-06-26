package xyz.rootlab.app.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.rootlab.app.menu.service.MenuService;
import xyz.rootlab.app.menu.vo.MenuVO;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;

@RestController
@RequiredArgsConstructor
public class menuApiController {

    private final MenuService menuService;

    /***
     * 메뉴 리스트
     * @param request
     * @param model
     * @param menuVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/menu/getMenuList", method = RequestMethod.GET)
    public Response menuList(HttpServletRequest request, ModelMap model, MenuVO menuVO) throws Exception {
        Map<String, Object> rtn = new HashMap<>();
        menuVO.setMarkYn("N");
        List<EgovMap> menuList = menuService.getMenuList(menuVO);
        rtn.put("menuList",menuList);
        return ResponseHandler.success(rtn);
    }

    /***
     * 메뉴 정보 들고오기
     * @param request
     * @param model
     * @param menuVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/menu/getMenuInfo", method = RequestMethod.GET)
    public Response menuWrite(HttpServletRequest request, ModelMap model, MenuVO menuVO) throws Exception {
        Map<String, Object> rtn = new HashMap<>();
        EgovMap menuInfo = menuService.getMenuInfo(menuVO);
        rtn.put("menuInfo", menuInfo);
        return ResponseHandler.success(rtn);
    }

    /***
     * 메뉴 분류 0:대분류 1:중분류 2:소분류
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/menu/getMenuClsList", method = RequestMethod.GET)
    public Response getMenuClsList(HttpServletRequest request, ModelMap model) throws Exception {
        Map<String, Object> rtn = new HashMap<>();
        MenuVO vo = new MenuVO();
        vo.setMenuCls(0);
        List<EgovMap> menuClsList0 = menuService.getMenuClsList(vo);
        vo.setMenuCls(1);
        List<EgovMap> menuClsList1 = menuService.getMenuClsList(vo);
        vo.setMenuCls(2);
        List<EgovMap> menuClsList2 = menuService.getMenuClsList(vo);

        rtn.put("menuClsList0", menuClsList0);
        rtn.put("menuClsList1", menuClsList1);
        rtn.put("menuClsList2", menuClsList2);
        return ResponseHandler.success(rtn);
    }

    /***
     * 메뉴 등록
     * @param request
     * @param session
     * @param model
     * @param menuVO
     * @param authentication
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/menu/upsertMenu", method = RequestMethod.POST)
    public Response upsertMenu(HttpServletRequest request, HttpSession session, ModelMap model, MenuVO menuVO) throws Exception {
        List<EgovMap> rtn = new ArrayList<>();
        MenuVO vo = new MenuVO();
        BeanUtils.copyProperties(menuVO, vo);
        vo.setRgtrSn(1L);
        if(StringUtils.isEmpty(vo.getMenuCd())) {
            int menuCls = vo.getMenuCls();
            String menuCd = null;
            int tot = 1;
            int length = Integer.valueOf(vo.getDepthLength())+ 1;
            if (length > 99) {
                tot = 3;
            } else if (length > 9) {
                tot = 2;
            }
            switch (menuCls) {
                case 0:
                    menuCd = "MOR000";
                    menuCd = menuCd.substring(0, menuCd.length() - tot) + length;
                    break;
                case 1:
                    menuCd = "MOT000";
                    menuCd = menuCd.substring(0, menuCd.length() - tot) + length;
                    break;
                case 2:
                default:
                    menuCd = "MOB000";
                    menuCd = menuCd.substring(0, menuCd.length() - tot) + length;
            }
            vo.setMenuCd(menuCd);
        }

        menuService.upsertMenu(vo);
        return ResponseHandler.success(rtn);
    }

    /***
     * 메뉴 삭제
     * @param request
     * @param model
     * @param menuVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/spt/menu/deleteMenu", method = RequestMethod.POST)
    public Response menuView(HttpServletRequest request, ModelMap model, @RequestBody MenuVO menuVO) throws Exception {
        List<EgovMap> rtn = new ArrayList<>();
        menuService.deleteMenu(menuVO);
        return ResponseHandler.success(rtn);
    }
}
