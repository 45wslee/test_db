let frm = document.frmHst;
let gb_historyState = "";   // pushstate값 저장

$(async function () {
    //검색창 엔터키 동작
    let searchWrd1 = document.getElementById('searchWrd1');
    searchWrd1.addEventListener('keyup', event => submitTextarea(event, 'getListBeforeProcess'));

    let pageIndex = 1;

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
});

async function getListBeforeProcess(pageIndex) {
    let param = {
        pageIndex : pageIndex,
        searchWrd1: $("#searchWrd1").val()
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
    let res = await fetchGet("/spt/auth/userAuthList", param);

    let pagination;
    if (res.code === 1000) {
        console.log(res)
        let list = '';
        if (res.data.resultList.length > 0) {
            let jsonData = res.data;
            list = $("#designListTmpl").tmpl(jsonData);
            pagination = egovPagination.getPaginationTag(jsonData.paginationInfo, 'getListBeforeProcess');
        } else {
            if (res.data.fixList.length > 0) {
                list = $("#designListTmpl").tmpl(res.data);
            } else {
                list = '<tr><td colspan="9">데이터가 없습니다.</td></tr>';
            }
            pagination = '';
        }

        $("#designList").html(list);
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
        $("#searchWrd1").val("");
        return;
    }

    // 목록 개수
    $("#recordCountPerPage").val(param.recordCountPerPage)
    // 검색어
    $("#searchWrd1").val(param.searchWrd1);
}

// -------------- 권한 상세보기 ------------------
function detailView(roleCd) {
    if(roleCd !== '') {
        goPage('/spt/auth/authView/'+roleCd)
    } else {
        goPage('/spt/auth/authWrite')
    }
}
