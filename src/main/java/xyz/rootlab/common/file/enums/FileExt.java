package xyz.rootlab.common.file.enums;

public enum FileExt {
    PNG("png"),
    JPG("jpg"),
    JPEG("jpeg"),
    GIF("gif"),
    BMP("bmp"),
    AVI("avi"),
    WMV("wmv"),
    MP4("mp4"),
    WEBM("webm"),
    PDF("pdf"),
    XLS("xls"),
    XLSX("xlsx"),
    DOC("doc"),
    DOCX("docx"),
    PPT("ppt"),
    PPTX("pptx"),
    HWP("hwp"),
    HWPX("hwpx");

    private final String type;

    FileExt(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
