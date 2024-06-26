let depth0, depth1, depth2;

$(async function () {
    // 최상위 부모 코드
    await getParntMenuCd();
    // await getMenuInfo();
});

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

async function upsertMenu() {
    let res = await fetchPostForm("/spt/menu/upsertMenu", 'frm');

    if (res.code === 1000) {
        await swConfirm('info', false, '성공', 'goMenuList');
    } else {
        await alertError("실패")
    }
}


async function getMenuInfo() {
    let res = await fetchGet("/spt/menu/getMenuInfo", 'frm');

    if (res.code === 1000) {
        await alertInfo("성공")
    } else {
        await alertError("실패")
    }
}

async function getParntMenuCd() {
    let res = await fetchGet("/spt/menu/getMenuClsList");

    if (res.code === 1000) {
        console.log(res.data)
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

function menuAlert() {
    swConfirm('warning', true, '등록하시겠습니까?', 'upsertMenu')
}

function goMenuList() {
    goPage("/spt/menu/menuList");
}