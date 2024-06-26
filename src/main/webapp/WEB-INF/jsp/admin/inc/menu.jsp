<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="requestUrl" value="${fn:split(pageContext.request.requestURL, '/') }"/>
<c:set var="menuLevel0" value="${requestUrl[fn:length(requestUrl)-3]}"/>
<c:set var="menuLevel1" value="${requestUrl[fn:length(requestUrl)-3]}"/>
<c:set var="menuLevel2" value="${requestUrl[fn:length(requestUrl)-2]}"/>
<c:set var="menuLevel3" value="${requestUrl[fn:length(requestUrl)-1]}"/>

<ul class="sidebar_list_wrap">
    <%--<li class="sidebar_small_cap sidebar_cap_first">
        <p>
            <span class="title">HOME</span>
            <span class="line"></span>
        </p>
    </li>--%>

    <c:forEach var="menu" items="${menuList}" varStatus="status">
        <c:set var="nextVal" value="${menuList[status.count]}"/>
        <c:if test="${empty menu.parntMenuCd}">
            <li class="sidebar_list_item">
            <c:if test="${nextVal.depth == 0 || nextVal == null}">
                 <!-- list_title_only : 하나일때 추가-->
                <a onclick="goPage('${menu.menuUrl}')" class="list_title list_title_only"><i class="${menu.menuIconCls }"></i>${menu.menuNm}</a>
            </c:if>
            <c:if test="${nextVal.depth != 0 && nextVal != null}">
                <div class="list_title"> <!-- list_title_only : 하나일때 추가-->
                <a href="javascript:void(0)"><i class="${menu.menuIconCls }"></i>${menu.menuNm}</a>
            </c:if>
                <c:if test="${nextVal.depth == 0 || nextVal == null}">
                    <i class="fa-solid"></i> <!-- fa-angle-down list_arrow : 하나일때 제거 -->
                </c:if>
                <c:if test="${nextVal.depth != 0 && nextVal != null}">
                    <i class="fa-solid fa-angle-down list_arrow"></i> <!-- list_arrow : 하나일때 제거 -->
                </c:if>
            <c:if test="${nextVal.depth != 0 && nextVal != null}">
                </div>
            </c:if>
            <c:if test="${nextVal.depth != 0 && nextVal != null}">
                <ol class="sidebar_menu">
            </c:if>
        </c:if>
        <c:if test="${not empty menu.parntMenuCd}">
            <c:if test="${menu.depth == 1 }">
            <li class="sidebar_menu_item">
                <a 
                <c:if test="${nextVal.depth == 2 && nextVal != null}">
                class="list_depth"
                </c:if>
                <c:if test="${nextVal.depth != 2 && nextVal != null}">
                onclick="goPage('${menu.menuUrl}')"
                </c:if>
                ><i class="fa-solid fa-circle"></i>${menu.menuNm}
                <c:if test="${nextVal.depth == 2 && nextVal != null}">
                    <i class="fa-solid fa-angle-down list_arrow"></i>
                </c:if>
                </a>
                <c:if test="${nextVal.depth == 2}">
                    <ul class="sidebar_menu_item_depth">
                </c:if>
            </c:if>
            <c:if test="${menu.depth == 2}">
                 <li>
                      <a onclick="goPage('${menu.menuUrl}')">${menu.menuNm}</a>
                  </li>
            </c:if>
            <c:if test="${nextVal.depth == 0 || nextVal == null}">
            </li>
            </c:if>
        </c:if>
        <c:if test="${nextVal.depth == 0 || nextVal == null}">
            </ol>
            </li>
        </c:if>
    </c:forEach>

    <li class="sidebar_list_item <c:if test = "${fn:contains(menuLevel3, 'dashboard')}">on</c:if>">
        <a onclick="goPage('/spt/dashboard')" class="list_title list_title_only"> <i class="fa-solid fa-house"></i>Dashboard </a>
    </li>
    <%--<li class="sidebar_small_cap">
        <p>
            <span class="title">LAYOUTS & PAGES</span>
            <span class="line"></span>
        </p>
    </li>--%>
    <li class="sidebar_list_item <c:if test = "${fn:contains(menuLevel3, 'table')}"> on</c:if>">
        <div class="list_title">
            <a href="#"><i class="fa-solid fa-layer-group"></i>Pages</a>
            <i class="fa-solid fa-angle-down list_arrow"></i>
        </div>
        <ol class="sidebar_menu <c:if test = "${fn:contains(menuLevel3, 'table')}">show</c:if>">
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'tableList')}">on</c:if>">
                <a onclick="goPage('/table/tableList')"><i class="fa-solid fa-circle"></i>Table List</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'tableForm')}">on</c:if>">
                <a onclick="goPage('/table/tableForm')"><i class="fa-solid fa-circle"></i>Table Form</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'tableEditorForm')}">on</c:if>">
                <a onclick="goPage('/table/tableEditorForm')"><i class="fa-solid fa-circle"></i>Table Editor Form</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'tableView')}">on</c:if>">
                <a onclick="goPage('/table/tableView')"><i class="fa-solid fa-circle"></i>Table View</a>
            </li>
        </ol>
    </li>
    <li class="sidebar_list_item <c:if test = "${fn:contains(menuLevel3, 'authentication')}">on</c:if>">
        <div class="list_title">
            <a href="#"><i class="fa-solid fa-lock"></i>Authentication</a>
            <i class="fa-solid fa-angle-down list_arrow"></i>
        </div>
        <ol class="sidebar_menu <c:if test = "${fn:contains(menuLevel3, 'authentication')}">show</c:if>">
            <li class="sidebar_menu_item">
                <a href="auth_login.html"><i class="fa-solid fa-circle"></i>Login</a>
            </li>
            <li class="sidebar_menu_item">
                <a href="auth_regist_agree.html"><i class="fa-solid fa-circle"></i>Regist</a>
            </li>
            <li class="sidebar_menu_item">
                <a href="auth_id_find.html"><i class="fa-solid fa-circle"></i>Id Find</a>
            </li>
            <li class="sidebar_menu_item">
                <a href="auth_password_find.html"><i class="fa-solid fa-circle"></i>Password Find</a>
            </li>
            <li class="sidebar_menu_item">
                <a href="auth_id_find_complete.html"><i class="fa-solid fa-circle"></i>Id Complete</a>
            </li>
            <li class="sidebar_menu_item">
                <a href="auth_password_find_complete.html"><i class="fa-solid fa-circle"></i>Password Complete</a>
            </li>
        </ol>
    </li>
    <%--<li class="sidebar_small_cap">
        <p>
            <span class="title">UI COMPONENTS</span>
            <span class="line"></span>
        </p>
    </li>--%>

    <li class="sidebar_list_item <c:if test = "${fn:contains(menuLevel3, 'components')}">on</c:if>">
        <div class="list_title">
            <a href="#"><i class="fa-solid fa-display"></i>Components</a>
            <i class="fa-solid fa-angle-down list_arrow"></i>
        </div>
        <ol class="sidebar_menu <c:if test = "${fn:contains(menuLevel3, 'components')}">show</c:if>">
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'badges')}">on</c:if>">
                <a onclick="goPage('/spt/components/badges')"><i class="fa-solid fa-circle"></i>Badges</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'buttons')}">on</c:if>">
                <a onclick="goPage('/spt/components/buttons')"><i class="fa-solid fa-circle"></i>Buttons</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'checks')}">on</c:if>">
                <a onclick="goPage('/spt/components/checks')"><i class="fa-solid fa-circle"></i>Checks And Radios</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'draggable')}">on</c:if>">
                <a onclick="goPage('/spt/components/draggable')"><i class="fa-solid fa-circle"></i>Draggable</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'icons')}">on</c:if>">
                <a onclick="goPage('/spt/components/icons')"><i class="fa-solid fa-circle"></i>Icons</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'inputs')}">on</c:if>">
                <a onclick="goPage('/spt/components/inputs')"><i class="fa-solid fa-circle"></i>Inputs</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'modals')}">on</c:if>">
                <a onclick="goPage('/spt/components/modals')"><i class="fa-solid fa-circle"></i>Modals</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'swiper')}">on</c:if>">
                <a onclick="goPage('/spt/components/swiper')"><i class="fa-solid fa-circle"></i>Swiper</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'tooltip')}">on</c:if>">
                <a onclick="goPage('/spt/components/tooltip')"><i class="fa-solid fa-circle"></i>Tooltip And Popover</a>
            </li>
            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'typography')}">on</c:if>">
                <a onclick="goPage('/spt/components/typography')"><i class="fa-solid fa-circle"></i>Typography</a>
            </li>
        </ol>
    </li>
</ul>