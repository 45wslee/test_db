package xyz.rootlab.app.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.app.member.repository.MemberRepository;
import xyz.rootlab.app.notice.dao.NoticeDAO;
import xyz.rootlab.app.notice.dto.NoticeDto;
import xyz.rootlab.app.notice.dto.NoticeForm;
import xyz.rootlab.app.notice.dto.NoticeSearch;
import xyz.rootlab.app.notice.entity.Notice;
import xyz.rootlab.app.notice.repository.NoticeRepository;
import xyz.rootlab.app.notice.vo.NoticeVO;
import xyz.rootlab.common.file.dao.FileDAO;
import xyz.rootlab.common.file.dto.FileDto;
import xyz.rootlab.common.file.entity.FileEntity;
import xyz.rootlab.common.file.enums.ReferenceTable;
import xyz.rootlab.common.file.repository.FileRepository;
import xyz.rootlab.common.file.vo.FileVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 공지사항 관리를 위한 Service 클래스
 *
 * @author 김태형
 * @version 1.0
 * @since 2023.01.25
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 * 수정일         수정자      수정내용
 * -----------  -------   ----------------------
 * 2023.01.25   김태형      최초생성
 *
 *
 * </pre>
 */
@Service("noticeService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    private final MemberRepository memberRepository;

    private final FileRepository fileRepository;

    private final NoticeDAO noticeDAO;

    private final FileDAO fileDAO;

    /**
     * 공지사항 등록 - JPA
     *
     * @param notice - Notice entity
     */
    @Transactional
    public void saveNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    /**
     * 공지사항 목록 조회 - JPA
     *
     * @return 조회 결과
     * @throws Exception
     */
    @Override
    public List<NoticeDto> getNoticeList() throws Exception {
        return noticeRepository.findAll().stream().map(notice -> {
            String author = memberRepository.findById(notice.getRgtrSn()).orElseThrow(IllegalArgumentException::new).getMbrNm();
            return NoticeDto.fromEntity(notice, author);
        }).collect(Collectors.toList());
    }

    /**
     * 공지사항 목록 조회 - JPA(검색, 페이징)
     *
     * @param pageable - 페이징
     * @param noticeSearch - 검색 조건
     * @return 조회 결과
     * @throws Exception
     */
    @Override
    public Page<NoticeDto> getNoticeList(Pageable pageable, NoticeSearch noticeSearch) throws Exception {
        Page<Notice> page = noticeRepository.findAll(pageable);

        return page.map(notice -> {
            String author = memberRepository.findById(notice.getRgtrSn()).orElseThrow(IllegalArgumentException::new).getMbrNm();
            return NoticeDto.fromEntity(notice, author);
        });
    }

    /**
     * 공지사항 상세조회 - JPA
     *
     * @param ntcSn - 일련번호
     * @return 조회 결과
     * @throws Exception
     */
    @Override
    public NoticeDto getNoticeDetail(Long ntcSn) throws Exception {
        Notice findNotice = noticeRepository.findById(ntcSn).orElseThrow(() -> new IllegalArgumentException("Not Found"));
        String author = memberRepository.findById(findNotice.getRgtrSn()).orElseThrow(IllegalArgumentException::new).getMbrNm();
        NoticeDto noticeDto = NoticeDto.fromEntity(findNotice, author);

        List<FileEntity> fileEntityList = fileRepository.findByRfrncSnAndRfrncTbl(ntcSn, ReferenceTable.NOTICE);
        List<FileDto> fileList = fileEntityList.stream().map(FileDto::fromEntity).collect(Collectors.toList());
        noticeDto.setFileList(fileList);

        return noticeDto;
    }

    /**
     * 공지사항 수정 - JPA
     *
     * @param noticeForm - 수정 데이터
     * @param member - 회원 Entity
     * @return 수정 결과
     * @throws Exception
     */
    @Override
    @Transactional
    public Notice updateNotice(NoticeForm noticeForm, Member member) throws Exception {
        // 1. 기존 데이터 조회
        Notice notice = noticeRepository.findById(noticeForm.getNtcSn()).orElseThrow(IllegalAccessError::new);

        Member createdBy = memberRepository.findById(notice.getRgtrSn()).orElseThrow(IllegalArgumentException::new);

        // 2. 권한 확인
        if(!createdBy.equals(member)) {
            throw new SecurityException();
        }

        // 3. 수정
        notice.updateNotice(noticeForm);

        return notice;
    }

    /**
     * 공지사항 등록 - MyBatis
     *
     * @param noticeVO - Notice vo
     */
    @Override
    public void saveNoticeWithMyBatis(NoticeVO noticeVO) {
        noticeDAO.insertNotice(noticeVO);
    }

    /**
     * 공지사항 상세 조회 - MyBatis
     *
     * @param ntcSn - 일련번호
     * @return 조회 결과
     * @throws Exception
     */
    @Override
    public NoticeDto getNoticeDetailWithMyBatis(Long ntcSn) throws Exception {
        NoticeDto noticeDto = noticeDAO.selectNotice(ntcSn);

        FileVO fileVO = new FileVO();
        fileVO.setRfrncSn(ntcSn);
        fileVO.setRfrncTbl(ReferenceTable.NOTICE);

        List<FileDto> fileList = fileDAO.selectFileList(fileVO);

        noticeDto.setFileList(fileList);

        return noticeDto;
    }
}
