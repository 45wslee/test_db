package xyz.rootlab.common.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rootlab.common.file.entity.FileEntity;
import xyz.rootlab.common.file.enums.FileType;
import xyz.rootlab.common.file.enums.ReferenceTable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
   private Long fileSn;
   private Long rfrncSn;
   private String srvrFileNm;
   private ReferenceTable rfrncTbl;
   private FileType fileType;
   private String orgnlFileNm;
   private Long fileSz;

   public static FileDto fromEntity(FileEntity file) {
      return FileDto.builder()
              .fileSn(file.getFileSn())
              .rfrncSn(file.getRfrncSn())
              .rfrncTbl(file.getRfrncTbl())
              .srvrFileNm(file.getSrvrFileNm())
              .orgnlFileNm(file.getOrgnlFileNm())
              .fileSz(file.getFileSz())
              .build();
   }
}
