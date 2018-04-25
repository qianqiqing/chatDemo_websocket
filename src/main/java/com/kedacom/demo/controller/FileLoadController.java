package com.kedacom.demo.controller;

import com.kedacom.demo.common.utils.FileOperateUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 文件操作控制器
 * @Author 钱其清
 */
@Controller
@RequestMapping ("/fileLoad")
public class FileLoadController {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * 下载文件
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping (value = "/downLoad")
    @ResponseBody
    public void downLoad(HttpServletRequest request, HttpServletResponse response, @RequestParam String filePath) {
        try {
            filePath = java.net.URLDecoder.decode(URLDecoder.decode(filePath,"UTF-8"));
            FileOperateUtil.downLoad(request, response, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param file
     * @param session
     * @return
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
    @ResponseBody
    public String uploadImage(@RequestParam("file") CommonsMultipartFile file,
                              HttpSession session) throws UnsupportedEncodingException {
        String imagePath = FileOperateUtil.uploadFile(file, session);
        return imagePath;
    }
}
