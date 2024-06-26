let consoleLog = false;  // console 출력 여부

$(function () {
    // ajax POST 요청 전 document title 재 설정
    $.ajaxPrefilter(function (options) {
        if (options.type.toUpperCase() == "POST") {
            document.title = getMessage("message.document.title");
        }
    });

    $("input[maxlength]").on("input", function () {
        maxLengthCheck(this);
    });

    $("textarea[maxlength]").on("input", function () {
        maxLengthCheck(this);
    });
});

/**
 * 반응형 screen width 체크(desktop / mobile) mediaQuery 현재 screen 사이즈에 따른 matches
 * 값(true/fasle) 반환 true: mobile, false: desktop
 */
function isMobileMedia() {
    const mediaQuery = matchMedia("screen and (max-width: 1024px)");
    return mediaQuery.matches;
}


/**
 * header.jsp 에 hidden 속성으로 contextPath를 먼저 지정한다.
 * <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
 *
 * @param url
 * @returns string
 */
function makeApiUrl(url) {
    if (url.startsWith('http')) {
        return url;
    }
    return document.querySelector('#contextPath').value + url;
}

function goPage(url) {
    location.href = makeApiUrl(url);
}

function requestList(pageNo, url) {
    document.frm.pageIndex.value = pageNo;
    document.frm.action = makeApiUrl(url);
    document.frm.submit();
}

/**
 * enterKey 입력 시 페이지 호출
 * @param event
 * @param url
 */
function pressSearchKey(event, url) {
    if (event.keyCode === 13) {
        requestList('1', url);
    }
}

/**
 * GET 방식 ajax 호출
 * @param url
 * @param callbackFunc
 * @param data
 * @param callbackFailFunc
 * @param ajaxAsync
 * @param loading
 */
function callAjaxGet(url, callbackFunc, data, callbackFailFunc, ajaxAsync, loading) {
    if (ajaxAsync === undefined || ajaxAsync === '') {
        ajaxAsync = true;
    }

    if (loading === undefined) {
        loading = true;
    }


    let transferData = getTransferData(data);
    url = makeApiUrl(url);
    if (consoleLog) {
        console.log(url);
    }
    $.ajax({
        type       : "get",
        async      : ajaxAsync,
        url        : url,
        data       : transferData,
        dataType   : "json",
        cache      : false,
        xhrFields  : {
            withCredentials: true
        },
        crossDomain: true,
        beforeSend : function (jqXHR) {
            if (loading) {
                showLoading();
            }
            jqXHR.setRequestHeader("AJAX", "true");
            /*$("#loading").show();
            jqXHR.setRequestHeader("AJAX", "true");
            if (localStorage.token) {
                jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }*/
        },
        complete   : function () {
            if (loading) {
                hideLoading();
            }
        },
        success    : function (data, textStatus, jqXHR) {
            if (consoleLog) {
                console.log("Success=====S");
                logView(data);
                console.log("Success=====E");
            }
            if (isFunction(callbackFunc)) {
                window[callbackFunc](data);
            }
        },
        error      : function (jqXHR, textStatus, errorThrown) {
            if (consoleLog) {
                console.log("error=====S");
                logView(jqXHR);
                console.log("error=====E");
            }
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc](jqXHR);
            } else {
                handleErrorStatus(jqXHR);
            }
        }
    });
}

/**
 * POST 방식 ajax 호출
 * @param url
 * @param callbackFunc
 * @param data
 * @param callbackFailFunc
 * @param ajaxAsync ( default = true )
 */
