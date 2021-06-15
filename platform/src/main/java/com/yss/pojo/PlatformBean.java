package com.yss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Lookup;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlatformBean {
    private int id;
    private String version;//平台的版本
    private String platformType; //平台的类型
    private String question;
    private String answer;
    private List<String> imageUrls;
}
