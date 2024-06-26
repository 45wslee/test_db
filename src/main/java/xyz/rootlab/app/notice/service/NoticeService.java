package xyz.rootlab.app.notice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.app.notice.dto.NoticeDto;
import xyz.rootlab.app.notice.dto.NoticeForm;
import xyz.rootlab.app.notice.dto.NoticeSearch;
import xyz.rootlab.app.notice.entity.Notice;
import xyz.rootlab.app.notice.vo.NoticeVO;

import java.util.List;

/**
 * 공지사항 관리를 위한 Service 인터페이스
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
public interface NoticeService {
    /**
     * 공지사항 등록 - JPA
     *
     * @param notice - Notice entity
     */
    void saveNotice(Notice notice);

    /**
     * 공지사항 목록 조회 - JPA
     *
     * @return 조회 결과
     * @throws Exception
     */
    List<NoticeDto> getNoticeList() throws Exception;

    /**
     * 공지사항 목록 조회 - JPA(검색, 페이징)
     *
     * @param pageable - 페이징
     * @param noticeSearch - 검색 조건
     * @return 조회 결과
     * @throws Exception
     */
    Page<NoticeDto> getNoticeList(Pageable pageable, NoticeSearch noticeSearch) throws Exception;

    /**
     * 공지사항 상세조회 - JPA
     *
     * @param ntcSn - 일련번호
     * @return 조회 결과
     * @throws Exception
     */
    NoticeDto getNoticeDetail(Long ntcSn) throws Exception;

    /**
     * 공지사항 수정 - JPA
     *
     * @param noticeForm - 수정 데이터
     * @param member - 회원 Entity
     * @return 수정 결과
     * @throws Exception
     */
    Notice updateNotice(NoticeForm noticeForm, Member member) throws Exception;

    /**
     * 공지사항 등록 - MyBatis
     *
     * @param noticeVO - Notice vo
     */
    void saveNoticeWithMyBatis(NoticeVO noticeVO);

    /**
     * 공지사항 상세 조회 - MyBatis
     *
     * @param ntcSn - 일련번호
     * @return 조회 결과
     * @throws Exception
     */
    NoticeDto getNoticeDetailWithMyBatis(Long ntcSn) throws Exception;
}
