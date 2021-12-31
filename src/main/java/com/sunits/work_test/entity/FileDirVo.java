package com.sunits.work_test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2021/12/31--10:54
 * @description:
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDirVo {

    //文件目录
    private String fileDir;

    //文件名称
    private String fileName;

    //文件
    private File file;

}
