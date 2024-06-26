package xyz.rootlab.common.file.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import xyz.rootlab.common.file.entity.FileEntity;
import xyz.rootlab.common.file.vo.DeleteInfo;
import xyz.rootlab.common.file.vo.FileVO;
import xyz.rootlab.common.file.vo.UploadInfo;

public interface FileService {
    void uploadFiles(UploadInfo uploadInfo) throws Exception;
    void deleteFiles(DeleteInfo deleteInfo) throws Exception;
    FileEntity getFile(Long fileSeq) throws Exception;

    void uploadFilesWithMyBatis(UploadInfo uploadInfo) throws Exception;
    void deleteFilesWithMyBatis(DeleteInfo deleteInfo) throws Exception;
    FileVO getFileWithMyBatis(Long fileSeq) throws Exception;
    
    List<FileVO> uploadFilesForCkeditor(MultipartHttpServletRequest request) throws Exception;
    void getCkeditorFile(HttpServletResponse response, String serverSubPath, String physicalName, String mimeTypeParam) throws Exception;
}
