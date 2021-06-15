package com.yss.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class Version {
    private int vid;
    private int majorVersion;
    private int secondVersion;
    private int fixedVersion;
    private String greekVersion;
}
