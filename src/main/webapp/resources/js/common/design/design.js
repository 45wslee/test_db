$(document).ready(function () {
    // 달력 1개일 경우 ID and CLASS 상관없이, 2개일경우 CLASS 로 선언
    onDatePicker("only_date");
    onDatePicker("start_date", "end_date");

    // 사이드바 토글메뉴
    $(".list_title").click(function () {
        $(this).next(".sidebar_menu").stop().slideToggle(300);
        $(this).children(".list_arrow").toggleClass("fa-angle-up");

        // 1개씩 펼치기
        $(this).next(".sidebar_menu").siblings(".sidebar_menu").slideUp(300);
    });

    // 메뉴 펼쳐져 있을 경우 오른쪽 아이콘 변경
    $(".sidebar_list_item.on .list_title .list_arrow").addClass("fa-angle-up");
    $(".sidebar_menu_item.on .list_depth .list_arrow").addClass("fa-angle-up");

     // 사이드바 3 depth
     $(".list_depth").click(function () {
        $(this).next(".sidebar_menu_item_depth").stop().slideToggle(300);
        $(this).children(".list_arrow").toggleClass("fa-angle-up");

        // 1개씩 펼치기
        $(this).next(".sidebar_menu_item_depth").siblings(".sidebar_menu_item_depth").slideUp(300);
    });

    // 로딩
    window.onbeforeunload = function () {
        $("#loading").show();
    }; // 현재 페이지에서 다른 페이지로 넘어갈 때 표시해주는 기능

    $("#loading").hide();

    // 관리자 달력 시작일 + timepicker
    if ($(".start_date_time").length != 0) {
        dpMin = new AirDatepicker(".start_date_time", {
            autoClose: true,
            timepicker: true,

            onSelect({ date }) {
                dpMax.update({
                    minDate: date,
                });
            },
        });
    }

    // 관리자 달력 종료일 + timepicker
    if ($(".end_date_time").length != 0) {
        dpMax = new AirDatepicker(".end_date_time", {
            autoClose: true,
            timepicker: true,

            onSelect({ date }) {
                dpMin.update({
                    maxDate: date,
                });
            },
        });
    }

    // 검색창 input에 글자 입력했을 때 x 버튼 나타나게 하기
    $(".search").on("change keyup", function () {
        if ($(this).val().length == 0) {
            $(".fa-circle-xmark").addClass("hidden");
        } else {
            $(".fa-circle-xmark").removeClass("hidden");
        }
    });

    // popup open
    $(".btn_popup").on("click", function () {
        $(".modal_basic").addClass("show");
        $("body").css({ overflow: "hidden" });
    });

    $(".btn_popup_02").on("click", function () {
        $(".modal_wrap_lg").addClass("show");
        $("body").css({ overflow: "hidden" });
    });

    $(".btn_popup_03").on("click", function () {
        $(".modal_wrap_xl").addClass("show");
        $("body").css({ overflow: "hidden" });
    });

    $(".btn_popup_04").on("click", function () {
        $(".modal_wrap_form").addClass("show");
        $("body").css({ overflow: "hidden" });
    });

    $(".btn_popup_05").on("click", function () {
        $(".modal_footer_none_wrap").addClass("show");
        $("body").css({ overflow: "hidden" });
    });

    // popup close
    $(".btn_close").on("click", function () {
        $(".modal_wrap").removeClass("show");
        $("body").css({ overflow: "auto" });
    });

    // 전체 체크
    $("#allSelect").click(function () {
        if ($("#allSelect").prop("checked")) {
            $(".itemChk").prop("checked", true);
        } else {
            $(".itemChk").prop("checked", false);
        }
    });

    // 파일 네임 가져오기
    $("#file").on("change", function () {
        var fileName = $("#file").val();
        $(".upload_name").val(fileName);
    });

    // 드롭다운
    $(".btn_dropdown").on("click", function () {
        $(this).next().stop().slideToggle(300);
    });

    // 드롭다운 바깥 영역 클릭했을 때 닫힘
    $(".btn_dropdown").on("blur", function () {
        setTimeout(() => {
            $(this).next().hide(100);
        }, 200);
    });

    $(".dropdown_item_txt").on("click", function () {
        $(this).parent().prev().text($(this).text());
        $(this).parent().hide(100);
    });

    // 추가 버튼 클릭 시 삭제 html 나타나게 하기
    $(".btn_add").click(function () {
        $(".input_add_group").append('<div class="form_group delete_group"><input type="text" placeholder="Input Text" /><button type="button" class="btn btn_red btn_delete">삭제<i class="fa-solid fa-minus"></i></button></div>');
        $(".btn_delete").on("click", function () {
            $(this).parent().remove();
        });
    });

    // 프로그레스
    $(".progress").each(function () {
        $(this)
            .find(".progress_inner")
            .animate({ width: $(this).attr("data-width") }, 2000);
    });
});

