package xyz.rootlab.common.ext.log.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xyz.rootlab.common.ext.log.service.ExtApiLogService;
import xyz.rootlab.common.ext.log.vo.ReqBody;
import xyz.rootlab.common.response.Response;
import xyz.rootlab.common.response.ResponseHandler;

// 테스트용도
@RestController
@RequestMapping("/ext/api")
@RequiredArgsConstructor
public class ExtApiController {

    private final ExtApiLogService extApiLogService;

    @GetMapping("/v1/aaa")
    public Response aaa() {
        return ResponseHandler.success();
    }

    @PostMapping(value = "/v1/bbb", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response bbb(@RequestBody ReqBody body) throws Exception {

        System.out.println("body = " + body);

        if (body.getA().equals("error")) {
            throw new SecurityException();
        }

        extApiLogService.ttt(body);

        return ResponseHandler.success();
    }
}
