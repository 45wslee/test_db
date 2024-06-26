$(document).ready(function () {
    // 차트 그릴때
    // mainChart();
});

var barChart;
var lineChart;
var doughnutChart;
var pieChart;
var fontFamily = "SUIT";
var fontWeight = 500;

function mainChart() {
    // bar chart
    let config_bar = {
        type: "bar",
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            datasets: [
                {
                    label: "label 01",
                    data: [200, 150, 140, 50, 70, 70, 120, 210, 230, 30, 78, 172],
                    borderColor: "rgb(0 56 255 / 50%",
                    backgroundColor: "rgb(0 56 255 / 50%)",
                    hoverBackgroundColor: "rgb(0 56 255 / 50%)",
                },
                {
                    label: "label 02",
                    data: [56, 100, 70, 23, 150, 132, 32, 89, 90, 150, 132, 45],
                    borderColor: "rgb(158 175 255 / 50%)",
                    backgroundColor: "rgb(158 175 255 / 50%)",
                    hoverBackgroundColor: "rgb(158 175 255 / 50%)",
                },
            ],
        },
        options: {
            responsive: true,
            borderRadius: 15,
            maxBarThickness: 15,
            scales: {
                x: {
                    grid: {
                        display: false,
                    },
                    ticks: {
                        font: {
                            family: fontFamily,
                            weight: fontWeight,
                        },
                    },
                },
                y: {
                    grid: {
                        color: "#e5e5e5",
                    },
                    ticks: {
                        font: {
                            family: fontFamily,
                            weight: fontWeight,
                        },
                    },
                },
            },
            plugins: {
                tooltip: {
                    padding: 10,
                    titleFont: {
                        family: fontFamily,
                        size: 14,
                        weight: fontWeight,
                    },
                    bodyFont: {
                        family: fontFamily,
                    },
                    boxPadding: 5,
                    mode: "index",
                    intersect: false,
                    usePointStyle: true,
                    callbacks: {
                        labelPointStyle: function (context) {
                            return {
                                pointStyle: "rectRounded",
                            };
                        },
                    },
                },
                legend: {
                    position: "bottom",
                    labels: {
                        padding: 20,
                        usePointStyle: true,
                        pointStyle: "rectRounded",
                        borderWidth: 0,
                        font: {
                            family: fontFamily,
                            size: 16,
                            weight: fontWeight,
                        },
                    },
                },
            },
        },
    };

    // line chart
    let config_line = {
        type: "line",
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            datasets: [
                {
                    label: "label 01",
                    data: [200, 150, 140, 50, 70, 70, 120, 210, 230, 30, 78, 172],
                    borderColor: "rgb(0 56 255 / 50%)",
                    hoverborderColor: "rgb(0 56 255 / 50%)",
                    backgroundColor: "rgb(0 56 255 / 50%)",
                    hoverBackgroundColor: "rgb(0 56 255 / 50%)",
                },
                {
                    label: "label 02",
                    data: [56, 100, 70, 23, 150, 132, 32, 89, 90, 150, 132, 45],
                    borderColor: "rgb(158 175 255 / 50%)",
                    hoverborderColor: "rgb(158 175 255 / 50%)",
                    backgroundColor: "rgb(158 175 255 / 50%)",
                    hoverBackgroundColor: "rgb(158 175 255 / 50%)",
                },
            ],
        },
        options: {
            responsive: true,
            scales: {
                x: {
                    grid: {
                        display: false,
                    },
                    ticks: {
                        font: {
                            family: fontFamily,
                            weight: fontWeight,
                        },
                    },
                },
                y: {
                    grid: {
                        color: "#e5e5e5",
                    },
                    ticks: {
                        font: {
                            family: fontFamily,
                            weight: fontWeight,
                        },
                    },
                },
            },
            plugins: {
                tooltip: {
                    padding: 10,
                    titleFont: {
                        family: fontFamily,
                        size: 14,
                        weight: fontWeight,
                    },
                    bodyFont: {
                        family: fontFamily,
                    },
                    boxPadding: 5,
                    mode: "index",
                    intersect: false,
                    usePointStyle: true,
                    callbacks: {
                        labelPointStyle: function (context) {
                            return {
                                pointStyle: "rectRounded",
                            };
                        },
                    },
                },
                legend: {
                    position: "bottom",
                    labels: {
                        padding: 20,
                        usePointStyle: true,
                        pointStyle: "rectRounded",
                        borderWidth: 0,
                        font: {
                            family: fontFamily,
                            size: 16,
                            weight: fontWeight,
                        },
                    },
                },
            },
        },
    };

    // doughnut chart
    let config_doughnut = {
        type: "doughnut",
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr"],
            datasets: [
                {
                    data: [40, 30, 20, 10],
                    backgroundColor: ["rgb(50 64 255 / 70%)", "rgb(0 56 255 / 50%)", "rgb(60 145 255 / 60%)", "rgb(60 145 255 / 30%)"],
                    hoverBackgroundColor: ["rgb(50 64 255 / 70%)", "rgb(0 56 255 / 50%)", "rgb(60 145 255 / 60%)", "rgb(60 145 255 / 30%)"],
                    borderWidth: 0,
                },
            ],
        },
        options: {
            responsive: true,
            plugins: {
                tooltip: {
                    padding: 10,
                    titleFont: {
                        family: fontFamily,
                        size: 14,
                        weight: fontWeight,
                    },
                    bodyFont: {
                        family: fontFamily,
                    },
                    boxPadding: 5,
                    usePointStyle: true,
                    callbacks: {
                        labelPointStyle: function (context) {
                            return {
                                pointStyle: "rectRounded",
                            };
                        },
                    },
                },
                legend: {
                    position: "bottom",
                    labels: {
                        padding: 20,
                        usePointStyle: true,
                        pointStyle: "rectRounded",
                        borderWidth: 0,
                        font: {
                            family: fontFamily,
                            size: 16,
                            weight: fontWeight,
                        },
                    },
                },
                datalabels: {
                    formatter: (value) => {
                        return value;
                    },
                    color: "#fff",
                    font: {
                        family: fontFamily,
                        size: 18,
                        weight: 700,
                    },
                },
                doughnutlabel: {
                    labels: [
                        {
                            text: "first line",
                            font: {
                                family: fontFamily,
                                size: 16,
                                weight: 700,
                            },
                        },
                        {
                            text: "total",
                            font: {
                                family: fontFamily,
                                size: 16,
                                weight: 700,
                            },
                        },
                    ],
                },
            },
        },
        plugins: [ChartDataLabels],
    };

    // pie chart
    let config_pie = {
        type: "pie",
        data: {
            labels: ["Jan", "Feb", "Mar", "Apr"],
            datasets: [
                {
                    data: [40, 30, 20, 10],
                    backgroundColor: ["rgb(50 64 255 / 70%)", "rgb(0 56 255 / 50%)", "rgb(60 145 255 / 60%)", "rgb(60 145 255 / 30%)"],
                    hoverBackgroundColor: ["rgb(50 64 255 / 70%)", "rgb(0 56 255 / 50%)", "rgb(60 145 255 / 60%)", "rgb(60 145 255 / 30%)"],
                    borderWidth: 0,
                },
            ],
        },
        options: {
            responsive: true,
            plugins: {
                tooltip: {
                    padding: 10,
                    titleFont: {
                        family: fontFamily,
                        size: 14,
                        weight: fontWeight,
                    },
                    bodyFont: {
                        family: fontFamily,
                    },
                    boxPadding: 5,
                    usePointStyle: true,
                    callbacks: {
                        labelPointStyle: function (context) {
                            return {
                                pointStyle: "rectRounded",
                            };
                        },
                    },
                },
                legend: {
                    position: "bottom",
                    labels: {
                        padding: 20,
                        usePointStyle: true,
                        pointStyle: "rectRounded",
                        borderWidth: 0,
                        font: {
                            family: fontFamily,
                            size: 16,
                            weight: fontWeight,
                        },
                    },
                },
                datalabels: {
                    formatter: (value) => {
                        return value;
                    },
                    color: "#fff",
                    font: {
                        family: fontFamily,
                        size: 18,
                        weight: 700,
                    },
                },
            },
        },
        plugins: [ChartDataLabels],
    };

    barChart = new Chart("barChart", config_bar);
    lineChart = new Chart("lineChart", config_line);
    doughnutChart = new Chart("doughnutChart", config_doughnut);
    pieChart = new Chart("pieChart", config_pie);
}
