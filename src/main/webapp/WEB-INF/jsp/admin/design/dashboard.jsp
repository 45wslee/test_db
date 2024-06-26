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
    <p class="content_header_title">Dashboard</p>
    <div class="chart_wrap grid_2">
        <div class="chart_item">
            <p class="heading">Bar Chart</p>
            <canvas id="barChart"></canvas>
        </div>
        <div class="chart_item">
            <p class="heading">Line Chart</p>
            <canvas id="lineChart"></canvas>
        </div>
    </div>
    <div class="chart_wrap grid_3 mt15">
        <div class="chart_item">
            <p class="heading">Doughnut Chart</p>
            <canvas id="doughnutChart"></canvas>
        </div>
        <div class="chart_item">
            <p class="heading">Pie Chart</p>
            <canvas id="pieChart"></canvas>
        </div>
        <div class="chart_item">
            <p class="heading">Progress Chart</p>
            <div class="progress_wrap">
                <div class="progress_item">
                    <p class="text_title">label_01 <span class="text_blue">10.13%</span></p>
                    <div class="progress" data-width="10.13%">
                        <div class="progress_inner"></div>
                    </div>
                </div>
                <div class="progress_item">
                    <p class="text_title">label_02 <span class="text_blue">20.26%</span></p>
                    <div class="progress" data-width="20.26%">
                        <div class="progress_inner"></div>
                    </div>
                </div>
                <div class="progress_item">
                    <p class="text_title">label_03 <span class="text_blue">30.39%</span></p>
                    <div class="progress" data-width="30.39%">
                        <div class="progress_inner"></div>
                    </div>
                </div>
                <div class="progress_item">
                    <p class="text_title">label_04 <span class="text_blue">40.26%</span></p>
                    <div class="progress" data-width="40.26%">
                        <div class="progress_inner"></div>
                    </div>
                </div>
                <div class="progress_item">
                    <p class="text_title">label_05 <span class="text_blue">50.39%</span></p>
                    <div class="progress" data-width="50.39%">
                        <div class="progress_inner"></div>
                    </div>
                </div>
                <div class="progress_item">
                    <p class="text_title">label_06 <span class="text_blue">60.13%</span></p>
                    <div class="progress" data-width="60.13%">
                        <div class="progress_inner"></div>
                    </div>
                </div>
                <div class="progress_item">
                    <p class="text_title">label_07 <span class="text_blue">70.26%</span></p>
                    <div class="progress" data-width="70.26%">
                        <div class="progress_inner"></div>
                    </div>
                </div>
                <div class="progress_item">
                    <p class="text_title">label_08 <span class="text_blue">80.39%</span></p>
                    <div class="progress" data-width="80.39%">
                        <div class="progress_inner"></div>
                    </div>
                </div>
                <div class="progress_item">
                    <p class="text_title">label_09 <span class="text_blue">90.13%</span></p>
                    <div class="progress" data-width="90.13%">
                        <div class="progress_inner"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<!-- e:content -->
</body>
</html>
<script>
    $(function(){
        mainChart();
    });
</script>