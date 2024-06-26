$(function () {
    ClassicEditor.create(document.querySelector("#editorCn"), {
        language    : "ko",
        extraPlugins: [ImageUploadAdapterPlugin],
    })
        .then((newEditor) => {
            editor1 = newEditor;
        })
        .catch((error) => {
            console.error(error);
        });
});