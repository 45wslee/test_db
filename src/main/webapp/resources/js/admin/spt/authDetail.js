let frm = document.frmHst;
let gb_historyState = "";   // pushstate값 저장
let authInfo
$(function () {
});


authInfo = new Vue({
    el     : ".content_wrap",
    data   : {
        depthList : [],
        menuDepth1: [],
        menuDepth2: [],
        depth2    : [],
        chkArray  : [],
        authrtInfo: {}
    },
    created: async function () {
        authInfo = this
        let param = {
            roleCd: document.getElementById('roleCd').value
        }
        let res = await fetchGet('/spt/auth/getUserAuthDetail', param)

        if (res.code === 1000) {
            let data = res.data
            authInfo.depthList = data.resultList
            if (data.resultInfo != null) {
                authInfo.authrtInfo = data.resultInfo;
            }

            authInfo.menuDepth1 = data.menuDepth1
            authInfo.menuDepth2 = data.menuDepth2

            if (data.resultInfo !== null) {
                // 선택한 권한 정보
                data.resultInfo.detail.forEach(function (item, index) {
                    authInfo.chkArray.push(item.menuCd)
                })
            }
            setTimeout(function () {
                calculateRowspan()
            }, 5)
        } else {
            alertInfo('실패');
        }

    },
    methods: {
        savePopup() {
            swConfirm('warning', true, '저장하시겠습니까?', function () {
                callAjaxPost("/spt/auth/updateUserAuthDeatil", "authProcess", 'frm');
            })
        },
        menuChk1(item) {
            // 현재 들어가있는 체크박스 목록
            const menuIncluded = authInfo.chkArray.includes(item.menuCd);
            let menuIndex;
            for (let i = 0; i < authInfo.menuDepth1.length; i++) {
                let info = authInfo.menuDepth1[i]
                if (info.menuCd === item.menuCd) {
                    menuIndex = i
                    break;
                }
            }

            let list = authInfo.menuDepth1[menuIndex].depth
            if (menuIncluded) {
                // 전체 선택
                list.forEach(function (item, index) {
                    authInfo.chkArray.push(item.menuCd)
                    authInfo.menuChk2(item, false)
                })
            } else {
                // 전체 해제
                list.forEach(function (item, index) {
                    authInfo.chkArray = authInfo.chkArray.filter(item1 => item1 !== item.menuCd);
                    authInfo.menuChk2(item, false)
                })
            }
        },
        menuChk2(item, flg) { // flg :  체크박스 체크했을때(true) or 내부 함수 호출일때(false)
            let subList
            let menuCd;
            let parentCd = item.t1menu;
            if (flg) {
                // 상위메뉴 체크처리
                parentCd = item.t1menu
                menuCd = item.t2menu
            } else {
                menuCd = item.menuCd
            }
            const menuIncluded = authInfo.chkArray.includes(menuCd);
            Object.keys(authInfo.menuDepth2).forEach(function (key) {
                if (menuCd === key) {
                    subList = authInfo.menuDepth2[key]
                    if (menuIncluded) {
                        // 전체 선택
                        subList.forEach(function (item, index) {
                            authInfo.chkArray.push(item.menuCd)
                        })
                    } else {
                        // 전체 해제
                        subList.forEach(function (item, index) {
                            authInfo.chkArray = authInfo.chkArray.filter((item1) => !item1.includes(item.menuCd));
                        })
                    }
                } else {
                    subList = [];
                }
            });

            if (parentCd) {
                // 현재 들어가있는 체크박스 목록
                const parentIncluded = authInfo.chkArray.includes(parentCd);
                let menuIndex = -1;
                for (let i = 0; i < authInfo.menuDepth1.length; i++) {
                    let info = authInfo.menuDepth1[i];
                    let currentLevelTotalLength = info.length;
                    if (info.menuCd === parentCd) {
                        let selectedCnt = 0;
                        currentLevelTotalLength = info.length; //현재단계 크기
                        let parentList = authInfo.menuDepth1[i].depth;
                        parentList.forEach(function (item, index) {
                            if (authInfo.chkArray.indexOf(item.menuCd) !== -1) {
                                selectedCnt++;
                            }
                        });

                        if (selectedCnt > 0) {
                            if (!parentIncluded) {
                                authInfo.chkArray.push(parentCd);
                            }
                        } else {
                            authInfo.chkArray = authInfo.chkArray.filter((item1) => !item1.includes(parentCd));
                        }
                        break;
                    }
                }
            }
        },
        menuChk3(item) {
            let parentList
            let parentCd = item.t2menu;

            Object.keys(authInfo.menuDepth2).forEach(function (key) {
                if (parentCd === key) {
                    // 현재 들어가있는 체크박스 목록
                    const parentIncluded = authInfo.chkArray.includes(parentCd);

                    parentList = authInfo.menuDepth2[key];
                    let selectedCnt = 0;
                    parentList.forEach(function (item, index) {
                        if (authInfo.chkArray.indexOf(item.menuCd) !== -1) {
                            selectedCnt++;
                        }
                    });

                    if (selectedCnt > 0) {
                        if (!parentIncluded) {
                            authInfo.chkArray.push(parentCd);
                            authInfo.menuChk2(item, false);
                        }
                    } else {
                        authInfo.chkArray = authInfo.chkArray.filter((item1) => !item1.includes(parentCd));
                        authInfo.menuChk2(item, false);
                    }
                } else {
                    parentList = [];
                }
            });
        },
    },
})

function checkbox(val) {
    let $rowspan = $(this).closest('tr').find('.first[rowspan]'); // 병합된 셀
    if ($rowspan.length > 0) {
        $rowspan.each(function () {
            $(this).nextUntil('tr').find('.itemChk').prop('checked', this.checked);
        });
    } else {
        $(this).closest('tr').nextUntil(':not(.cursor_pointer)').find('.itemChk').prop('checked', this.checked);
    }
    /* $('.first .itemChk').change(function(){
         // 해당 상위 체크박스의 하위 체크박스들을 선택
         $(this).closest('tr').find('.second .itemChk').prop('checked', this.checked);
     })*/
    ;
}

function authProcess(res) {
    if (res.code === 1000) {
        alertInfo("저장 성공", function () {
            goPage('/spt/auth/authList/')
        });
    } else {
        alertError("저장 실패")
    }
}

function calculateRowspan() {
    $(".first").each(
        function () {
            let rows = $(".first:contains('" + $(this).text() + "')");/* 특정 문자열 요소를 탐색하는 함수 "name"":contains */
            if (rows.length > 1) {
                rows.eq(0).attr("rowspan", rows.length);
                rows.not(":eq(0)").remove();
            }
        });

    $(".second").each(
        function () {
            let text = $(this).text()
            if (text !== '') {
                let rows = $(".second:contains('" + $(this).text() + "')");/* 특정 문자열 요소를 탐색하는 함수 "name"":contains */
                if (rows.length > 1) {
                    rows.eq(0).attr("rowspan", rows.length);
                    rows.not(":eq(0)").remove();
                }
            }
        });
}