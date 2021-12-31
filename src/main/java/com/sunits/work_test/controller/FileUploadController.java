package com.sunits.work_test.controller;

import com.sunits.work_test.common.R;
import com.sunits.work_test.config.FileException;
import com.sunits.work_test.entity.FileDirVo;
import com.sunits.work_test.properties.FileProperties;
import com.sunits.work_test.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2021/12/30--14:36
 * @description:
 */
@RestController
@Slf4j
@RequestMapping("fileUpload")
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
    public R<List<FileDirVo>> getFiles(@RequestParam("userId")String userId, @RequestParam("userName")String userName) {
        List<FileDirVo> filesDirs = new ArrayList<>();
        // 检查文件夹是否存在
        String fileUrl = userId+"_"+userName+"/";
        File file = new File(System.getProperty("user.dir")+fileProperties.getUploadDir()+fileUrl);
        if (file.exists()){
            filesDirs = getFile(System.getProperty("user.dir") + fileProperties.getUploadDir() + fileUrl);
        }
        return R.ok("成功获取到对象",filesDirs);
    }

    private List<FileDirVo> getFile(String path) {
        List<FileDirVo> filesDirs = new ArrayList<>();
        // 获得指定文件对象
        File userFile = new File(path);
        // 获得该文件夹内的所有文件
        File[] fileArray = userFile.listFiles();

        for (File file : fileArray) {
            FileDirVo fileDirVo = new FileDirVo();
            if(file.isFile()) {//如果是文件
                fileDirVo.setFileName(file.getName()); //文件名
                fileDirVo.setFileDir(file.getParent());//文件目录
                fileDirVo.setFile(file);
                filesDirs.add(fileDirVo);
                log.info("文件的name:{}",file.getName());
                log.info("文件的parent:{}",file.getParent());
                log.info("文件的path:{}",file.getPath());
                log.info("文件的AbsoluteFile:{}",file.getAbsoluteFile());
                log.info("文件的AbsolutePath:{}",file.getAbsolutePath());
                try {
                    log.info("文件的CanonicalFile:{}",file.getCanonicalFile().getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                log.info("文件的FreeSpace:{}",file.getFreeSpace());
                log.info("文件的ParentFile:{}",file.getParentFile());
                log.info("文件的TotalSpace:{}",file.getTotalSpace());
                log.info("文件的UsableSpace:{}",file.getUsableSpace());
                log.info("--------------------------------");
            }else if(file.isDirectory()){//如果是文件夹
                fileDirVo.setFileDir(file.getPath());//完整路径
                fileDirVo.setFileName(file.getName()); //文件名
                filesDirs.add(fileDirVo);
                getFile(file.getPath());
            }
        }
        /*for(int i=0;i<array.length;i++){
            FileDirVo fileDirVo = new FileDirVo();
            if(array[i].isFile()) {//如果是文件
                fileDirVo.setFileName(array[i].getName()); //文件名
                fileDirVo.setFileDir(array[i].getParent());//文件目录
                fileDirVo.setFile(array[i]);
                filesDirs.add(fileDirVo);
            }else if(array[i].isDirectory()){//如果是文件夹
                fileDirVo.setFileDir(array[i].getPath());//完整路径
                fileDirVo.setFileName(array[i].getName()); //文件名
                filesDirs.add(fileDirVo);
                getFile(array[i].getPath());
            }
        }*/
        return filesDirs;
    }
}