function callAjaxPost(url, callbackFunc, data, callbackFailFunc, ajaxAsync) {
    if (ajaxAsync === undefined || ajaxAsync === '') {
        ajaxAsync = true;
    }
    let transferData = getTransferData(data);
    url = makeApiUrl(url);
    if (consoleLog) {
        console.log(url);
    }
    $.ajax({
        type       : "post",
        async      : ajaxAsync,
        url        : url,
        data       : transferData,
        dataType   : "json",
        cache      : false,
        xhrFields  : {
            withCredentials: true
        },
        crossDomain: true,
        beforeSend : function (jqXHR) {
            $("#loading").show();
            jqXHR.setRequestHeader("AJAX", "true");
            if (localStorage.token) {
                jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        complete   : function () {
            $("#loading").hide();
        },
        success    : function (data, textStatus, jqXHR) {
            if (consoleLog) {
                console.log("Success=====S");
                logView(data);
                console.log("Success=====E");
            }
            if (isFunction(callbackFunc)) {
                window[callbackFunc](data);
            }
        },
        error      : function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR)
            console.log(textStatus)
            console.log(errorThrown)
            if (consoleLog) {
                console.log("error=====S");
                logView(jqXHR);
                console.log("error=====E");
            }
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc](jqXHR.responseJSON);
            } else {
                handleErrorStatus(jqXHR);
            }
        }
    });
}

/**
 * multipart ajax call
 * @param url
 * @param callbackFunc
 * @param formId
 * @param callbackFailFunc
 */
function callByMultipart(url, callbackFunc, formId, callbackFailFunc = '') {

    if (consoleLog) {
        console.log(url);
    }
    url = makeApiUrl(url);

    $("#" + formId).ajaxForm({
        xhrFields  : {
            withCredentials: true
        },
        crossDomain: true,
        processData: false,
        dataType   : 'json',
        beforeSend : function (jqXHR) {
            jqXHR.setRequestHeader("AJAX", "true");
        },
        xhr        : function () {
            var xhr = $.ajaxSettings.xhr();
            if ($(".progressBar:visible").length > 0) {
                xhr.upload.onprogress = function (e) {
                    var percent = e.loaded * 100 / e.total;
                    $(".progressBar:visible").val(percent); //개별 파일의 프로그레스바 진행
                };
            }
            return xhr;
        },
        complete   : function (jqXHR, textStatus) {

        },
        success    : function (data, textStatus, jqXHR) {
            if (consoleLog) {
                console.log("Success=====S");
                logView(data);
                console.log("Success=====E");
            }
            if (isFunction(callbackFunc)) {
                window[callbackFunc](data);
            }
        },
        error      : function (jqXHR, textStatus) {
            if (consoleLog) {
                console.log("error=====S");
                logView(jqXHR);
                console.log("error=====E");
            }
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc](jqXHR);
            } else {
                handleErrorStatus(jqXHR);
            }
        }
    });

    $("#" + formId).attr("action", url);
    $("#" + formId).submit();
}

function callByMultipartFormData(url, callbackFunc, data, callbackFailFunc, ajaxAsync, loading) {
    if (ajaxAsync === undefined || ajaxAsync === "") {
        ajaxAsync = true;
    }

    if (loading === undefined) {
        loading = true;
    }

    url = makeApiUrl(url);
    if (consoleLog) {
        console.log(url);
    }
    $.ajax({
        type       : "post",
        async      : ajaxAsync,
        url        : url,
        data       : data,
        dataType   : "json",
        cache      : false,
        xhrFields  : {
            withCredentials: true,
        },
        beforeSend : function (jqXHR) {
            if (loading) {
                showLoading();
            }
            jqXHR.setRequestHeader("AJAX", "true");
        },
        crossDomain: true,
        processData: false,
        contentType: false,
        complete   : function () {
            if (loading) {
                hideLoading();
            }
        },
        success    : function (data, textStatus, jqXHR) {
            if (consoleLog) {
                console.log("Success=====S");
                logView(data);
                console.log("Success=====E");
            }
            if (isFunction(callbackFunc)) {
                window[callbackFunc](data);
            }
        },
        error      : function (jqXHR, textStatus, errorThrown) {
            if (consoleLog) {
                console.log("error=====S");
                logView(jqXHR);
                console.log("error=====E");
            }
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc](jqXHR);
            } else {
                handleErrorStatus(jqXHR);
            }
        },
    });
}

/**
 * POST 방식 파일다운로드 ajax 호출
 *
 * @param url
 * @param data
 * @param loading
 *            (default = true)
 */
