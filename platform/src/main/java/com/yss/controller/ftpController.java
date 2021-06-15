package com.yss.controller;

import com.yss.FtpInteranceEntity;
import com.yss.service.FtpService;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.UUID;

@RestController
public class ftpController {
    @Autowired
    FtpService ftpService;
    @Autowired
    FtpInteranceEntity ftpInteranceEntity;

    @PostMapping(value = "/submitThesis")
    @Transactional
    public void submitThesis(MultipartFile thesisFile) throws IOException, ParseException {

        System.out.println("接收提交的论文！");
        try {
            if (thesisFile.isEmpty()) {
                System.out.println("内容为空");
                //ResponseUtil.out(response, 1002, resultGenerator.getFreeResult(ResultCode.PARAM_IS_BLANK,"请上传论文文件！").toString());
                return;
            }
            if (thesisFile.getSize() > 838860800) {
                System.out.println("size大于");
                //ResponseUtil.out(response, 4004, resultGenerator.getFreeResult(ResultCode.DOCUMENT_CON_SIZE_UP).toString());
                return;
            }

            String extension1 = FilenameUtils.getExtension(thesisFile.getOriginalFilename()).toLowerCase();
            String uuidName = UUID.randomUUID() + "." + extension1;
            InputStream inputStream = thesisFile.getInputStream();
            String filePath ="/opt/upload/file";
            boolean isSuccess = ftpService.uploadFile(inputStream, uuidName, filePath);
            System.out.println("文件上传ftp文件服务器结果："+isSuccess);
        } catch (Exception e) {
           // logger.error("提交论文执行异常：",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
            System.out.println("提交论文异常");
          //  ResponseUtil.out(response, 3001, resultGenerator.getFreeResult(ResultCode.API_EXCEPTION).toString());
            return;
        }
        System.out.println("正常结束");
        //ResponseUtil.out(response, 400, resultGenerator.getFailResult("提交论文失败！").toString());

    }
    @GetMapping(value = "/downloadFile",produces = "application/octet-stream")
    public void downloadFile(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
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
            String filename="a2d86940-23b5-4b6b-bedd-92b52559d67e.yml";
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
            response.addHeader("Content-Disposition", "inline;fileName=" + filename);// 设置文件名
            response.addHeader("fileName",filename);// 设置文件名
            //配置返回相应类型
           // String contype = MimeTypes.getContentType(extension);//application/x-download
            String contype="application/x-download";
            response.setContentType((contype == null?"application/octet-stream":contype)+";charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            ftpService.downloadFileTo(ftpPath,out);
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
