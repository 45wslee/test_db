/**
 * (CKEDITOR) CKEDITOR 이미지 업로드 소스코드
 *
 * @author LEE Hyeon Ki
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 * 수정일      수정자           수정내용
 * -------    -------------    ----------------------
 * 2023. 1. 26.   LEE Hyeon Ki      최초생성
 * @since 2023.01.26
 */

package xyz.rootlab.common.web;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import xyz.rootlab.common.file.service.FileService;
import xyz.rootlab.common.file.vo.FileVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FileUploadController {

    private final FileService fileService;

    private String allowImgExtList = ".gif.jpg.jpeg.png.svg";

    @ResponseBody
    @RequestMapping(value = "/common/ckeditorImageUpload", method = RequestMethod.POST)
    public byte[] ckeditorImageUpload(MultipartHttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = null;
        FileVO vo = null;
        Map<String, String> result = new HashMap<>();
        try {
            //이미지 업로드
            List<FileVO> list = fileService.uploadFilesForCkeditor(request);

            if (list.size() > 0) {
                vo = list.get(0); // 첫번째 이미지
                System.out.println(">> request.getRequestURI : " + request.getRequestURI());
                url = request.getContextPath() + "/common/ckeditorImageSrc?" + "path=" + vo.getCkSubDirNm() + "&physical=" + vo.getSrvrFileNm() + "&contentType=" + vo.getCkContentType();
            }

            result.put("url", url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().toJson(result).getBytes("UTF-8");
    }

    @RequestMapping(value = "/common/ckeditorImageSrc", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(request.getParameter("path"));
        System.out.println(request.getParameter("physical"));
        System.out.println(request.getParameter("contentType"));

        String subPath = nullToString(request.getParameter("path"));
        String physical = nullToString(request.getParameter("physical"));
        String mimeType = nullToString(request.getParameter("contentType"));

        System.out.println(">> subPath : " + subPath);
        System.out.println(">> physical : " + physical);
        System.out.println(">> mimeType : " + mimeType);

        if (subPath.indexOf("..") >= 0) throw new Exception("Security Exception - illegal url called.");
        if (physical.indexOf("..") >= 0) throw new Exception("Security Exception - illegal url called.");

        String ext = "";
        if (physical.lastIndexOf(".") > 0) {
            ext = physical.substring(physical.lastIndexOf(".") + 1, physical.length()).toLowerCase();
        }
        if (ext == null) {
            System.out.println(">> ext is null");
            throw new FileNotFoundException();
        }

        if (allowImgExtList.indexOf(ext) >= 0) {
            //EgovFormBasedFileUtil.viewFile(response, uploadDir, subPath, physical, mimeType);
            fileService.getCkeditorFile(response, subPath, physical, mimeType);
        } else {
            System.out.println(">> allowImgExtList error");
            throw new FileNotFoundException();
        }
    }

    private String nullToString(String input) throws Exception {
        return input != null ? input : "";
    }
}
