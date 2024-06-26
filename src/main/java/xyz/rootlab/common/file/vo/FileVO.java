package xyz.rootlab.common.file.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.rootlab.common.file.enums.FileAuth;
import xyz.rootlab.common.file.enums.ReferenceTable;
import xyz.rootlab.common.vo.ApiDefaultVO;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileVO extends ApiDefaultVO {
    private Long fileSn;

    private Long rfrncSn;

    private ReferenceTable rfrncTbl;

    private String orgnlFileNm;

    private String srvrFileNm;

    private String fileExtnNm;

    private String filePath;

    private Long fileSz;

    private FileAuth fileDwnldAuthrtCd;

    private FileAuth fileDelAuthrtCd;

    /* ckeditor 파일업로드용 서브디렉토리(업로드날짜)명 */
    private String ckSubDirNm;
    /* ckeditor 파일업로드용 contentType */
    private String ckContentType;
    /* 파일 구분 */
    private String fileType;

    public String getAbsolutePath() {
        return filePath + File.separator + srvrFileNm;
    }

}