function callFileAjaxPost(url, data, loading) {
    if (loading === undefined) {
        loading = true;
    }

    if (loading) {
        showLoading();
    }

    var frm = document.getElementById(data);
    var formData = new FormData(frm);

    let transferData = formData;
    url = makeApiUrl(url);
    if (consoleLog) {
        console.log(url);
    }

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                var fileName = xhr.getResponseHeader("content-disposition").split("filename=")[1].split(";")[0];
                console.log("fileName : " + fileName);
                fileName = window.atob(fileName);
                fileName = decodeURI(fileName);

                var a = document.createElement("a");
                var url = URL.createObjectURL(this.response);
                a.href = url;
                a.download = fileName;
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
            }

            if (loading) {
                hideLoading();
            }
        }
    };
    xhr.open("POST", url);
    xhr.responseType = "blob";
    xhr.send(formData);
}

function getTransferData(data) {
    var transferData = '';
    if (typeof (data) === 'object') {
        transferData = data;
    } else if (typeof (data) === 'string') {
        transferData = data !== "" ? $("#" + data).serialize() : "";
    }
    return transferData;
}

function logView(data) {
    console.log(JSON.stringify(data, null, 4));
}

function isFunction(functionName) {
    return (typeof window[functionName] === "function");
}

function handleErrorStatus(jqXHR, forbidden) {
    if (jqXHR.status === 401) {
        alert("세션이 만료되었습니다. 다시 로그인을 해주세요.");
        // document.location.href = makeApiUrl("/");
    } else if (jqXHR.status === 403) {
        //console.log(JSON.stringify(jqXHR.responseText,null,4));
    } else if (jqXHR.status === 404) {
        //console.log(JSON.stringify(jqXHR.responseText,null,4));
    } else if (jqXHR.status === 400) {
        //console.log(JSON.stringify(jqXHR.responseText,null,4));
    } else if (jqXHR.status === 405) {
    } else if (jqXHR.status === 500) {
        //console.log(JSON.stringify(jqXHR.responseText,null,4));
    } else {
        //console.log(JSON.stringify(jqXHR.responseText,null,4));
    }
}

/**
 *  배열 또는 객체의 빈 값 체크 함수
 * @param val 입력배열 또는 객체값
 * @returns {boolean}
 */
function isEmpty(val) {
    if (val === "" || val === null || val === undefined || (typeof val === "object" && !Object.keys(val).length)) {
        return true;
    } else {
        return false;
    }
}

/**
 * 전체 replace
 * @param str
 * @param searchStr
 * @param replaceStr
 * @returns {*}
 */
function replaceAll(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);
}

/**
 * input 필드에 오직 숫자만 입력
 * 사용방법 : oninput="this.value = onlyNumber(this.value);"
 * @param value
 * @returns {*}
 */
function onlyNumber(value) {
    var tempValue = parseInt(value.replace(/,/gi, ""));
    return String(tempValue).replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');
}

// 영문만
function onlyEnglish(value) {
    return value.replace(/[^a-zA-Z]/g, '').replace(/(\..*)\./g, '$1');
}

// 한글만
function onlyKorea(value) {
    return value.replace(/([^가-힣ㄱ-ㅎㅏ-ㅣ\x20])/g, '').replace(/(\..*)\./g, '$1');
}

// 영문만 제외
function exceptEnglish(value) {
    return value.replace(/[a-zA-Z]/g, '').replace(/(\..*)\./g, '$1');
}

// 한글만 제외
function exceptKorea(value) {
    return value.replace(/([가-힣ㄱ-ㅎㅏ-ㅣ\x20])/g, '').replace(/(\..*)\./g, '$1');
}

/** 숫자 입력 (1~100)*/
function onlyNumberRang(value) {
    var tempValue = onlyNumber(value);
    if (value < 0) {
        return 0;
    } else if (value > 100) {
        return 100;
    } else {
        return tempValue;
    }
}

/**
 *  date 타입을 문자열로 변환 (date -> string)
 * @param date date 타입 값
 * @param seperator 날짜 사이 구분자 문자 지정(default : "-")
 * @param timeFlag time 형식도 표시
 * @returns {*|string}
 */
