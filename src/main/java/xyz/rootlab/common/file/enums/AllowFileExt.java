package xyz.rootlab.common.file.enums;

import java.util.Arrays;
import java.util.List;

public enum AllowFileExt {
    ALL("제한없음", null),
    IMAGE("이미지", Arrays.asList(FileExt.PNG, FileExt.JPG, FileExt.JPEG, FileExt.GIF, FileExt.BMP)),
    VIDEO("비디오", Arrays.asList(FileExt.AVI, FileExt.WMV, FileExt.MP4, FileExt.WEBM)),
    WORD("워드", Arrays.asList(FileExt.DOC, FileExt.DOCX)),
    PPT("파워포인트", Arrays.asList(FileExt.PPT, FileExt.PPTX)),
    EXCEL("엑셀", Arrays.asList(FileExt.XLS, FileExt.XLSX)),
    OFFICE("오피스", Arrays.asList(FileExt.PDF, FileExt.HWP, FileExt.HWPX, FileExt.XLS, FileExt.XLSX, FileExt.PPT, FileExt.PPTX, FileExt.DOC, FileExt.DOCX))
    ;


    private String title;
    private List<FileExt> allowList;

    AllowFileExt(String title, List<FileExt> allowList) {
        this.title = title;
        this.allowList = allowList;
    }

    public String getTitle() {
        return title;
    }

    public boolean isValid(String ext) {
        if(title.equals("제한없음") && allowList == null) {
            return true;
        }

        for(FileExt type : allowList) {
            if(ext.equalsIgnoreCase(type.getType())) {
                return true;
            }
        }

        return false;
    }

}
