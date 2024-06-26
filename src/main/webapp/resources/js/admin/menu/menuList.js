let depth0, depth1, depth2;
let popupDepth0, popupDepth1, popupDepth2;

$(document).ready(function () {

    // 초기 데이터 가져오기
    getMenuList();

});

$(async function () {
    // 최상위 부모 코드
    await getParntMenuCd();

});

async function getMenuList() {
    let res = await fetchGet("/spt/menu/getMenuList", 'frm');
    if (res.code === 1000) {
        await successCallBack(res);
    } else {
        await alertError("실패")
    }

}

async function successCallBack(res) {
    let menuListTemp = res.data.menuList;
    console.log(menuListTemp) 
    let menuListUl ='';
    if (menuListTemp == 0) {
        menuListUl='<p class="menu_list_none"><i class="fa-solid fa-triangle-exclamation"></i><br>등록된 메뉴가 없습니다.<br>신규등록을 눌러 메뉴를 추가해주세요.</p>';
    }else {
        for(i=0 ; i< menuListTemp.length; i++) {
            let menuList = menuListTemp[i];
            let menuListNext = menuListTemp[i+1];
            menuListUl+=`<li>`;
             if(menuList.depth == 0) {
            menuListUl+=`   <p class="menu_list_item" onclick="getMenuInfo(this,'${menuList.menuCd}')">${menuList.menuNm}`;
                if(menuList.useYn == 'N') {
                    menuListUl+=`<span class="badge badge_rounded badge_red">미사용</span>`;
                }
             }
            if(menuList.depth == 0 && menuList.mCount != 0) {
                 menuListUl+=`<i class="fa-solid fa-angle-down"></i>`;
            }
             if(menuList.depth == 0) {
                 menuListUl+=`   </p>`;
             }
            if(menuList.mCount != 0 && menuList.depth == 0) {
                menuListUl+=`   <ul class="menu_list_02">`;
                menuListUl+=`       <li>`;
            }
            if(menuList.depth == 1) {
                menuListUl+=`           <p class="menu_list_item" onclick="getMenuInfo(this,'${menuList.menuCd}')">${menuList.menuNm}`;
                if(menuList.useYn == 'N') {
                    menuListUl+=`<span class="badge badge_rounded badge_red">미사용</span>`;
                }
                if(menuList.mCount != 0) {
                menuListUl+=`           <i class="fa-solid fa-angle-down"></i>`;
                }
                menuListUl+=`           </p>`;
                if(menuList.mCount != 0) {
                 menuListUl+=`           <ul class="menu_list_03">`;
                 menuListUl+=`           <li>`;
                }
            }
            if(menuList.depth == 2) {
            menuListUl+=`           <p class="menu_list_item" onclick="getMenuInfo(this,'${menuList.menuCd}')">${menuList.menuNm}`;
                if(menuList.useYn == 'N') {
                    menuListUl+=`<span class="badge badge_rounded badge_red">미사용</span>`;
                }
                menuListUl+=`</p>`;
                if(menuListNext.depth != 2 && menuListNext != undefined) {
                    menuListUl+=`</li>`;
                    menuListUl+=`</ul>`;
                }
            }
            if(menuList.depth == 0 && menuList.mCount == 0){
                menuListUl+=`</li>`;
                menuListUl+=`</ul>`;
            }else if (menuList.depth == 1 && menuListNext != undefined ) {
                if(menuListNext.depth == 0) {
                    menuListUl+=`</li>`;
                    menuListUl+=`</ul>`;
                }
            }else if (menuList.depth == 2 && menuListNext.depth == 0 && menuListNext != undefined) {
                 menuListUl+=`</li>`;
                menuListUl+=`</ul>`;
            }
        }
        //menuListUl = $('#menuListTmpl').tmpl(jsonData);
    }
    $("#menuListUl").html(menuListUl);
}

