'use strict';

/**
 * GET 요청
 * @param url
 * @returns {Promise<any>}
 */
async function fetchGet(url = '', data) {
    url = makeApiUrl(url);
    let options = {
        method: "GET",
        mode: "cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
            "ajax": "true"
        },
        redirect: "follow",
        referrerPolicy: "no-referrer"
    }

    if(typeof data === 'object') { // json
        let queryString = Object.entries(data).map(e => e.join('=')).join('&');
        url = url + "?" + queryString;
    } else if (typeof data === 'string') { // form
        url = url + "?" + converter.serialize(document.getElementById(data));
    }

    const response = await fetch(url, options);
    return response.json();
}

/**
 * Post 요청
 * data 를 object 로 받아 body 에 넣은 후 application/json 으로 요청
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
async function fetchPostJson(url = '', data = {}) {
    url = makeApiUrl(url);
    let options = {
        method: "POST",
        mode: "cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
            "Content-Type": "application/json",
            "ajax": "true"
        },
        redirect: "follow",
        referrerPolicy: "no-referrer",
        body: JSON.stringify(data)
    }

    const response = await fetch(url, options);
    return response.json();
}

/**
 * Post 요청
 * data 가 formId 일 경우 form 내에 있는 데이터를 추가하여 application/x-www-form-urlencoded 요청
 *
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
async function fetchPostForm(url = '', data) {
    url = makeApiUrl(url);
    let transferredData;
    if (typeof data === 'string') {
        // transferredData = $('#' + data).serialize();
        transferredData = converter.serialize(document.getElementById(data));
    } else {
        transferredData = new URLSearchParams(data);
    }

    let options = {
        method: "POST",
        mode: "cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "ajax": "true"
        },
        redirect: "follow",
        referrerPolicy: "no-referrer",
        body: transferredData
    }

    const response = await fetch(url, options);
    return response.json();
}

/**
 * fetch API 의 파일 전송 버전
 *
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
const fetchMultipartFile = async (url = '', data = {}) => {
    url = makeApiUrl(url);
    let options = {
        method: "POST",
        mode: "cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
            "ajax": "true"
        },
        redirect: "follow",
        referrerPolicy: "no-referrer",
        body: data
    }

    const response = await fetch(url, options);
    return response.json();
}

/**
 * formId 를 인자로 받아 FormData 를 생성한다. 생성한 FormData 는 fetchMultipartFile 의 인자로 전달한다.
 * @param formId
 * @returns {FormData}
 */
const makeFormData = (formId) => {
    let data = new FormData();
    let form = document.getElementById(formId);

    let tempNames = [];

    // input
    for (let input of form.getElementsByTagName("input")) {
        if (input.type === 'file') {
            for (let file of input.files) {
                data.append(input.name, file);
            }
        } else if (input.type === 'radio' || input.type === 'checkbox') {
            if (!tempNames.includes(input.name)) {
                tempNames.push(input.name);
                for (let element of document.querySelectorAll(`input[name="${input.name}"]`)) {
                    if (element.checked) {
                        data.append(element.name, element.value);
                    }
                }
            }
        } else {
            data.append(input.name, input.value);
        }
    }

    // select
    for (let select of form.getElementsByTagName("select")) {
        data.append(select.name, select.value);
    }

    return data;
}
const converter = {
    serialize: (form) => {
        // Setup our serialized data
        let serialized = [];

        // Loop through each field in the form
        for (let i = 0; i < form.elements.length; i++) {

            const field = form.elements[i];

            // Don't serialize fields without a name, submits, buttons, file and reset inputs, and disabled fields
            if (!field.name || field.disabled || field.type === 'file' || field.type === 'reset' || field.type === 'submit' || field.type === 'button') continue;

            // If a multi-select, get all selections
            if (field.type === 'select-multiple') {
                for (let n = 0; n < field.options.length; n++) {
                    if (!field.options[n].selected) continue;
                    serialized.push(encodeURIComponent(field.name) + "=" + encodeURIComponent(field.options[n].value));
                }
            }

            // Convert field data to a query string
            else if ((field.type !== 'checkbox' && field.type !== 'radio') || field.checked) {
                serialized.push(encodeURIComponent(field.name) + "=" + encodeURIComponent(field.value));
            }
        }

        return serialized.join('&');
    },
    formToObject: (form) => {
        let data = new FormData(form);
        let obj = {};
        for (let [key, value] of data) {
            if (obj[key] !== undefined) {
                if (!Array.isArray(obj[key])) {
                    obj[key] = [obj[key]];
                }
                obj[key].push(value);
            } else {
                obj[key] = value;
            }
        }
        return obj;
    },
}

function convertParamReverse(paramStr) {
    var temp = paramStr.substr(paramStr.indexOf("?")+1);
    return JSON.parse('{"' + decodeURI(temp.replace(/&/g, "\",\"").replace(/=/g,"\":\"")) + '"}')
}
