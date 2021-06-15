package com.yss.platform;

import com.yss.pojo.UploadFileStatus;
import com.yss.utils.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileClientApplicationTests {

    @Test
    public void uploadFileTest() throws FileNotFoundException {

        // 要上传的文件
        File file = new File("C:\\Users\\dell\\Desktop\\恒河平台知识库需求分析.md");
        UploadFileStatus fileStatus = new UploadFileStatus();
        // 上传到服务器后的文件名
        fileStatus.setFileName("test2");
        // 上传到服务器的哪个位置
        fileStatus.setFilePath("/root/myFile/");
        // 文件的大小
        fileStatus.setFileSize(file.length());
        // 文件的类型
        fileStatus.setFileType("png");
        fileStatus.setInputStream(new FileInputStream(file));

        String result = HttpUtils.postFile("http://192.168.101.54:8090/upload", fileStatus);
        System.out.println(result);
    }

}
