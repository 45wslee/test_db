let RSAModulus = "";
let RSAExponent = "";

$(document).ready(function() {
    $("#mbrId").keypress(function (e) {
        if (e.which == 13) {
            $("#mbrPswd").focus();
        }
    });

    $("#mbrPswd").keypress(function (e) {
        if (e.which == 13) {
            //$("#securityLoginBtn").click();
            $("#loginBtn").click();
        }
    });
});

function join() {
    let data = {
        "mbrNm"   : document.joinForm.mbrNm.value,
        "mbrId"   : document.joinForm.mbrId.value,
        "mbrPswd" : document.joinForm.mbrPswd.value,
        "mbrTelno": document.joinForm.mbrTelno.value
    }

    let publicKeyStr = $('#publicKeyStr').val();
    data.mbrNm = rsaEncrypt(data.mbrNm, publicKeyStr);
    data.mbrId = rsaEncrypt(data.mbrId, publicKeyStr);
    data.mbrPswd = rsaEncrypt(data.mbrPswd, publicKeyStr);
    data.mbrTelno = rsaEncrypt(data.mbrTelno, publicKeyStr);

    callAjaxPost('/join', 'joinSuccess', data);

    return false;
}

async function joinSuccess(res) {
    console.log(res);
    if (res.code === 1000) {
        alertSuccess("회원가입 성공 성공", "/login");
    } else {
        alertError("회원가입 실패");
    }
}

function login() {
    let userId = $("#mbrId").val();
    let userPw = $("#mbrPswd").val();

    getLoginAuth();

    let rsa = new RSAKey();
    rsa.setPublic(RSAModulus, RSAExponent);
    userId = rsa.encrypt(userId.trim());
    userPw = rsa.encrypt(userPw.trim());

    let param = {
        mbrId  : userId,
        mbrPswd: userPw
    }

    callAjaxPost('/login', 'loginSuccess', param);
}

function getLoginAuth() {
    callAjaxPost("/api/member/loginAuth", "authSuccess", undefined, "commonFail", false, false);
}

function authSuccess(res) {
    if (res.code === 1000) {
        let jsonData = res.data;

        RSAModulus = jsonData.RSAModulus;
        RSAExponent = jsonData.RSAExponent;
    } else {
        alertError(getMessage("common.info.request.error"));
    }
}

function commonFail(res) {
    alertError(getMessage("common.info.request.error"));
}


function securityAjaxLogin() {
    let userId = $("#mbrId").val();
    let userPw = $("#mbrPswd").val();

    getLoginAuth();

    let rsa = new RSAKey();
    rsa.setPublic(RSAModulus, RSAExponent);
    userId = rsa.encrypt(userId.trim());
    userPw = rsa.encrypt(userPw.trim());

    /*let userInfo = {
        mbrId: userId,
    }
    callAjaxGet("/checkUser", 'test', userInfo, '', false)*/
    let param = {
        mbrId  : userId,
        mbrPswd: userPw
    }
    callAjaxPost('/loginProc', 'loginSuccess', param);
}

function test(res) {
    console.log("test")

}

function loginSuccess(res) {
    console.log(res);
    if (res.code === 1000) {
        goPage(res.data.returnUrl);
    } else {
        alertError("로그인 실패")
    }
}

function login2() {
    alertInfo('aaa');
}

function login3() {
    alertInfo('aaa', '/join');
}

function login4() {
    alertInfo('aaa', 'aaaaaaa');
}

function aaaaaaa() {
    alert("aa");
}