$(document).on("click", ".menu_list_item", function() {
    // 메뉴 관리 페이지
    $(this).next("ul").stop().slideToggle(300);
    $(this).children(".fa-angle-down").toggleClass("fa-angle-up");
});

// 검색창 x 버튼 누를 시 인풋 클리어
var clearInput = function (obj) {
    obj.parentNode.querySelector(".search").value = "";
    $(".fa-circle-xmark").addClass("hidden");
};

// 기본적으로 input의 type이 text일땐, maxlength를 지정해주면 최대 글자 수를 지정 할 수 있지만 number일 경우 그렇지 않습니다. 그래서 이 스크립트를 추가 input에 oninput을 추가해줘야 합니다.
function maxLengthCheck(object) {
    if (object.value.length > object.maxLength) {
        object.value = object.value.slice(0, object.maxLength);
    }
}

// 달력 설정 CLASS로 선언
// 1 개일떄는 only_date로 CLASS 추가
// 2개일떄는 각가 다른 이름으로 CLASS 추가 후 함수 호출

// EX  <input class="only_date" type="text" placeholder="날짜를 선택하세요."/>
//
// EX  <input class="start_date" type="text" placeholder="시작일"/>
//     ~
//     <input class="end_date" type="text" placeholder="종료일"/>
//     -->
//     onDatePicker("start_date", "end_date");
// 단 2개일때의 경우 중 start_date와 end_date는 이미 선언되어 있음으로 class만 추가
let dpMin = {},
    dpMax = {};
function onDatePicker(start, end) {
    if (end == undefined) {
        // 달력 하나일 경우
        if ($("." + start).length != 0) {
            let dateList = $("." + start);

            for (let i = 0; i < dateList.length; i++) {
                let datePicker = new AirDatepicker(dateList[i], {
                    autoClose: true,
                });
                datePicker.selectDate($(dateList[i]).val());
            }
        }
    } else {
        // 관리자 달력 시작일
        if ($("." + start).length != 0) {
            dpMin["." + start] = new AirDatepicker("." + start, {
                autoClose: true,
                onSelect(dp) {
                    dpMax["." + end].update({
                        minDate: dp.date,
                    });
                },
            });
            dpMin["." + start].selectDate($("." + start).val());
        }

        // 관리자 달력 종료일
        if ($("." + end).length != 0) {
            dpMax["." + end] = new AirDatepicker("." + end, {
                autoClose: true,
                onSelect(dp) {
                    dpMin["." + start].update({
                        maxDate: dp.date,
                    });
                },
            });
            dpMax["." + end].selectDate($("." + end).val());
        }
    }
}

// time picker sort up
function inc(element) {
    let el = document.querySelector(`[name="${element}"]`);
    el.value = parseInt(el.value) + 1;
}

// time picker sort down
function dec(element) {
    let el = document.querySelector(`[name="${element}"]`);
    if (parseInt(el.value) > 0) {
        el.value = parseInt(el.value) - 1;
    }
}
