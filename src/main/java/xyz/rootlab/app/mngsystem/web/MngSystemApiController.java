package xyz.rootlab.app.mngsystem.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.rootlab.app.mngsystem.service.MngSystemService;
import xyz.rootlab.app.mngsystem.vo.MngSystemVO;
import xyz.rootlab.common.response.Response;

@RestController
@RequiredArgsConstructor
public class MngSystemApiController {

    private final MngSystemService mngSystemService;

    @RequestMapping(value = "/spt/mngSystem/getMemberList", method = RequestMethod.GET)
    public Response memberList(HttpServletRequest request, MngSystemVO mngSystemVO) throws Exception {
        return mngSystemService.memberList(request,mngSystemVO);
    }
}
