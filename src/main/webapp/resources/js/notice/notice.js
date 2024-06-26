function saveNotice() {
    callByMultipart('/notice/new', 'saveNoticeCallback', 'frm')
}

function saveNoticeCallback(res) {
    console.log(res);
    if(res.success) {
        goPage('/notice');
    }
}

function saveNoticeWithMyBatis() {
    callByMultipart('/notice/mybatis/new', 'saveNoticeCallback', 'frm')
}

function handleFiles(files) {
    let fileNames = getFileNames(files);

    let temp = '';
    for (let i = 0; i < fileNames.length; i++) {
        temp += `<p>${fileNames[i]}
                    <button type="button" class="btn03 btn_red_line" onclick="removeFileFromFileList('files', ${i}); removeThis(this);">취소</button>
                    <input type="hidden" so
                </p>`;
    }
    $('#file_contents').html(temp);
}

function deleteFile(fileSeq) {

    let deleteInput = document.createElement('input');
    deleteInput.setAttribute("type", "hidden");
    deleteInput.setAttribute("name", "deleteFileSnList");
    deleteInput.setAttribute("value", fileSeq);

    let frm = document.getElementById('frm');
    frm.appendChild(deleteInput);
    console.log(frm);
}

function updateNotice() {
    callByMultipart('/notice/update', 'updateCallback', 'frm');
}

function updateCallback(res) {
    console.log(res);
    if(res.success) {
        location.reload();
    }
}

function removeThis(obj) {
    $(obj).parent().remove();
}