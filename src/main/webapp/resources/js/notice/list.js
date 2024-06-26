let key = 'notice';
$(document).ready(function () {
    // 새로고침을 했을 때 검색조건 유지
    let objKeys = ['size', 'searchCnd', 'searchWrd'];
    setSearchCondition(objKeys);

    // 초기 데이터 가져오기
    setLocationHash(1);
    getNoticeList();

    // 뒤로가기 이벤트 등록
    historyBack(getNoticeList);
});

function setSearchCondition(objKeys) {
    if(Array.isArray(objKeys)) {
        let noticeSearch = getSessionItem(key, objKeys);
        for(let i=0; i < objKeys.length; i++) {
            let objKey = objKeys[i];
            if(noticeSearch[objKey] !== null) {
                document.frm[objKey].value = noticeSearch[objKey];
            }
        }
    }
}

function getNoticeList() {
    let pageNo = getPageNoFromHash();
    // spring-data 에서 제공하는 page 는 index 가 0부터 시작
    document.frm.page.value = pageNo - 1;
    callAjaxPost("/notice", 'successCallback', 'frm');
}

function searchNotice(pageNo) {
    setLocationHash(pageNo)

    let noticeSearch = {
        "size" : document.frm.size.value,
        "searchCnd" : document.frm.searchCnd.value,
        "searchWrd" : document.frm.searchWrd.value,
    }
    setSessionObject(key, noticeSearch);
    getNoticeList();
}

function successCallback(res) {
    let tbody = '';
    let pagination = '';
    if (res.content.length === 0) {
        tbody = "<tr><td colspan='4'>데이터가 없습니다.</td></tr>";
        pagination = '';
    } else {
        tbody = $('#noticeTmpl').tmpl(res);
        pagination = springPagination.getPaginationTag(res, 'setLocationHash');
    }

    $("#noticeListTbody").html(tbody);
    $("#pagination").html(pagination);
}