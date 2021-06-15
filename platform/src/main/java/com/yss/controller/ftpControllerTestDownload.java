package com.yss.controller;

import com.yss.FtpInteranceEntity;
import com.yss.service.FtpService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;

@RestController
public class ftpControllerTestDownload {
    @Autowired
    FtpService ftpService;
    @Autowired
    FtpInteranceEntity ftpInteranceEntity;
    @GetMapping(value = "/downloadFile1",produces = "application/octet-stream")
    public void downloadFile(   ) throws IOException, ParseException {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        System.out.println("下载已上传文件！");
       /* if (ParametersUtil.isBlank(docId)) {
            ResponseUtil.out(response, 1002, resultGenerator.getFreeResult(ResultCode.PARAM_IS_BLANK).toString());
            return;
        }
        String ftpPath = document.getFilePath();//ftp下文件的存放路径*/
        String ftpPath="/opt/upload/file";
        try (OutputStream out = response.getOutputStream();){
            //配置返回文件名
            //String filename = document.getFileName();//前端需要的文件名，中英文你看着来，我随意
            String filename="恒河平台知识库需求分析.md";
            String extension = FilenameUtils.getExtension(filename).toLowerCase();
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko") ) {
                filename = URLEncoder.encode(filename, "UTF-8");// win10 ie edge 浏览器 和其他系统的ie
                filename = filename.replace("+", "%20");
            } else {
                filename = new String(filename.getBytes("utf-8"), "iso-8859-1");
            }
            //  response.setContentLengthLong(document.getFileSize());
            response.setContentLengthLong(3279);
            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);// 设置文件名
            response.addHeader("fileName",filename);// 设置文件名
            //配置返回相应类型
            // String contype = MimeTypes.getContentType(extension);//application/x-download
            String contype="application/x-download";
            response.setContentType((contype == null?"application/octet-stream":contype)+";charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            ftpService.downloadFileTo(ftpPath,out);
            System.out.println();
            out.flush();
        } catch (Exception e) {
           /* logger.error("下载已上传文件异常:",e);
            ResponseUtil.out(response, 3001, resultGenerator.getFreeResult(ResultCode.API_EXCEPTION).toString());
            return;*/
            System.out.println("下载已上传文件异常");
        }
        //logger.info("下载文件：["+document.getId()+"]-["+document.getTitle()+"]执行结束！");
        System.out.println("文件下载结束");
    }
}
