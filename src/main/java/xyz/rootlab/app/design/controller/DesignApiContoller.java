package xyz.rootlab.app.design.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import xyz.rootlab.app.design.service.DesignService;
import xyz.rootlab.app.member.entity.Member;
import xyz.rootlab.app.notice.vo.NoticeVO;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseCode;
import xyz.rootlab.common.response.ResponseHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Design", description = "Design API")
public class DesignApiContoller {

    @Autowired
    private WebClient webClient;

    private final DesignService designService;

    @RequestMapping(value = "/api/table/tableTestList", method = RequestMethod.POST)
    @Operation(summary = "Get member profile", description = "특정 멤버의 상세 정보를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = Member.class))}),
            @ApiResponse(responseCode = "404", description = "해당 ID의 유저가 존재하지 않습니다."),

    })
    public Response tableList(HttpServletRequest request, ModelMap model) throws Exception {
        return ResponseHandler.success();
    }

    /**
     * @param request  HttpServletRequest
     * @param memberId 멤버 ID
     * @param loginId  dbwj ID
     * @return
     */
    @RequestMapping(value = "/api/table/tableListT", method = RequestMethod.POST)
    @Operation(summary = "Get member profile", description = "특정 멤버의 상세 정보를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = Member.class))}),
            @ApiResponse(responseCode = "404", description = "해당 ID의 유저가 존재하지 않습니다."),
    })
    public Response getMemberProfile(
            HttpServletRequest request,
            @PathVariable
            @Positive(message = "유저 ID는 양수입니다.")
            @Schema(description = "Member ID", example = "1")
            Long memberId,

            // TODO: Replace with member ID from JWT or that from any other authentication method
            @Parameter(name = "loginId", description = "로그인 유저 ID 값", example = "3", required = true)
            @Positive(message = "유저 ID는 양수입니다.") @RequestParam final Long loginId
    ) {
        return ResponseHandler.success();
    }

    // 게시판 상세보기
    @RequestMapping(value = "/api/table/tableList", method = RequestMethod.GET)
    public Response tableList(HttpServletRequest request, NoticeVO noticeVO) {
        return designService.tableList(request, noticeVO);
    }

    // 게시판 목록 조회
    @RequestMapping(value = "/api/table/tableDetail", method = RequestMethod.GET)
    public Response tableDetail(HttpServletRequest request, NoticeVO noticeVO) {
        return designService.tableDetail(request, noticeVO);
    }

    // CORS 테스트 API
    @RequestMapping(value = "/api/corsTest", method = RequestMethod.GET)
    public String test() {
        return "테스트입니다.";
    }

    @ResponseBody
    @RequestMapping(value = "/webClientGetTest")
    public Response webClientGetTest(HttpServletRequest request, ModelMap model) throws Exception {
        System.out.println("================== webClientGetTest Start ==================");
        Map<String, Object> rtn = new HashMap<>();
        try {
            rtn.put("test", "테스트 데이터");
            System.out.println("================== webClientGetTest End ==================");
            return ResponseHandler.success(rtn);
        } catch (Exception e) {
            return ResponseHandler.error(ResponseCode.FAIL_PROCESS);
        }
    }

    @ResponseBody
    @PostMapping(value = "/webClientPostTest")
    public Response webClientPostTest(HttpServletRequest request, ModelMap model) throws Exception {
        System.out.println("================== webClientPostTest Start ==================");
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("테스트 데이터", "테스트 데이터");
        System.out.println("================== webClientPostTest End ==================");
        return ResponseHandler.success(rtn);
    }

    @ResponseBody
    @GetMapping(value = "/webClientTest")
    public Response webClientTest(HttpServletRequest request, ModelMap model) throws Exception {
        System.out.println("================== webClientTest Start ==================");
        Map<String, Object> rtn = new HashMap<>();
        var responseEntity = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("localhost:8040/webClientGetTest")
                        .build())
                .retrieve()
                .bodyToMono(Map.class).block();
        System.out.println("================== webClientTest End ==================");

        Integer code = (Integer) responseEntity.get("code");
        if (code == 1000) {
            Object map = responseEntity.get("data");
            rtn.put("data", map);
            return ResponseHandler.success(rtn);
        } else {
            Object msg = responseEntity.get("msg");
            rtn.put("msg", msg);
            return ResponseHandler.success(rtn);
        }
    }
}
