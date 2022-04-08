package com.sunits.work_test.controller;

import com.sunits.work_test.common.R;
import com.sunits.work_test.service.IFlowService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2022/4/8--14:52
 * @description: 流程控制器
 */
@RequestMapping("flowService")
@RestController
public class FlowController {

    @Resource
    private IFlowService iFlowService;

    @RequestMapping("deployProcess")
    public R<Void> deployProcess(@RequestParam("file") MultipartFile multipartFile) {
        iFlowService.deployProcess(multipartFile);
        return R.ok();
    }
}
