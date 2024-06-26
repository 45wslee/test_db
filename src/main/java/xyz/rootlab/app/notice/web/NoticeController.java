package xyz.rootlab.app.notice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.app.member.service.MemberService;
import xyz.rootlab.app.notice.dto.NoticeDto;
import xyz.rootlab.app.notice.dto.NoticeForm;
import xyz.rootlab.app.notice.dto.NoticeSearch;
import xyz.rootlab.app.notice.entity.Notice;
import xyz.rootlab.app.notice.service.NoticeService;
import xyz.rootlab.app.notice.vo.NoticeVO;
import xyz.rootlab.common.file.enums.ReferenceTable;
import xyz.rootlab.common.file.service.FileService;
import xyz.rootlab.common.file.vo.DeleteInfo;
import xyz.rootlab.common.file.vo.UploadInfo;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;
import xyz.rootlab.common.utils.CommonFnc;
import xyz.rootlab.common.utils.FileUtils;
import xyz.rootlab.common.utils.LocaleMessageSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 공지사항 관리를 위한 Controller 클래스
 *
 * @author 김태형
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 * 수정일         수정자      수정내용
 * -----------  -------   ----------------------
 * 2023.01.25   김태형      최초생성
 *
 *
 * </pre>
 * @since 2023.01.25
 */
