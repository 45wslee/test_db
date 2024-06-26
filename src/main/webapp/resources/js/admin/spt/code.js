// import common from "/test.js";
$(async function () {
    toUpperCase();
    // Vue.use(common);
});


// 코드 추가/수정
async function upsertSubCode(isParent) {
    // TODO 유효성체크
    await swConfirm('info', true, '저장하시겠습니까?', async function () {
        let res;
        if (isParent) {
            // 상위코드
            res = await fetchPostForm("/spt/code/updateCode", 'pCodeForm');
            if (res.code === 1000) {
                alertSuccess("저장되었습니다")
                codeInfo.codeList = res.data
            } else {
                alertError("저장 실패")
            }
        } else {
            // 하위코드
            res = await fetchPostForm("/spt/code/updateSubCode", 'codeForm');
            if (res.code === 1000) {
                alertSuccess("저장되었습니다")
                codeInfo.subCodeList = res.data
            } else {
                alertError("저장 실패")
            }
        }
        $("#codePopup").removeClass("show");
        $("#pCodePopup").removeClass("show");
        $("body").css({overflow: "auto"});
    });

}

function setTotalCd() {
    let mainCd = $('#mainCd').val().toUpperCase()
    let totalCd = mainCd + $('#subCd').val()
    $('#totalCd').val(totalCd)
}

let codeInfo
codeInfo = new Vue({
    el     : ".content_wrap",
    data   : {
        totalCd    : '',
        codeNm     : '',
        useYn      : '',
        isDuplicate: false,
        selCodeInfo: [],
        codeList   : [],
        subCodeList: []
    },
    created: async function () {
        await this.selectCode(true, '000');
    },
    methods: {
        addParent() {
            // TODO isDuplicate 유효성체크
            swConfirm('warning', true, '등록하시겠습니까?', async function () {
                let param = {
                    mainCd: $('#pMainCd').val(),
                    codeNm: $('#pCodeNm').val(),
                }
                let res = await fetchPostForm("/spt/code/addParntCode", param);
                if (res.code === 1000) {
                    alertSuccess("등록되었습니다")
                    this.codeList = res.data
                } else {
                    alertError("등록 실패")
                }
            })
        },
        async codePopup(codeNm, totalCd) {
            // 상위 코드 수정 팝업
            let param = {
                totalCd: totalCd
            }
            let res = await fetchGet("/spt/code/getCodeInfo", param);
            if (res.code === 1000) {
                let form = document.forms[1]
                form.codeNm.value = res.data.codeNm
                form.totalCd.value = res.data.totalCd

                if (res.data.useYn === 'Y') {
                    $("input:radio[id='useYn1']").prop("checked", true);
                } else {
                    $("input:radio[id='useYn2']").prop("checked", true);
                }

                if (res.data.systemYn === 'Y') {
                    $("input:radio[id='systemYn1']").prop("checked", true);
                } else {
                    $("input:radio[id='systemYn2']").prop("checked", true);
                }

                $("#pCodePopup").addClass("show");
                $("body").css({overflow: "hidden"});
            } else {
                alertError("조회 실패")
            }
        },
        searchCode() {
            console.log('searchCode')
        },
        async subCodePopup(isInsert, code) {
            // 하위 코드 등록/수정 팝업
            if (isInsert === true) {
                // 등록시 값 초기화
                $('#codeForm .reset').each(function () {
                    let elementType = $(this).attr('type'); // input 요소의 type 얻기
                    if (elementType === 'text' || elementType === 'number' || elementType === 'radio') {
                        if (elementType === 'radio') {
                            $(this).prop('checked', false); // radio 요소의 선택 해제
                        } else {
                            $(this).attr('readonly', false)
                            $(this).val(''); // text 또는 number 요소의 값을 빈 문자열로 설정하여 초기화
                        }
                    }
                });
                // 선택한 부모 코드 설정
                $('#mainCd').val(codeInfo.selCodeInfo.mainCd)
                $('#totalCd').val(codeInfo.selCodeInfo.mainCd)
                // TODO 코드 추가 시 메인 코드의 정보는 DB에서 가지고 와도 됨
                $("#codePopup").addClass("show");
                $("body").css({overflow: "hidden"});
            } else {
                let param = {
                    totalCd: code,
                }

                let res = await fetchGet("/spt/code/getCodeInfo", param);
                if (res.code === 1000) {
                    $('#codeNm').val(res.data.codeNm)
                    $('#totalCd').val(res.data.totalCd)
                    $('#mainCd').val(res.data.mainCd)
                    $('#subCd').val(res.data.subCd)
                    $('#sort').val(res.data.sort)

                    $('#totalCd').attr('readonly', true)
                    $('#mainCd').attr('readonly', true)
                    $('#subCd').attr('readonly', true)

                    if (res.data.systemYn === 'Y') {
                        $("input:radio[id='system1']").prop("checked", true);
                    } else {
                        $("input:radio[id='system2']").prop("checked", true);
                    }

                    if (res.data.useYn === 'Y') {
                        $("input:radio[id='item_01']").prop("checked", true);
                    } else {
                        $("input:radio[id='item_02']").prop("checked", true);
                    }
                    $("#codePopup").addClass("show");
                    $("body").css({overflow: "hidden"});
                } else {
                    alertError("조회 실패")
                }
            }
        },
        async duplicateCode() {
            // 코드중복체크
            // TODO 유효성체크
            let param = {
                mainCd: $('#pMainCd').val()
            }

            let res = await fetchPostForm("/spt/code/duplicateCode", param);
            if (res.code === 1011) {
                this.isDuplicate = true
                alertWarning('공통코드 중복')
            } else if (res.code === 1500) {
                this.isDuplicate = false
                alertError('체크 에러 발생')
            } else {
                this.isDuplicate = false
                alertError('공통코드 미중복')
            }
        },
        async selectCode(isParent, code, idx) {
            // 상위코드
            if (isParent) {
                let param = {
                    subCd: code
                }
                let res = await fetchGet("/spt/code/selectCommonCodeList", param);
                if (res.code === 1000) {
                    codeInfo.codeList = res.data
                } else {
                    alertError('조회 에러 발생')
                }
            } else {
                this.selCodeInfo = this.codeList[idx]
                // 하위 코드
                let param = {
                    mainCd: code
                }
                let res = await fetchGet("/spt/code/selectCommonCodeList", param);
                if (res.code === 1000) {
                    // $(".modal_wrap").addClass("show");
                    codeInfo.subCodeList = res.data
                } else if (res.code === 1010) {
                    codeInfo.subCodeList = []
                } else {
                    alertError('조회 에러 발생')
                }
            }
        }
    }
})