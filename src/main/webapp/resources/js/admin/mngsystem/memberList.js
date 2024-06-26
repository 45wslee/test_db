let frm = document.frmHst;
let gb_historyState = "";   // pushstate값 저장

$(async function () {
    //검색창 엔터키 동작
    let searchWrd = document.getElementById('searchWrd');
    searchWrd.addEventListener('keyup', event => submitTextarea(event, 'getListBeforeProcess'));

    let pageIndex = 1;
    console.log("document function start")

    // 새로고침 또는 목록에서 리스트로 복귀 시 기존 파라메터 반영
    if (window.location.search) {
        let paramObj = convertParamReverse(window.location.href);
        searchParamRecovery(paramObj); //파라미터 유지
        // pageIndex 업데이트
        if (paramObj.pageIndex !== "" && paramObj.pageIndex != null) {
            pageIndex = paramObj.pageIndex;
        }
    }

    await getListBeforeProcess(pageIndex);
    // fetchPostForm("/api/table/tableList", 'frm').then((data) => console.log(data));

    // 아래는 일반 목록 처리
    // 뒤로가기로 페이지 로드 시
    History.Adapter.bind(window, "statechange", function () {
        let url = History.getState().url;
        let paramObj = convertParamReverse(url);
        searchParamRecovery(paramObj); // 파라미터 유지
        getList(paramObj);
    });

    console.log("document function end")
});

async function getListBeforeProcess(pageIndex) {
    let param = {
        pageIndex         : pageIndex,
        recordCountPerPage: $("#recordCountPerPage option:selected").val(),
        searchCnd1        : $("#searchCnd1").val(),
        searchWrd        : $("#searchWrd").val()
    };

    let paramStr = convertParam(param);

    // 'statechange' 일 경우 flag 업데이트
    let skipFlag = false;
    if (gb_historyState !== "" && gb_historyState !== paramStr) {
        skipFlag = true;
    }

    // pushState를 이용한 state 저장
    gb_historyState = paramStr;
    if (location.search !== null && location.search !== '') {
        History.pushState(null, document.title, paramStr);
    } else {
        History.replaceState(null, document.title, paramStr);
    }

    // pushstate로 변경 사항이 발생한 경우는 'statechange' 이벤트에서 처리되기에 ajax 요청 skip
    if (!skipFlag) {
        await getList(param);
    }
}

async function getList(param) {
    let res = await fetchGet("/spt/mngSystem/getMemberList", param);

    let pagination;
    if (res.code === 1000) {
        console.log(res)
        let list = '';
        if (res.data.resultList.length > 0) {
            let jsonData = res.data;
            list = $("#memberListTmpl").tmpl(jsonData);
            pagination = egovPagination.getPaginationTag(jsonData.paginationInfo, 'getListBeforeProcess');
        } else {
            list = '<tr><td colspan="6">데이터가 없습니다.</td></tr>';
            pagination = '';
        }

        $("#memberList").html(list);
        $("#totalCnt").html(res.data.totalCnt);
        $("#pagination").html(pagination);
    } else {
        await sweetAlert(res.msg)
    }
}

// 새로고침 또는 뒤로가기 시 검색 조건 유지
function searchParamRecovery(param) {
    // null check
    if (isEmpty(param)) {
        $("#recordCountPerPage").eq(0).prop("selected", true);
        $("#searchCnd1").val("0");
        $("#searchWrd").val("");
        return;
    }

    // 목록 개수
    $("#recordCountPerPage").val(param.recordCountPerPage)

    // 검색 조건
    $("#searchCnd1").val(param.searchCnd1);

    // 검색어
    $("#searchWrd").val(param.searchWrd);
}

function detailView(sn) {
    console.log(sn)
}