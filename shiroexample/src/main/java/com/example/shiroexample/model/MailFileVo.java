package com.example.shiroexample.model;

import lombok.Data;

import java.io.File;

@Data
public class MailFileVo {

    /**
     * 1，当作为静态资源时，代表资源的唯一标识，可以插入到 html 内容中，如 img 标签的 src 属性
     * 2，当作为附件时，作为附件的名称
     */
    private String name;

    /**
     * 文件对象，可以是附件也可以是静态资源
     */
    private File file;
}
