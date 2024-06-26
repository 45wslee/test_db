package xyz.rootlab.common.file.dao;

import xyz.rootlab.common.file.dto.FileDto;
import xyz.rootlab.common.file.vo.FileVO;

import java.util.List;

public interface FileDAO {
    void insertFileList(List<FileVO> fileVOList);

    List<FileDto> selectFileList(FileVO fileVO);

    FileVO selectFile(Long fileSn);

    List<FileVO> selectFileListInFileSn(List<Long> deleteFileSnList);
    void deleteAll(List<Long> deleteFileSnList);

    List<FileVO> selectFileListByRfrncInfo(FileDto fileDto);
}
