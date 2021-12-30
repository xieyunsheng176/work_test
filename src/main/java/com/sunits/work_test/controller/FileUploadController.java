package com.sunits.work_test.controller;

import com.sunits.work_test.common.R;
import com.sunits.work_test.properties.FileProperties;
import com.sunits.work_test.service.EmpService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2021/12/30--14:36
 * @description:
 */
public class FileUploadController {

    @Value("${file.upload.uploadDir}")
    private String uploadUrl;

    @Autowired
    private FileProperties fileProperties;

    @PostMapping(value = "uploadFile")
    @Transactional(rollbackFor = Exception.class)
    public  void uploadFile(@RequestParam("multipartFile") MultipartFile multipartFile, @RequestParam("userId")String userId, @RequestParam("userName")String userName) throws IOException {
        // Normalize file name
        String originalFilename = multipartFile.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // uuid 生成文件名
        String uuid = String.valueOf(UUID.randomUUID());
        // 新的文件名，使用uuid生成文件名
        String basePath = ResourceUtils.getURL("classpath:").getPath();

        // 检查文件夹是否存在
        String fileUrl = userId+"_"+userName+"/";
        File fileExist = new File(basePath+fileUrl);
        // 文件夹不存在，则新建
        if (!fileExist.exists()) {
            fileExist.mkdirs();
        }


        //上传到，项目的路径+配置好的文件夹+用户信息文件夹
        File file2 = new File( System.getProperty("user.dir")+fileProperties.getUploadDir()+fileUrl, originalFilename);
        // 完成文件的上传
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file2);

        /*File file = new File(basePath+fileUrl, originalFilename);
        // 完成文件的上传到classes包下
        multipartFile.transferTo(file);*/

       /* File file2 = new File(uploadUrl+fileUrl, originalFilename);
        String uploadDir=ResourceUtils.getURL("classpath:").getPath()+uploadUrl+fileUrl;
        System.out.println(uploadDir);
        //如果目录不存在，自动创建文件夹
        File dir = new File(uploadDir);
        if(!dir.exists())
        {
            dir.mkdir();
        }
        FileOutputStream fos = new FileOutputStream(file2);
        fos.write(multipartFile.getBytes());
        fos.close();*/
    }

    @GetMapping("getFiles")
    public R<List<String>> getFiles(@RequestParam("userId")String userId, @RequestParam("userName")String userName) {
        return
    }
}
