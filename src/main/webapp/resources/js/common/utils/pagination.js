let egovPagination = {
    /* paginationInfo 설정 */
    setPaginationInfo : function(paginationInfo) {
        let firstPageNoOnPageList = Math.floor((paginationInfo.currentPageNo - 1) / paginationInfo.pageSize) * paginationInfo.pageSize + 1;
        let lastPageNoOnPageList = firstPageNoOnPageList + paginationInfo.pageSize - 1;
        let totalPageCount = Math.floor((paginationInfo.totalRecordCount - 1) / paginationInfo.recordCountPerPage) + 1;

        if (lastPageNoOnPageList > totalPageCount) {
            lastPageNoOnPageList = totalPageCount;
        }
        paginationInfo.firstPageNoOnPageList = firstPageNoOnPageList;
        paginationInfo.lastPageNoOnPageList = lastPageNoOnPageList;
        paginationInfo.totalPageCount = totalPageCount;
        return paginationInfo;
    },

    /* AbstractPaginationRenderer 내에 있는 renderPagination 메소드와 동일 -  */
    getPaginationTag : function(paginationInfo, callbackFunc) {
        // if(!isFunction(callbackFunc)) {
        //     return '';
        // }
        let temp = '';
        paginationInfo = this.setPaginationInfo(paginationInfo);
        if (paginationInfo.totalPageCount > paginationInfo.pageSize) {
            if (paginationInfo.firstPageNoOnPageList > paginationInfo.pageSize) {
                temp += '<a title="맨 처음 페이지" onclick="' + callbackFunc + '(1);return false;"><i class="fa-solid fa-angles-left"></i></a>';
                temp += '<a title="이전 페이지" onclick="' + callbackFunc + '(' + (paginationInfo.firstPageNoOnPageList - 1) + ');return false;"><i class="fa-solid fa-angle-left"></i></a>';
            } else {
                temp += '<a title="맨 처음 페이지" onclick="' + callbackFunc + '(1);return false;"><i class="fa-solid fa-angles-left"></i></a>';
                temp += '<a title="이전 페이지" onclick="' + callbackFunc + '(1);return false;"><i class="fa-solid fa-angle-left"></i></a>';
            }
        }

        for (let i = paginationInfo.firstPageNoOnPageList; i <= paginationInfo.lastPageNoOnPageList; i++) {
            if (i === paginationInfo.currentPageNo) {
                temp += '<a title="선택된 페이지" class="on" onclick="' + callbackFunc + '(' + i + ')">' + i + '</a>';
            } else {
                temp += '<a onclick="' + callbackFunc + '(' + i + ')">' + i + '</a>';
            }
        }

        if (paginationInfo.totalPageCount > paginationInfo.pageSize) {
            if (paginationInfo.lastPageNoOnPageList < paginationInfo.totalPageCount) {
                temp += '<a title="다음 페이지" onclick="' + callbackFunc + '(' + (firstPageNoOnPageList + paginationInfo.pageSize) + ');return false;"><i class="fa-solid fa-angle-right"></i></a>';
                temp += '<a title="맨끝 페이지" onclick="' + callbackFunc + '(' + (totalPageCount) + ');return false;"><i class="fa-solid fa-angles-right"></i></a>';
            } else {
                temp += '<a title="다음 페이지" onclick="' + callbackFunc + '(' + (totalPageCount) + ');return false;"><i class="fa-solid fa-angle-right"></i></a>';
                temp += '<a title="맨끝 페이지" onclick="' + callbackFunc + '(' + (totalPageCount) + ');return false;"><i class="fa-solid fa-angles-right"></i></a>';
            }
        }
        return temp;
    }
}

let springPagination = {
    getPaginationTag : function(res, callbackFunc) {
        if(!isFunction(callbackFunc)) {
            return '';
        }
        let temp = '';
        let pageSize = 10;
        let firstPageNoOnPageList = Math.floor((res.number) / pageSize) * pageSize + 1;
        let lastPageNoOnPageList = firstPageNoOnPageList + pageSize - 1;

        if (lastPageNoOnPageList > res.totalPages) {
            lastPageNoOnPageList = res.totalPages;
        }

        let contextPath = $('#contextPath').val();
        // if (res.totalPages > pageSize) {
            if (firstPageNoOnPageList > pageSize) {
                temp += '<a title="맨 처음 페이지" onclick="' + callbackFunc + '(1);return false;"><i class="fa-solid fa-angles-left"></i></a>';
                temp += '<a title="이전 페이지" onclick="' + callbackFunc + '(' + (firstPageNoOnPageList - 1) + ');return false;"><i class="fa-solid fa-angle-left"></i></a>';
            } else {
                temp += '<a title="맨 처음 페이지" onclick="' + callbackFunc + '(1);return false;"><i class="fa-solid fa-angles-left"></i></a>';
                temp += '<a title="이전 페이지" onclick="' + callbackFunc + '(1);return false;"><i class="fa-solid fa-angle-left"></i></a>';
            }
        // }

        for (let i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
            if (i === res.number + 1) {
                temp += '<a title="선택된 페이지" class="on" onclick="' + callbackFunc + '(' + i + ')">' + (i) + '</a>';
            } else {
                temp += '<a onclick="' + callbackFunc + '(' + i + ')">' + (i) + '</a>';
            }
        }

        // if (res.totalPages > pageSize) {
            if (lastPageNoOnPageList < res.totalPages) {
                temp += '<a title="다음 페이지" onclick="' + callbackFunc + '(' + (firstPageNoOnPageList + pageSize) + ');return false;"><i class="fa-solid fa-angle-right"></i></a>';
                temp += '<a title="맨끝 페이지" onclick="' + callbackFunc + '(' + (res.totalPages) + ');return false;"><i class="fa-solid fa-angles-right"></i></a>';
            } else {
                temp += '<a title="다음 페이지" onclick="' + callbackFunc + '(' + (res.totalPages) + ');return false;"><i class="fa-solid fa-angle-right"></i></a>';
                temp += '<a title="맨끝 페이지" onclick="' + callbackFunc + '(' + (res.totalPages) + ');return false;"><i class="fa-solid fa-angles-right"></i></a>';
            }
        // }
        return temp;
    }
}