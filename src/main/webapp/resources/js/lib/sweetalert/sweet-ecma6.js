/**
 * ecma6 반영, sweetAlert 경고창
 * @param msg
 * @returns {Promise<*>}
 */
const sweetAlert = async (msg) => {
    return await swal.fire({
        confirmButtonText: '확인',
        html: msg,
        allowOutsideClick: false,
        allowEscapeKey: false
    });
}

/**
 * ecma6 반영, sweetAlert 확인창
 * @param msg
 * @returns {Promise<*>}
 */
const sweetConfirm = async (msg) => {
    return await Swal.fire({
        html: msg,
        showDenyButton: true,
        showCancelButton: false,
        confirmButtonText: '확인',
        denyButtonText: `취소`,
        allowOutsideClick: false,
        allowEscapeKey: false
    });
}