function changeMenu(obj) {
    let val = obj.value
    let html = ''

    switch (val) {
        case '0':
            // 대분류 0
            // 부모 메뉴 코드 disabled
            $('#parntMenuCd').attr('disabled', true);
            $('#depthLength').val(depth0.length);
            $('#iconTr').css('display','table-row');
            break;
        case '1':
            // 중분류 1
            // 부모 메뉴 코드 활성화
            // 부모 메뉴 코드 depth0
            $('#parntMenuCd').children().remove();
            $.each(depth0, function (index, item) {
                html += ' <option value="' + item.menuCd + '">' + item.menuNm + '</option>'
            })

            $('#parntMenuCd').append(html);
            $('#parntMenuCd').attr('disabled', false);
            $('#depthLength').val(depth1.length);
            $('#iconTr').css('display','none');
            break;
        case '2':
            // 소분류 2
            // 부모 메뉴 코드 depth1
            $('#parntMenuCd').children().remove();
            $.each(depth1, function (index, item) {
                html += ' <option value="' + item.menuCd + '">' + item.menuNm + '</option>'
            })

            $('#parntMenuCd').append(html);
            $('#parntMenuCd').attr('disabled', false);
            $('#depthLength').val(depth2.length);
            $('#iconTr').css('display','none');
            break;
        default:
            // 나머지
            break;
    }
}

//팝업의 부모 코드 선택 시
function popupChangeMenu(obj) {
    let val = obj.value
    let html = ''

    switch (val) {
        case '0':
            // 대분류 0
            // 부모 메뉴 코드 disabled
            $('#popupParntMenuCd').attr('disabled', true);
            $('#popupDepthLength').val(popupDepth0.length);
            $('#popupIconTr').css('display','block');
            break;
        case '1':
            // 중분류 1
            // 부모 메뉴 코드 활성화
            // 부모 메뉴 코드 depth0
            $('#popupParntMenuCd').children().remove();
            $.each(popupDepth0, function (index, item) {
                html += ' <option value="' + item.menuCd + '">' + item.menuNm + '</option>'
            })

            $('#popupParntMenuCd').append(html);
            $('#popupParntMenuCd').attr('disabled', false);
            $('#popupDepthLength').val(popupDepth1.length);
            $('#popupIconTr').css('display','none');
            break;
        case '2':
            // 소분류 2
            // 부모 메뉴 코드 depth1
            $('#popupParntMenuCd').children().remove();
            $.each(popupDepth1, function (index, item) {
                html += ' <option value="' + item.menuCd + '">' + item.menuNm + '</option>'
            })

            $('#popupParntMenuCd').append(html);
            $('#popupParntMenuCd').attr('disabled', false);
            $('#popupDepthLength').val(popupDepth2.length);
            $('#popupIconTr').css('display','none');
            break;
        default:
            // 나머지
            break;
    }
}

async function getMenuInfo(obj,menuCd) {
    $(".menu_list_item").removeClass("on");
    $(obj).addClass('on');
    $("#menuCd").val(menuCd);
    let res = await fetchGet("/spt/menu/getMenuInfo", 'frm');

    if (res.code === 1000) {
        var jsonData= res.data.menuInfo;
        $("#menuFormTable").css("display","table");
        $("#menuFormTableNo").css("display","none");
        $("#menuCls").val(jsonData.menuCls);
        if(jsonData.menuCls == 0) {
            $('#parntMenuCd').attr('disabled', true);
            $('#iconTr').css('display','table-row');
            $('input:radio[name="menuIconCls"]').each(function() { 
                this.checked = false;
            });
            $('input:radio[name="menuIconCls"]').each(function() { 
            if(this.value== jsonData.menuIconCls ){//checked 처리된 항목의 값
                this.checked = true;       
            } });
        }else {
            $("#menuCls").change();
            $('#parntMenuCd').attr('disabled', false);
            $("#parntMenuCd").val(jsonData.parntMenuCd);
            $('#iconTr').css('display','none');
        }
        $("#sort").val(jsonData.sort);
        $("#menuNm").val(jsonData.menuNm);
        $("#menuUrl").val(jsonData.menuUrl);
        //초기화
        $('input:radio[name="systemYn"]').each(function() { 
            this.checked = false;
        });
        $('input:radio[name="externalYn"]').each(function() { 
            this.checked = false;
        });
        $('input:radio[name="useYn"]').each(function() { 
            this.checked = false;
        });

        $('input:radio[name="systemYn"]').each(function() { 
        if(this.value == jsonData.systemYn ){//checked 처리된 항목의 값
            this.checked = true;       
        } });
        $('input:radio[name="externalYn"]').each(function() { 
        if(this.value == jsonData.externalYn ){//checked 처리된 항목의 값
            this.checked = true;       
        } });
        $('input:radio[name="useYn"]').each(function() { 
        if(this.value == jsonData.useYn ){//checked 처리된 항목의 값
            this.checked = true;       
        } });

    } else {
        $("#menuFormTable").css("display","none");
        $("#menuFormTableNo").css("display","block");
        await alertError("실패")
    }
}

