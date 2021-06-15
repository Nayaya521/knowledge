package com.yss.pojo;

import lombok.Data;
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
