function logout() {
    swConfirm(null, true,"로그아웃 하시겠습니까?", 'logoutAction');
}

function logoutAction() {
    callAjaxGet('/member/logout', 'logoutSuccess');
}

function logoutSuccess(res) {
    if(res.code === 1000) {
        goPage(res.data.returnUrl);
    }else {
        alertError("로그아웃 실패")
    }

}