async function getParntMenuCd() {
    let res = await fetchGet("/spt/menu/getMenuClsList");

    if (res.code === 1000) {
        depth0 = res.data.menuClsList0
        depth1 = res.data.menuClsList1
        depth2 = res.data.menuClsList2
        $('#depthLength').val(depth0.length)
        let html = ''
        $.each(depth0, function (index, item) {
            html += ' <option value="' + item.menuCd + '">' + item.menuNm + '</option>'
        })
        $('#parntMenuCd').append(html)
        $('#parntMenuCd').attr('disabled', true)
    } else {
        await alertError("실패")
    }
}

//팝업의 분류코드 들고오기
async function getPopupParntMenuCd() {
    let res = await fetchGet("/spt/menu/getMenuClsList");

    if (res.code === 1000) {
        popupDepth0 = res.data.menuClsList0
        popupDepth1 = res.data.menuClsList1
        popupDepth2 = res.data.menuClsList2
        $('#popUpdepthLength').val(popupDepth0.length)
        let html = ''
        $.each(popupDepth0, function (index, item) {
            html += ' <option value="' + item.menuCd + '">' + item.menuNm + '</option>'
        })
        $('#popupParntMenuCd').append(html)
        $('#popupParntMenuCd').attr('disabled', true)
    } else {
        await alertError("실패")
    }
}

//팝업 키기
function openMenuPopup() {
    $("#menuListPopup").addClass("show");
    $("body").css({ overflow: "hidden" });
    $("#popupFrm")[0].reset();
    getPopupParntMenuCd();
    $("#popupMenuCls").val(0);
    $('#popupIconTr').css('display','block');
}

//팝업 닫기
function closeMenuPopup() {
    $("#menuListPopup").removeClass("show");
    $("body").css({ overflow: "auto" });
}

function menuPopupCancel () {
    swConfirm('warning', true, '취소하겠습니까?', 'closeMenuPopup')
}

//신규등록
function menuAlertPoup() {
    swConfirm('warning', true, '등록하시겠습니까?', 'upsertMenuPoup')
}

function menuAlert() {
    swConfirm('warning', true, '저장하시겠습니까?', 'upsertMenu')
}

//등록
async function upsertMenuPoup() {
    let res=await fetchPostForm("/spt/menu/upsertMenu", 'popupFrm');

    if (res.code === 1000) {
        await swConfirm('info', false, '등록되었습니다.', 'popupGoMenuList');
    } else {
        await alertError("실패")
    }
}

//수정
async function upsertMenu() {
    let res=await fetchPostForm("/spt/menu/upsertMenu", 'frm');

    if (res.code === 1000) {
        await swConfirm('info', false, '수정되었습니다.', 'goMenuList');
    } else {
        await alertError("실패")
    }
}

function popupGoMenuList() {
    closeMenuPopup();
    window.location.reload();
}
function goMenuList() {
    window.location.reload();
}

//삭제
function deleteMenuAlert() {
    swConfirm('warning', true, '삭제하시겠습니까?', 'deleteMenu');
}

async function deleteMenu() {
    let data = {
        "menuCd":$("#menuCd").val()
    }

    let res = await fetchPostJson('/spt/menu/deleteMenu',data);

    if(res.code === 1000) {
        await swConfirm('info', false, '삭제되었습니다.', 'goMenuList');
    }else {
        await alertError("실패")
    }
}
