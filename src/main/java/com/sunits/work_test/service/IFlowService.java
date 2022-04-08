package com.sunits.work_test.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2022/4/8--15:18
 * @description:
 */
public interface IFlowService {
    void deployProcess(MultipartFile multipartFile);
}
