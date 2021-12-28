package com.sunits.work_test.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2021/12/27--14:05
 * @description:
 */

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "file.upload")
public class FileProperties {

    private String uploadDir;

}