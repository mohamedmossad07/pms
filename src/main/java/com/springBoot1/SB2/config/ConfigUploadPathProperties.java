package com.springBoot1.SB2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Setter
@Getter
@Configuration
@ConfigurationProperties("upload-paths")
public class ConfigUploadPathProperties {
    private String basePath;
    private String transactionPath;
    private Integer pathPrefixCount;


}
