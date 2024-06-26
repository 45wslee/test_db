package xyz.rootlab.common.file.dao;

import org.springframework.stereotype.Repository;
import xyz.rootlab.common.dao.SqlComAbstractDAO;
import xyz.rootlab.common.file.dto.FileDto;
import xyz.rootlab.common.file.vo.FileVO;

import java.util.List;

@Repository
public class FileDAOImpl extends SqlComAbstractDAO implements FileDAO {


    @Override
    public void insertFileList(List<FileVO> fileVOList) {
        insert("FileDAO.insertFileList", fileVOList);
    }

    @Override
    public List<FileDto> selectFileList(FileVO fileVO) {
        return selectList("FileDAO.selectFileList", fileVO);
    }

    @Override
    public FileVO selectFile(Long fileSn) {
        return selectOne("FileDAO.selectFile", fileSn);
    }

    @Override
    public List<FileVO> selectFileListInFileSn(List<Long> deleteFileSnList) {
        return selectList("FileDAO.selectFileListInFileSn", deleteFileSnList);
    }

    @Override
    public void deleteAll(List<Long> deleteFileSnList) {
        delete("FileDAO.deleteAll", deleteFileSnList);
    }

    @Override
    public List<FileVO> selectFileListByRfrncInfo(FileDto fileDto) {
        return selectList("FileDAO.selectFileListByRfrncInfo", fileDto);
    }
}
