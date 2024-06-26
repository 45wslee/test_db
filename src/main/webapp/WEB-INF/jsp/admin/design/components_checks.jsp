<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<c:import url="/EgovPageLink?link=admin/inc/header"/>
            <!-- s:content -->
            <div class="content_wrap">
                <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Checks And Radios</p>
                <div class="components_group">
                    <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Checks</p>
                    <div class="item_group item_column_group">
                        <div class="checks">
                            <input type="checkbox" id="defaultCheck" />
                            <label for="defaultCheck">Default Checkbox</label>
                        </div>
                        <div class="checks">
                            <input type="checkbox" id="checkedCheck" checked />
                            <label for="checkedCheck">Checked Checkbox</label>
                        </div>
                        <div class="checks">
                            <input type="checkbox" id="disabledCheck" disabled />
                            <label for="disabledCheck">Disabled Checkbox</label>
                        </div>
                        <div class="checks">
                            <input type="checkbox" id="disabledChekedCheck" checked disabled />
                            <label for="disabledChekedCheck">Disabled Checked Checkbox</label>
                        </div>
                    </div>
                </div>
                <div class="components_group">
                    <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Checks Inline</p>
                    <div class="item_group">
                        <div class="checks">
                            <input type="checkbox" id="item_01" />
                            <label for="item_01">Item 01</label>
                        </div>
                        <div class="checks">
                            <input type="checkbox" id="item_02" checked />
                            <label for="item_02">Item 02</label>
                        </div>
                        <div class="checks">
                            <input type="checkbox" id="item_03" disabled />
                            <label for="item_03">Item 03</label>
                        </div>
                        <div class="checks">
                            <input type="checkbox" id="item_04" checked disabled />
                            <label for="item_04">Item 04</label>
                        </div>
                    </div>
                </div>
                <div class="components_group">
                    <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Radios</p>
                    <div class="item_group item_column_group">
                        <div class="radios">
                            <input type="radio" id="defaultRadio" name="radio" />
                            <label for="defaultRadio">Default Radios</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="checkedRadio" name="radio" checked />
                            <label for="checkedRadio">Checked Radios</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="disabledRadio" name="radio" disabled />
                            <label for="disabledRadio">Disabled Radios</label>
                        </div>
                    </div>
                </div>
                <div class="components_group">
                    <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Radios Inline</p>
                    <div class="item_group">
                        <div class="radios">
                            <input type="radio" id="item_05" name="radio_02" />
                            <label for="item_05">Item 01</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="item_06" name="radio_02" checked />
                            <label for="item_06">Item 02</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="item_07" name="radio_02" disabled />
                            <label for="item_07">Item 03</label>
                        </div>
                    </div>
                </div>
                <div class="components_group">
                    <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Switchs</p>
                    <div class="item_group item_column_group">
                        <div class="switchs">
                            <input type="checkbox" id="defaultSwitch" />
                            <label for="defaultSwitch">Default Switch</label>
                        </div>
                        <div class="switchs">
                            <input type="checkbox" id="checkedSwitch" checked />
                            <label for="checkedSwitch">Checked Switch</label>
                        </div>
                        <div class="switchs">
                            <input type="checkbox" id="defaultDisableSwitch" disabled />
                            <label for="defaultDisableSwitch">Disabled Switch</label>
                        </div>
                        <div class="switchs">
                            <input type="checkbox" id="checkedDisableSwitch" checked disabled />
                            <label for="checkedDisableSwitch">Disabled Checked Switch</label>
                        </div>
                    </div>
                </div>
                <div class="components_group">
                    <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Switchs Inline</p>
                    <div class="item_group">
                        <div class="switchs">
                            <input type="checkbox" id="item_08" />
                            <label for="item_08">Item 01</label>
                        </div>
                        <div class="switchs">
                            <input type="checkbox" id="item_09" checked />
                            <label for="item_09">Item 02</label>
                        </div>
                        <div class="switchs">
                            <input type="checkbox" id="item_10" disabled />
                            <label for="item_10">Item 03</label>
                        </div>
                        <div class="switchs">
                            <input type="checkbox" id="item_11" checked disabled />
                            <label for="item_11">Item 04</label>
                        </div>
                    </div>
                </div>
                <!-- s:footer -->
                <c:import url="/EgovPageLink?link=admin/inc/footer"/>
                <!-- e:footer -->
            </div>
            <!-- e:content -->
        </div>
    </body>
</html>
