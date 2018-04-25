package com.kedacom.demo.common.utils;

import com.kedacom.demo.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Arrays;

/**
 * 文件操作公共类
 * @Author 钱其清
 */
public class FileOperateUtil {

    /**
     * 上传文件
     * @param file
     * @param session
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, HttpSession session) {
        long maxSize = 1024*1024*2;
        String path = null;// 文件路径
        String type = null;// 文件类型
        String trueFilePath = null;
        User currentUser = (User) session.getAttribute("currentUser");

        if (file != null) {// 判断上传的文件是否为空
            long fileSize = file.getSize();
            if (fileSize > maxSize) {
                return ConstantDefine.FILE_SIZE_ERROR;
            } else {
                String fileName = file.getOriginalFilename();// 文件原名称
                // 判断文件类型
                type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                if (type == null || !Arrays.asList(ConstantDefine.FILETYPE.split(",")).contains(type)) {
                    return ConstantDefine.FILE_TYPE_ERROR;
                } else {
                    // 项目在容器中实际发布运行的根路径下创建loadFiles保存每个session上传的文件
                    String uploadPath = session.getServletContext().getRealPath(File.separator)
                            + ConstantDefine.LOAD_FILE_PATH
                            + File.separator
                            + currentUser.getId()
                            + File.separator;
                    if (!(new File(uploadPath).exists())) {
                        File uploadPathFile = new File(uploadPath);
                        boolean sucess = uploadPathFile.mkdirs();
                    }
                    // 自定义的文件名称 ：上传时间+文件名（防止上传文件名相同文件不同的文件覆盖问题）
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + "_" + fileName;
                    //保存在数据库中的路径,供页面展示
                    trueFilePath = ConstantDefine.LOAD_FILE_PATH + File.separator + currentUser.getId() + File.separator + trueFileName;
                    // 设置存放图片文件的路径
                    path = uploadPath + trueFileName;
                    // 转存文件到指定的路径
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return trueFilePath;
    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @param fileName
     */
    public static void downLoad(HttpServletRequest request, HttpServletResponse response, String fileName) {
       String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
        String path = rootPath + fileName;
        try {
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            byte[] fileNameBytes = fileName.getBytes("UTF-8");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileNameBytes,"iso-8859-1"));
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
