package xyz.rootlab.common.file.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import xyz.rootlab.common.file.enums.AllowFileExt;
import xyz.rootlab.common.file.enums.FileAuth;
import xyz.rootlab.common.file.enums.FileType;
import xyz.rootlab.common.file.enums.ReferenceTable;
import xyz.rootlab.common.utils.PropertiesLoader;

import java.io.File;
import java.util.List;

@Data
public class UploadInfo {
    /**
     * 파일 목록
     */
    private List<MultipartFile> files;

    /**
     * 참조테이블
     */
    private ReferenceTable rfrncTbl;

    /**
     * 참조 테이블 일련번호
     */
    private Long rfrncSn;

    /**
     * 파일 저장 경로
     */
    // private String rootPath = PropertiesLoader.getProperties("spring.servlet.multipart.location").toString();
    private String rootPath = null;

    /**
     * 다운로드 권한, Default : ALL
     */
    private FileAuth fileDwnldAuthrtCd = FileAuth.ALL;

    /**
     * 삭제 권한, Default : OWNER
     */
    private FileAuth fileDelAuthrtCd = FileAuth.OWNER;

    /**
     * 파일 확장자 제한
     */
    private AllowFileExt allowFileExt = AllowFileExt.ALL;

    /**
     * 등록자 일련번호
     */
    private Long rgtrSn;

    /**
     * 등록자 ID
     */
    private String rgtrId;

    /**
     * 파일 종류
     */
    private FileType fileType = FileType.NORMAL;

    public UploadInfo(String rootPathParam) {
        this.rootPath = rootPathParam;
    }

    public boolean isValid() {
        return files.size() != 0 && rfrncTbl != null && rfrncSn != null && rootPath != null && fileDwnldAuthrtCd != null && fileDelAuthrtCd != null;
    }

    public String getAbsoluteFilePath() {
        if (!rootPath.endsWith(File.separator)) {
            rootPath += File.separator;
        }
        return rootPath + rfrncTbl.name() + File.separator + rfrncSn + File.separator;
    }
}