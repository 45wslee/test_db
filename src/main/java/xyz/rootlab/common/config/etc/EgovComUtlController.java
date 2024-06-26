package xyz.rootlab.common.config.etc;

import lombok.RequiredArgsConstructor;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.app.menu.service.MenuService;
import xyz.rootlab.app.menu.vo.MenuVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 공통유틸리티성 작업을 위한 Controller 클래스
 *
 * @author 공통 서비스 개발팀 JJY
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일               수정자            수정내용
 *  ----------   --------   ---------------------------
 *  2009.03.02   JJY        최초 생성
 *  2011.08.31   JJY        경량환경 템플릿 커스터마이징버전 생성
 *  2021.02.23   신용호            moveToPage() 화이트리스트 처리
 *
 *  </pre>
 * @since 2009.03.02
 */
@Controller
@RequiredArgsConstructor
public class EgovComUtlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovComUtlController.class);

    @Resource(name = "egovPageLinkWhitelist")
    protected List<String> egovWhitelist;

    private final MenuService menuService;

    /**
     * JSP 호출작업만 처리하는 공통 함수
     */
    @RequestMapping(value = "/EgovPageLink")
    public String moveToPage(@RequestParam("link") String linkPage,
                             HttpSession session,
                             @RequestParam(value = "menuNo", required = false) String menuNo, ModelMap model) {

        String link = linkPage;
        link = link.replace(";", "");
        link = link.replace(".", "");

        // service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
        if (linkPage.isEmpty()) {
            link = "cmm/error/egovError";
        } else {
            if (link.contains(",")) {
                link = link.substring(0, link.indexOf(","));
            }
        }

        // 선택된 메뉴정보를 세션으로 등록한다.
        if (menuNo != null && !menuNo.equals("")) {
            session.setAttribute("menuNo", menuNo);
        }

        // 화이트 리스트 처리
        // whitelist목록에 있는 경우 결과가 true, 결과가 false인경우 FAIL처리
        if (!egovWhitelist.contains(link)) {
            LOGGER.debug("Page Link WhiteList Error! Please check whitelist!" + link);
            link = "cmm/error/egovError";
        }
        // 안전한 경로 문자열로 조치
        link = EgovWebUtil.filePathBlackList(link);

        if (link.contains("menu")) {
            try {
                MenuVO menuVO = new MenuVO();
                menuVO.setMarkYn("Y");
                Member loginUser = (Member) session.getAttribute("loginVO");
                menuVO.setMbrAuth(loginUser.getMbrAuth());
                List<EgovMap> menuList = menuService.getMenuList(menuVO);
                model.addAttribute("menuList", menuList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return link;
    }

    /**
     * validation rule dynamic java script
     */
    @RequestMapping(value = "/validator.do", method = RequestMethod.GET)
    public String validate() {
        return "cmm/validator";
    }

}