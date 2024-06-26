package xyz.rootlab.common.file.entity;

import lombok.*;
import org.hibernate.annotations.Comment;
import xyz.rootlab.common.entity.BaseEntity;
import xyz.rootlab.common.file.enums.FileAuth;
import xyz.rootlab.common.file.enums.ReferenceTable;

import javax.persistence.*;
import java.io.File;


@Entity
@Getter
@Table(name = "tb_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "FILE_SN")
    @Comment("파일 시퀀스")
    private Long fileSn;

    /**
     * 구분자
     */
    @Enumerated(EnumType.STRING)
    private ReferenceTable rfrncTbl;

    @Column(name = "rfrncSn")
    private Long rfrncSn;

    @Column
    @Comment("원본 파일명")
    private String orgnlFileNm;

    @Column
    @Comment("서버에 저장되는 파일명")
    private String srvrFileNm;

    @Column
    @Comment("확장자")
    private String fileExtnNm;

    @Column
    private String filePath;

    @Column
    private Long fileSz;

    /**
     * 다운로드 권한
     */
    @Enumerated(EnumType.STRING)
    private FileAuth fileDwnldAuthrtCd;

    /**
     * 삭제 권한
     */
    @Enumerated(EnumType.STRING)
    private FileAuth fileDelAuthrtCd;

    public String getAbsolutePath() {
        return filePath + File.separator + srvrFileNm;
    }
}
