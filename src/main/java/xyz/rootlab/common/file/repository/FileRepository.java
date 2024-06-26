package xyz.rootlab.common.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.rootlab.common.file.entity.FileEntity;
import xyz.rootlab.common.file.enums.ReferenceTable;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByRfrncSnAndRfrncTbl(Long refSeq, ReferenceTable refType);
    List<FileEntity> findByFileSnIn(List<Long> deleteSnList);
}
