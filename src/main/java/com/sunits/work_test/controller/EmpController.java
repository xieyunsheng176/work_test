/*package com.sunits.work_test.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunits.work_test.entity.Dept;
import com.sunits.work_test.entity.Emp;
import com.sunits.work_test.service.EmpService;
import com.sunits.work_test.utils.ExcelMergeUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

*//**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xys
 * @since 2021-06-03
 *//*
@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService iEmpService;
    @RequestMapping(value="/getEmp", method= RequestMethod.GET)
    public void getList(HttpServletResponse response) throws Exception {
        Page<Emp> pageInfo = new Page<>(0,100);
        QueryWrapper<Emp> empQueryWrapper  = new QueryWrapper<>();
        IPage<Emp> pages =  iEmpService.selectPage(pageInfo,empQueryWrapper);
        //需要合并的列
        int[] mergeColumeIndex = {0};
        // 从那一行开始合并
        int mergeRowIndex = 1;
        ExcelWriter excelWriter = ExcelMergeUtil.getExcelWriterMerge(response, "work", mergeRowIndex, mergeColumeIndex);
        //业务代码
        for (int i = 0 ; i < pages.getPages();i++){
            List<Emp> lists = pages.getRecords();
            if (!lists.isEmpty()){
                WriteSheet sheetWriter = EasyExcel.writerSheet(1,"sheetName").head(Emp.class).build();
                excelWriter.write(lists,sheetWriter);
            }
        }
//        Method print = Dept.class.getDeclaredMethod("print");
        
        // 千万别忘记finish 会帮忙关闭流
        if (excelWriter != null) {
            excelWriter.finish();
        }
    }

}*/
package com.sunits.work_test.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunits.work_test.config.FileException;
import com.sunits.work_test.entity.Address;
import com.sunits.work_test.entity.Dept;
import com.sunits.work_test.entity.Emp;
import com.sunits.work_test.service.EmpService;
import com.sunits.work_test.service.impl.FileService;
import com.sunits.work_test.utils.ExcelMergeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xys
 * @since 2021-06-03
 */
@RestController
@RequestMapping("/emp")
public class EmpController {


    @Resource
    private EmpService iEmpService;
    @Resource
    private FileService fileService;

    @Value("${file.upload.uploadDir}")
    private String uploadUrl;


    @RequestMapping(value="/getEmp", method= RequestMethod.GET)
    public void getList(HttpServletResponse response) throws Exception {
        Page<Emp> pageInfo = new Page<>(0,100);
        QueryWrapper<Emp> empQueryWrapper  = new QueryWrapper<>();
        IPage<Emp> pages =  iEmpService.selectPage(pageInfo,empQueryWrapper);
        //实例化 ExcelWriter

        //实例化表单
        //需要合并的列
//        int[] mergeColumeIndex = {0};
//        // 从那一行开始合并
//        int mergeRowIndex = 1;
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String excelName = URLEncoder.encode("work", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());

        ExcelWriter build = EasyExcel.write(response.getOutputStream()).build();
        //业务代码
        for (int i = 0 ; i < pages.getPages();i++){
            List<Emp> lists = pages.getRecords();
            if (!lists.isEmpty()){
                WriteSheet sheetWriter = EasyExcel.writerSheet(1,"sheetName").head(Emp.class).build();
                build.write(lists,sheetWriter);
            }
        }
//        Method print = Dept.class.getDeclaredMethod("print");

        // 千万别忘记finish 会帮忙关闭流
        if (build != null) {
            build.finish();
        }
    }
    @PostMapping(value="/requestJson")
    public void requestJson(@RequestBody List<Map<String,List<Integer>>> duixiang) throws Exception {
        String aaa = "[{\"\\u5341\\u91CC\":[17,43,5,51,137,100,0.9,109,0.9,258.7,1022.6,15.2,40,0]},{\"\\u4E94\\u4E03\\u4E8C\\u4E03\\u5382\":[20,42,2,45,134,91,0.8,91,1.8,79.3,1012.5,15.5,42,0]},{\"\\u897F\\u56ED\":[16,38,5,45,140,9985,0.6,102,3.5,41.7,1017.6,14.2,43,-999]},{\"\\u77F3\\u5316\\u603B\\u5382\":[14,33,2,36,147,101,0.8,109,3.4,228.9,1023.4,14.5,48,0]},{\"\\u5E90\\u5C71\\u6C14\\u8C61\\u53F0\":[9,29,1,30,50,38,0.6,123,1,350.4,893.2,0,0,0]},{\"\\u8305\\u5C71\\u5934\":[12,32,3,37,155,123,0.9,103,1.9,203.3,1026,15,52,-999]},{\"\\u5916\\u56FD\\u8BED\\u5B66\\u6821\":[17,31,2,34,121,103,0.7,111,4.1,61,1017.4,14.7,44,0]},{\"\\u7EFC\\u5408\\u5DE5\\u4E1A\\u56ED\":[19,51,7,62,150,132,1.3,92,2.2,0.5,1018,14.7,42,0]}]";
        List<Map> 转换完成的对象 = JSONArray.parseArray(aaa, Map.class);
        转换完成的对象.forEach(具体对象->{
            for (Object 那些地名 : 具体对象.keySet()) {
                System.out.println(那些地名);
                System.out.println(具体对象.get(那些地名));
                List 数字数组 = (List)具体对象.get(那些地名);
                数字数组.forEach(数字->{
                    System.out.println(数字);
                });
            }
        });
    }
    @PostMapping(value="/三级联动")
    public   Map<String, Map<String, List<Address>>> 三级联动(@RequestBody List<Address> addresses) throws Exception {
        Map<String, Map<String, List<Address>>> collect = addresses.stream().collect(groupingBy(Address::getCityName, groupingBy(Address::getCityNameNext)));
      /*  List<Address> records = new ArrayList<Address>;
        collect.keySet().forEach(map -> {
            Map<String, Long> countMap1 = collect.get(map).stream().collect(Collectors.groupingBy(o -> o.getCityNameNext(), Collectors.counting()));
            countMap1(key).stream().forEach(country -> {
                Address address = new Address();
                address.setCityName(productType);
                address.setCityNameNext();
                address.setStationCode(countMap1(key).intValue());
                address.setStationType(countMap1(key).intValue());
                address.setStationName( countMap1(key).intValue());
                records.add(address);
            });
        });*/

        return collect;
    }
    @PostMapping(value = "uploadFile")
    public  void uploadFile(@RequestParam("multipartFile")MultipartFile multipartFile,@RequestParam("userId")String userId,@RequestParam("userName")String userName) throws IOException {
        // Normalize file name
        fileService.storeFile(multipartFile);
        /*   String originalFilename = multipartFile.getOriginalFilename();
        String fileUrl = userId+"/"+userName+"/";
        // 新的文件名，使用uuid生成文件名
        String basePath = ResourceUtils.getURL("classpath:").getPath();
        // 创建新的文件
        File fileExist = new File(basePath+fileUrl);
        // 文件夹不存在，则新建
        if (!fileExist.exists()) {
            fileExist.mkdirs();
        }

        File file = new File(basePath+fileUrl, originalFilename);
        // 完成文件的上传
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



}