@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final MemberService memberService;
    private final NoticeService noticeService;
    private final FileService fileService;
    private final LocaleMessageSource messageSource;

    @Autowired
    Environment env;

    /**
     * 등록 화면
     *
     * @return - view
     * @throws Exception
     */
    @GetMapping("/notice/new")
    public String createForm(Locale locale) throws Exception {
        System.out.println(">>> Test MessageSource : " + messageSource.getMessage("info.test.message", null, locale));
        return "admin/notice/createForm";
    }

    /**
     * 목록 화면
     *
     * @param noticeSearch - 검색 조건
     * @return view
     * @throws Exception
     */
    @GetMapping("/notice")
    public String noticeListPage(@ModelAttribute NoticeSearch noticeSearch) throws Exception {
        return "admin/notice/list";
    }

    /**
     * 공지사항 목록 조회 API - JPA
     *
     * @param pageable     - paging 을 위한 클래스
     * @param noticeSearch - 검색조건
     * @return - Page
     * @throws Exception
     */
    @PostMapping("/notice")
    @ResponseBody
    public Page<NoticeDto> noticeList(Pageable pageable, @ModelAttribute NoticeSearch noticeSearch) throws Exception {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(new Sort.Order(Sort.Direction.DESC, "mdfrDt"));
        return noticeService.getNoticeList(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sorts)), noticeSearch);
    }

    /**
     * 공지사항 상세 조회 화면
     *
     * @param ntcSn - 공지사항 일련번호
     * @param model - 모델
     * @return view
     * @throws Exception
     */
    @GetMapping("/notice/{ntcSn}")
    public String noticeDetail(@PathVariable Long ntcSn, ModelMap model) throws Exception {
        NoticeDto notice = noticeService.getNoticeDetail(ntcSn);
        model.addAttribute("notice", notice);
        return "admin/notice/detail";
    }

    /**
     * 공지사항 등록 API - JPA
     *
     * @param noticeForm - 등록 데이터
     * @param session    - 세션
     * @return Response
     * @throws Exception
     */
    @PostMapping("/notice/new")
    @ResponseBody
    public Response create(@ModelAttribute @Validated NoticeForm noticeForm, HttpSession session) throws Exception {
        // 1. 로그인 사용자 정보 조회
        Member loginMember = memberService.getLoginMember(session);

        // 2. entity 생성
        Notice notice = noticeForm.toEntity();

        // 3. 공지사항 등록
        noticeService.saveNotice(notice);

        // 4. 파일 업로드
        if (!FileUtils.isEmpty(noticeForm.getFiles())) {
            String rootPath = env.getProperty("spring.servlet.multipart.location").toString();
            UploadInfo info = new UploadInfo(rootPath);
            info.setFiles(FileUtils.getFiles(noticeForm.getFiles()));
            info.setRfrncTbl(ReferenceTable.NOTICE);
            info.setRfrncSn(notice.getNtcSn());

            fileService.uploadFiles(info);
        }

        return ResponseHandler.success();
    }

    /**
     * 공지사항 수정 API - JPA
     *
     * @param noticeForm - 수정 데이터
     * @param session    - 세션
     * @return Response
     * @throws Exception
     */
    @PostMapping("/notice/update")
    @ResponseBody
    public Response update(@ModelAttribute @Validated NoticeForm noticeForm, HttpSession session) throws Exception {
        // 1. 로그인 사용자 정보 조회
        Member loginMember = memberService.getLoginMember(session);

        // 2. 업데이트
        Notice notice = noticeService.updateNotice(noticeForm, loginMember);

        // 3. 파일 업로드
        if (!FileUtils.isEmpty(noticeForm.getFiles())) {
            String rootPath = env.getProperty("spring.servlet.multipart.location").toString();
            UploadInfo info = new UploadInfo(rootPath);
            info.setFiles(FileUtils.getFiles(noticeForm.getFiles()));
            info.setRfrncTbl(ReferenceTable.NOTICE);
            info.setRfrncSn(notice.getNtcSn());
            info.setRgtrSn(loginMember.getMbrSn());

//            fileService.uploadFiles(info);
            fileService.uploadFilesWithMyBatis(info);
        }

        // 4. 파일 삭제
        if (noticeForm.getDeleteFileSnList() != null) {
            DeleteInfo deleteInfo = new DeleteInfo();
            deleteInfo.setDeleteList(noticeForm.getDeleteFileSnList());
            deleteInfo.setRfrncTbl(ReferenceTable.NOTICE);
            deleteInfo.setRfrncSn(notice.getNtcSn());
            deleteInfo.setRgtrSn(loginMember.getMbrSn());

//            fileService.deleteFiles(deleteInfo);
            fileService.deleteFilesWithMyBatis(deleteInfo);
        }

        return ResponseHandler.success();
    }

    /**
     * 공지사항 등록 API - MyBatis
     *
     * @param noticeForm - 등록 데이터
     * @param session    - 세션
     * @return Response
     * @throws Exception
     */
    @PostMapping("/notice/mybatis/new")
    @ResponseBody
    public Response createWithMyBatis(@ModelAttribute @Validated NoticeForm noticeForm, HttpSession session) throws Exception {
        // 1. 로그인 사용자 정보 조회
        Member loginMember = memberService.getLoginMember(session);

        // 2. vo 생성
        NoticeVO noticeVO = noticeForm.toVo(loginMember.getMbrSn());

        // 3. 공지사항 등록
        noticeService.saveNoticeWithMyBatis(noticeVO);

        // 4. 파일 업로드
        if (!FileUtils.isEmpty(noticeForm.getFiles())) {
            String rootPath = env.getProperty("spring.servlet.multipart.location").toString();
            UploadInfo info = new UploadInfo(rootPath);
            info.setFiles(FileUtils.getFiles(noticeForm.getFiles()));
            info.setRfrncTbl(ReferenceTable.NOTICE);
            info.setRfrncSn(noticeVO.getNtcSn());
            info.setRgtrSn(loginMember.getMbrSn());

            fileService.uploadFilesWithMyBatis(info);
        }

        return ResponseHandler.success();
    }

    /**
     * 공지사항 상세조회 화면 - MyBatis
     *
     * @param ntcSn - 일련번호
     * @param model - 모델
     * @return view
     * @throws Exception
     */
    @GetMapping("/notice/mybatis/{ntcSn}")
    public String noticeDetailWithMyBatis(@PathVariable Long ntcSn, ModelMap model) throws Exception {
        NoticeDto notice = noticeService.getNoticeDetailWithMyBatis(ntcSn);
        model.addAttribute("notice", notice);
        return "notice/detail";
    }

    /**
     * 공지사항 엑셀 일괄 다운로드
     *
     * @param pageable     - 페이징
     * @param noticeSearch - 검색조건
     * @param request      - 요청
     * @param response     - 응답
     * @throws Exception
     */
    @GetMapping("/notice/excel")
    public void noticeExcel(Pageable pageable, NoticeSearch noticeSearch, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = "공지사항";
        String sheetName = "공지사항";
        List<NoticeDto> noticeList = noticeService.getNoticeList();

        int columnTitleLength = 4;
        String[] columnTitle = new String[columnTitleLength];
        columnTitle[0] = "번호";
        columnTitle[1] = "제목";
        columnTitle[2] = "내용";
        columnTitle[3] = "작성자";

        String[][] content = new String[noticeList.size()][columnTitleLength];
        for (int i = 0; i < noticeList.size(); i++) {
            NoticeDto notice = noticeList.get(i);
            content[i][0] = String.valueOf(noticeList.size() - i);
            content[i][1] = String.valueOf(notice.getNtcTtl());
            content[i][2] = String.valueOf(notice.getNtcCn());
            content[i][3] = String.valueOf(notice.getAuthor());
        }
        CommonFnc.downloadExcel(request, response, fileName, sheetName, columnTitle, content);
    }
}