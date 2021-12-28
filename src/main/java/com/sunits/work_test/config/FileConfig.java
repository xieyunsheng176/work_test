package com.sunits.work_test.config;

import com.sunits.work_test.properties.FileProperties;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2021/12/27--14:05
 * @description:
 */

@Configuration
@EnableConfigurationProperties(FileProperties.class)
public class FileConfig {

}