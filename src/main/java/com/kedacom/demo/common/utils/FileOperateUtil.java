package com.kedacom.demo.common.utils;

import com.kedacom.demo.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class FileOperateUtil {

    /**
     * 上传文件
     * @param file
     * @param request
     * @param session
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file,
                                    HttpServletRequest request,
                                    HttpSession session){
        String trueFilePath = null;    //保存在服务器中的路径
        String path = null;// 文件路径
        String type = null;// 文件类型
        User currentUser = (User) session.getAttribute("currentUser");
        if (file != null) {// 判断上传的文件是否为空
            String fileName = file.getOriginalFilename();// 文件原名称
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                // 项目在容器中实际发布运行的根路径下创建loadFiles保存每个session上传的文件
                String uploadPath = request.getSession().getServletContext().getRealPath("/") + "loadFiles/" + currentUser.getId() + "/";
                if (!(new File(uploadPath).exists())) {
                    new File(uploadPath).mkdirs();
                }
                // 自定义的文件名称 ：上传时间+文件名（防止上传文件名相同文件不同的文件覆盖问题）
                String trueFileName = String.valueOf(System.currentTimeMillis()) + "_" + fileName;
                // 设置存放图片文件的路径
                path = uploadPath + trueFileName;
                trueFilePath = "loadFiles/" + currentUser.getId() + "/" + trueFileName;  //保存在数据库中的路径
                // 转存文件到指定的路径
                try {
                    file.transferTo(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return trueFilePath;
    }
}
