package xyz.rootlab.common.file.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.app.member.repository.MemberRepository;
import xyz.rootlab.common.config.etc.EgovWebUtil;
import xyz.rootlab.common.file.dao.FileDAO;
import xyz.rootlab.common.file.entity.FileEntity;
import xyz.rootlab.common.file.enums.ReferenceTable;
import xyz.rootlab.common.file.repository.FileRepository;
import xyz.rootlab.common.file.vo.DeleteInfo;
import xyz.rootlab.common.file.vo.FileVO;
import xyz.rootlab.common.file.vo.UploadInfo;
import xyz.rootlab.common.utils.FileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final FileDAO fileDAO;
    private final MemberRepository memberRepository;

    @Autowired
    Environment env;

    @Override
    public void uploadFiles(UploadInfo uploadInfo) throws Exception {
        if (!uploadInfo.isValid()) {
            throw new IllegalArgumentException();
        }
        String filePath = uploadInfo.getAbsoluteFilePath();
        File storedPath = new File(EgovWebUtil.filePathBlackList(filePath));
        if (!storedPath.exists() || storedPath.isFile()) {
            if (storedPath.mkdirs()) {
                log.debug("[file.mkdirs] saveFolder : Creation Success ");
            } else {
                throw new SecurityException("[file.mkdirs] Permission Denied");
            }
        }

        List<FileEntity> fileEntityList = new ArrayList<>();
        for (MultipartFile file : uploadInfo.getFiles()) {
            if (StringUtils.isEmpty(file.getOriginalFilename())) {
                continue;
            }

            // 확장자 제한
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!uploadInfo.getAllowFileExt().isValid(ext)) {
                throw new IllegalArgumentException("파일 확장자 제한");
            }

            String srvrFileNm = UUID.randomUUID().toString();

            file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath + File.separator + srvrFileNm)));

            FileEntity fileEntity = FileEntity.builder()
                    .rfrncSn(uploadInfo.getRfrncSn())
                    .rfrncTbl(uploadInfo.getRfrncTbl())
                    .orgnlFileNm(new String(file.getOriginalFilename().getBytes(StandardCharsets.UTF_8)))
                    .srvrFileNm(srvrFileNm)
                    .fileExtnNm(ext)
                    .filePath(filePath)
                    .fileSz(file.getSize())
                    .fileDwnldAuthrtCd(uploadInfo.getFileDwnldAuthrtCd())
                    .fileDelAuthrtCd(uploadInfo.getFileDelAuthrtCd())
                    .build();
            fileEntityList.add(fileEntity);
        }

        fileRepository.saveAllAndFlush(fileEntityList);
    }

    @Override
    public void deleteFiles(DeleteInfo deleteInfo) throws Exception {
        if (!deleteInfo.isValid()) {
            throw new IllegalArgumentException();
        }

        List<FileEntity> deleteFileList = fileRepository.findByFileSnIn(deleteInfo.getDeleteList());
        for (FileEntity file : deleteFileList) {
            // 삭제 권한 확인
            Member member = memberRepository.findById(deleteInfo.getRgtrSn()).orElseThrow(SecurityException::new);
            FileUtils.checkDeleteAuth(file, member);

            try {
                Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 파일을 모두 삭제한 후 DB 처리
        fileRepository.deleteAllById(deleteFileList.stream().map(FileEntity::getFileSn).collect(Collectors.toList()));
    }

    @Override
    public FileEntity getFile(Long fileSeq) throws Exception {
        return fileRepository.findById(fileSeq).<IllegalArgumentException>orElseThrow(() -> {
            throw new IllegalArgumentException("file not exist");
        });
    }

    @Override
    public void uploadFilesWithMyBatis(UploadInfo uploadInfo) throws Exception {
        if (!uploadInfo.isValid()) {
            throw new IllegalArgumentException();
        }
        String filePath = uploadInfo.getAbsoluteFilePath();
        File storedPath = new File(EgovWebUtil.filePathBlackList(filePath));
        if (!storedPath.exists() || storedPath.isFile()) {
            if (storedPath.mkdirs()) {
                log.debug("[file.mkdirs] saveFolder : Creation Success ");
            }
        }

        List<FileVO> fileVOList = new ArrayList<>();
        for (MultipartFile file : uploadInfo.getFiles()) {
            if (StringUtils.isEmpty(file.getOriginalFilename())) {
                continue;
            }

            // 확장자 제한
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!uploadInfo.getAllowFileExt().isValid(ext)) {
                throw new IllegalArgumentException("파일 확장자 제한");
            }

            String srvrFileNm = UUID.randomUUID().toString();

            file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath + File.separator + srvrFileNm)));

            FileVO fileVO = new FileVO();

            fileVO.setRfrncSn(uploadInfo.getRfrncSn());
            fileVO.setRfrncTbl(uploadInfo.getRfrncTbl());
            fileVO.setOrgnlFileNm(new String(file.getOriginalFilename().getBytes(StandardCharsets.UTF_8)));
            fileVO.setSrvrFileNm(srvrFileNm);
            fileVO.setFileExtnNm(ext);
            fileVO.setFilePath(filePath);
            fileVO.setFileSz(file.getSize());
            fileVO.setFileDwnldAuthrtCd(uploadInfo.getFileDwnldAuthrtCd());
            fileVO.setFileDelAuthrtCd(uploadInfo.getFileDelAuthrtCd());
            fileVO.setRgtrSn(uploadInfo.getRgtrSn());
            fileVO.setRgtrDt(LocalDateTime.now());

            fileVOList.add(fileVO);
        }

        fileDAO.insertFileList(fileVOList);
    }

    @Override
    public void deleteFilesWithMyBatis(DeleteInfo deleteInfo) throws Exception {
        if (!deleteInfo.isValid()) {
            throw new IllegalArgumentException();
        }

        List<FileVO> deleteFileList = fileDAO.selectFileListInFileSn(deleteInfo.getDeleteList());
        for (FileVO file : deleteFileList) {

            // 삭제 권한 확인
            Member member = memberRepository.findById(deleteInfo.getRgtrSn()).orElseThrow(SecurityException::new);
            FileUtils.checkDeleteAuth(file, member);

            try {
                Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 파일을 모두 삭제한 후 DB 처리
        fileDAO.deleteAll(deleteInfo.getDeleteList());
    }

    @Override
    public FileVO getFileWithMyBatis(Long fileSeq) throws Exception {
        FileVO file = fileDAO.selectFile(fileSeq);
        if (file == null) {
            throw new IllegalArgumentException("File Not Exist");
        }
        return file;
    }

    @Override
    public List<FileVO> uploadFilesForCkeditor(MultipartHttpServletRequest request) throws Exception {
        String nowDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        Long nowDateLong = Long.parseLong(nowDate);

        String rootPath = env.getProperty("spring.servlet.multipart.location").toString();
        UploadInfo uploadInfo = new UploadInfo(rootPath);
        uploadInfo.setRfrncTbl(ReferenceTable.CKEDITOR);
        uploadInfo.setRfrncSn(nowDateLong);
        uploadInfo.setFiles(request.getFiles("upload"));

        //파일 업로드
        String filePath = uploadInfo.getAbsoluteFilePath();
        File storedPath = new File(EgovWebUtil.filePathBlackList(filePath));
        if (!storedPath.exists() || storedPath.isFile()) {
            if (storedPath.mkdirs()) {
                log.debug("[file.mkdirs] saveFolder : Creation Success ");
            }
        }

        List<FileVO> fileVOList = new ArrayList<>();
        for (MultipartFile file : uploadInfo.getFiles()) {
            if (StringUtils.isEmpty(file.getOriginalFilename())) {
                continue;
            }

            // 확장자 제한
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!uploadInfo.getAllowFileExt().isValid(ext)) {
                throw new IllegalArgumentException("파일 확장자 제한");
            }

            String srvrFileNm = UUID.randomUUID().toString();

            file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath + File.separator + srvrFileNm)));

            FileVO fileVO = new FileVO();

            fileVO.setRfrncSn(uploadInfo.getRfrncSn());
            fileVO.setRfrncTbl(uploadInfo.getRfrncTbl());
            fileVO.setOrgnlFileNm(new String(file.getOriginalFilename().getBytes(StandardCharsets.UTF_8)));
            fileVO.setSrvrFileNm(srvrFileNm);
            fileVO.setFileExtnNm(ext);
            fileVO.setFilePath(filePath);
            fileVO.setFileSz(file.getSize());
            fileVO.setFileDwnldAuthrtCd(uploadInfo.getFileDwnldAuthrtCd());
            fileVO.setFileDelAuthrtCd(uploadInfo.getFileDelAuthrtCd());
            fileVO.setRgtrSn(uploadInfo.getRgtrSn());
            fileVO.setRgtrDt(LocalDateTime.now());

            fileVO.setCkSubDirNm(nowDate);
            fileVO.setCkContentType(file.getContentType());

            fileVOList.add(fileVO);
        }

        fileDAO.insertFileList(fileVOList);

        return fileVOList;
    }

    @Override
    public void getCkeditorFile(HttpServletResponse response, String serverSubPath, String physicalName, String mimeTypeParam) throws Exception {
        String mimeType = mimeTypeParam;
        Long subPath = null;

        if (StringUtils.isNotBlank(serverSubPath)) {
            subPath = Long.parseLong(serverSubPath);
        }

        String rootPath = env.getProperty("spring.servlet.multipart.location").toString();
        UploadInfo uploadInfo = new UploadInfo(rootPath);
        uploadInfo.setRfrncTbl(ReferenceTable.CKEDITOR);
        uploadInfo.setRfrncSn(subPath);

        String ckEditorFileName = uploadInfo.getAbsoluteFilePath() + File.separator + physicalName;

        File file = new File(EgovWebUtil.filePathBlackList(ckEditorFileName));

        if (!file.exists()) {
            throw new FileNotFoundException(ckEditorFileName);
        }

        if (!file.isFile()) {
            throw new FileNotFoundException(ckEditorFileName);
        }

        byte[] b = new byte[8192];

        if (mimeType == null) {
            mimeType = "application/octet-stream;";
        }

        response.setContentType(EgovWebUtil.removeCRLF(mimeType));
        response.setHeader("Content-Disposition", "filename=image;");

        BufferedInputStream fin = null;
        BufferedOutputStream outs = null;

        try {
            fin = new BufferedInputStream(new FileInputStream(file));
            outs = new BufferedOutputStream(response.getOutputStream());

            int read = 0;

            while ((read = fin.read(b)) != -1) {
                outs.write(b, 0, read);
            }
        } finally {
            fin.close();
            outs.close();
        }
    }
}