function convertDateToString(date, seperator, timeFlag) {
    var yyyy = date.getFullYear();
    var mm = date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var dd = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

    var seperator = typeof seperator === "undefined" ? "-" : seperator;
    if (timeFlag) {
        return yyyy + seperator + mm + seperator + dd + " " + hour + ":" + minute + ":" + seconds;
    } else {
        return yyyy + seperator + mm + seperator + dd;
    }
}

//검색 초기화
function searchInit() {
    $("#search").find('input[type=text]').each(function () {
        $(this).val('');
    });
    $('#search select').each(function () {
        $(this).find('option:eq(0)').prop("selected", true);
    });
}


/**
 * escape
 * 문자열 이스케이프 처리
 * @param str 치환할 문자열
 * @returns {*}
 */
function strConv(str) {
    str = str.replace(/&lt;/gi, "<");
    str = str.replace(/&gt;/gi, ">");
    str = str.replace(/&quot;/gi, "\"");
    //str = str.replace(/&nbsp;/gi," ");
    str = str.replace(/&amp;/gi, "&");
    str = str.replace(/&amp;#034;/gi, "\"");
    str = str.replace(/&#034;/gi, "\"");
    return str;
}


/**
 * checkbox 전체 선택
 * @param targetName target checkbox name
 */
function checkAllCheckbox(targetName) {
    const checkboxes = document.getElementsByName(targetName);
    let isChecked = false;
    for (let i = 0, n = checkboxes.length; i < n; i++) {
        if (checkboxes[i].disabled) {
            continue;
        }
        if (!checkboxes[i].checked) {
            isChecked = true;
            break;
        }
    }
    for (let i = 0, n = checkboxes.length; i < n; i++) {
        if (checkboxes[i].disabled) {
            continue;
        }
        checkboxes[i].checked = isChecked;
    }
}

/**
 * target checkbox의 상태를 확인하여 main checkbox 값을 변경
 * @param targetName target checkbox name
 * @param mainCheckerID 전체 선택 기능을 가진 checkbox id
 */
function modifyAllCheckerStatus(targetName, mainCheckerID) {
    const checkboxes = document.getElementsByName(targetName);
    let isAllSelected = true;

    for (let i = 0; i < checkboxes.length; i++) {
        if (!checkboxes[i].checked) {
            isAllSelected = false;
        }
    }
    document.getElementById(mainCheckerID).checked = isAllSelected;
}

/**
 * 형제 태그
 * @param t
 * @returns {*[]}
 */
function siblings(t) {
    let children = t.parentElement.children;
    let tempArr = [];

    for (let i = 0; i < children.length; i++) {
        tempArr.push(children[i]);
    }

    return tempArr.filter(function (e) {
        return e != t;
    });
}

/**
 * 형제 태그 중 특정 태그 찾기
 * @param obj
 * @returns {any}
 * @param info 입력정보 : tagName(대문자), type, id, name
 * @example info {"tagName":"INPUT", "type":"text"}
 */
function findTag(obj, info) {
    let keys = Object.keys(info);
    return obj.find(function (element) {
        let count = 0;
        for (let i = 0; i < keys.length; i++) {
            if (element[keys[i]] === info[keys[i]]) {
                count++;
            }
        }
        return count === keys.length;
    });
}

/**
 * tag 삭제
 * @param obj
 */
function deleteTag(obj) {
    obj.parentElement.removeChild(obj);
}

/**
 * 파일 확장자
 * @returns {string}
 * @param fileObj
 */
function getFileExt(fileObj) {
    // return fileObj.split('.').pop().toLowerCase();
    return getFileInfo(fileObj, 'ext');
}

/**
 * 파일 이름
 * @param fileObj
 * @returns {*|string|string}
 */
function getFileName(fileObj) {
    return getFileInfo(fileObj, 'name');
}

/**
 * 파일 이름.확장자
 * @param fileObj
 * @returns {*|string|string}
 */
function getFileNameAndExt(fileObj) {
    return getFileInfo(fileObj, 'all');
}

function getFileInfo(fileObj, flag) {
    let pathHeader = fileObj.lastIndexOf('\\');
    let pathMiddle = fileObj.lastIndexOf('.');
    let pathEnd = fileObj.length;
    let fileName = fileObj.substring(pathHeader + 1, pathMiddle);
    let extName = fileObj.substring(pathMiddle + 1, pathEnd);
    if (flag === 'ext') {
        return extName;
    } else if (flag === 'name') {
        return fileName;
    } else if (flag === 'all') {
        return fileName + '.' + extName;
    }
    return '';
}

function fileDownload(atchFileId, fileSn) {
    window.location.href = makeApiUrl('/cmm/fms/FileDown.do?atchFileId=' + atchFileId + '&fileSn=' + fileSn);
}

/**
 * API 호출 시 데이터 전달 함수
 * @param {Object} id Form 아이디
 * @param {Object} name 파라메타 명
 * @param {Object} value 파라메타 값
 */
function formData(id, name, value) {
    if (id === '') {
        return false;
    }
    var objMethod = document.createElement("input");
    objMethod.type = "hidden";
    objMethod.name = name;
    objMethod.value = value;
    if (typeof (id) === 'string') {
        document.getElementById(id).insertBefore(objMethod, null);
    } else {
        id.insertBefore(objMethod, null);
    }
}

/**
 * API 호출 시 데이터 전달 Form 삭제 함수
 * @param {Object} id Form 아이디
 * @param {Object} name 파라메타 명
 */
function formDataDelete(id, name) {
    $("#" + id + " input[name=\"" + name + "\"]").remove();
}

/**
 * API 호출 시 데이터 전달 Form 전체 삭제 함수
 * @param {Object} id Form 아이디
 */
function formDataDeleteAll(id) {
    $("#" + id).children().remove();
}

/**
 * form 데이터 를 json object 로 변환하는 함수
 * @param formId
 * @returns {{}}
 */
function convertFormDataToJson(formId) {
    let data = {};
    let array = $('#' + formId).serializeArray()
    for (let i = 0; i < array.length; i++) {
        data[array[i].name] = array[i].value;
    }
    return data;
}

/**
 * 텍스트에 콤마 입력
 * addComMa(jsonData.totalCnt)
 * @param str 콤마가 없는 값
 * @returns {string}
 */
function addComMa(str) {
    return str.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

/**
 * 텍스트 콤마 제거
 * @param str 콤마가 있는 값
 * @returns {*}
 */
function removeComMa(str) {
    return str.replace(/,/g, "");
}

/**
 * 특정한 클래스에 콤마 부여
 * @param classNm 콤마를 입력할 클래스명
 */
function commaAdd(classNm) {
    let commaObj = $("." + classNm);
    for (let i = 0; i < commaObj.length; i++) {
        $(commaObj[i]).val(moneyComma($(commaObj[i]).val()));
    }
}

function showLoading() {
    $('#loading').remove();
    $('body').append($('<div id="loading" class="loading"></div>'));
}

function hideLoading() {
    $(".loading").fadeOut();
}

function setLocationHash(pageNo) {
    window.location.hash = '#' + pageNo;
}

function getPageNoFromHash() {
    if (document.location.hash) {
        return Number(document.location.hash.replace("#", ""));
    }
    return 1;
}

function historyBack(callbackFailFunc) {
    window.onpopstate = function (event) {
        if (document.location.hash) {
            if (isFunction(callbackFailFunc)) {
                window[callbackFailFunc]();
            } else if (typeof callbackFailFunc === 'function') {
                callbackFailFunc();
            }
        } else {
            history.back();
        }
    }
}

function setSessionObject(key, obj) {
    if (('sessionStorage' in window) && window['sessionStorage'] !== null && typeof obj === 'object') {
        let objKeys = Object.keys(obj);
        for (let i = 0; i < objKeys.length; i++) {
            let objKey = objKeys[i];
            sessionStorage.setItem(key + "_" + objKey, obj[objKey]);
        }
    }
}

function getSessionItem(key, objKeys) {
    if (('sessionStorage' in window) && window['sessionStorage'] !== null && Array.isArray(objKeys)) {
        let obj = {};
        for (let i = 0; i < objKeys.length; i++) {
            let objKey = objKeys[i];
            obj[objKey] = sessionStorage.getItem(key + "_" + objKey);
        }
        return obj;
    }
}

/**
 *
 * @param paramObj 파라미터값 - { pageIndex: 1, searchCnd1: 10}
 * @returns {string} result -> ?pageIndex=1&searchCnd1=10
 */
function convertParam(paramObj) {
    return (
        "?" +
        Object.entries(paramObj)
            .map((p) => encodeURIComponent(p[0]) + "=" + encodeURIComponent(p[1]))
            .join("&")
    );
}

/**
 * (물음표) 빼먹지 말고 넣어야 합니다.
 * @param paramStr 파라미터값 - ?pageIndex=1&searchCnd1=10
 * @returns {any|string} result -> { pageIndex: 1, searchCnd1: 10 }
 */
function convertParamReverse(paramStr) {
    if (paramStr.indexOf("?") === -1) {
        return "";
    }

    let temp = paramStr.substr(paramStr.indexOf("?") + 1);
    return JSON.parse('{"' + decodeURI(temp.replace(/&/g, '","').replace(/=/g, '":"')) + '"}');
}


/**
 * 팝업 열기
 * @param popClass 팝업 클래스명
 */
function openPopup(popClass) {
    $(".popup_wrap").css("display", "block");
    $("." + popClass).css("display", "block");
}


/**
 * 팝업 닫기
 * @param popClass 팝업 클래스명
 * @param type 한개만 닫기 여부, 값 아무거나 넣어도 되지만 > 'on' < 으로 고정
 */
//
function closePopup(popClass, type) {
    if (type === undefined) {
        $(".popup_wrap").css("display", "none");
    }
    $("." + popClass).css("display", "none");
}


function showLoading() {
    $("#loading").remove();
    $("body").append($('<div id="loading" class="loading_area"><div class="loading_box"><img src="' + makeApiUrl("/images/loading_img.svg") + '"></div></div>'));
}

function hideLoading() {
    $(".loading_area").fadeOut();
}

// 전화번호 정규식 확인
function phoneCheck(value) {
    let result = true;

    var regPhone = /^(01[0|1|6|7|8|9]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
    if (regPhone.test(value)) {
        result = false;
    }

    return result;
}

/**
 * input 필드에 maxlength 제한 함수
 * @param object
 */
function maxLengthCheck(object) {
    if (object.value.length > object.maxLength) {
        object.value = object.value.slice(0, object.maxLength);
    }
}

/**
 * input 길이 자동 조절
 * 사용방법 : widthAutoSet($('#num')) / oninput="this.value = onlyNumber(this.value); widthAutoSet(this);"
 * @param object
 */
function widthAutoSet(object) {
    let defaultWidth = 14;
    let width = $(object).val().length * defaultWidth;

    if (width > object.maxLength * defaultWidth) {
        width = object.maxLength * defaultWidth;
    }
    $(object).css("width", width);
}

/**
 * input 길이 자동 조절
 * div에 input focus
 * 사용방법 : onclick="inputFocusOn('#num')" / <div class="inputWrap" onclick="inputFocusOn('#num')">
 * @param inputTag
 */
function inputFocusOn(inputTag) {
    $(inputTag).focus();
}

/**
 * 브라우저 검색 창에서 엔터 키 클릭 시 검색 공통함수
 * document.ready 내부에 선언
 *     let textarea = document.getElementById('searchWrd1');
 *     textarea.addEventListener('keyup', event => submitTextarea(event, 'getListBeforeProcess'));
 *
 * @param event keyup 이벤트
 * @param callbackFunc 실행할 콜백함수
 */
function submitTextarea(event, callbackFunc) {
    let key = event.key || event.keyCode;

    if (key === 'Enter' || key === 13) {
        if (isFunction(callbackFunc)) {
            window[callbackFunc](1);
        }
    }
}

function toUpperCase() {
    // 대문자만 입력 공통스크립트
    $("input[type=text]").filter(".upper").on("keyup", function () {
        $(this).val($(this).val().toUpperCase());
    });
}

function toLowerCase() {
    // 소문자만 입력 공통스크립트
    $("input[type=text]").filter(".lower").on("keyup", function () {
        $(this).val($(this).val().toLowerCase());
    });
}