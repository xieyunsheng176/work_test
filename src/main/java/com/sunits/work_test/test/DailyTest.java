package com.sunits.work_test.test;

import com.sunits.work_test.entity.Emp;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class DailyTest {

    @Value("${file.upload.uploadDir}")
    private String uploadUrl;

    public static void main1(String[] args) {
        String expirationDate = null;
        Map map = new HashMap();
        map.put("judgeExpirationDate",(null == expirationDate
                || (!map.containsKey("expirationDate") && 1!=1)));
        System.out.println(map);
        LocalDate parse = LocalDate.parse("2021-06-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long between = ChronoUnit.DAYS.between(parse, LocalDate.now());
        System.out.println(between);
        System.out.println(LocalDate.now().toEpochDay()-parse.toEpochDay());
        Map<String,Object> aa=  new HashMap<>();
        Emp aaa = (Emp)aa.get("aaa");
        System.out.println(aaa);
    }

    @Test
    public  void getFileDir() {
        String relativelyPath=System.getProperty("user.dir");
        System.out.println(relativelyPath);
        String basePath = null;
        String fileDir = null;
        try {
            basePath = ResourceUtils.getURL("classpath:").getPath();

            // 根路径，在 resources/static/upload
            fileDir = ResourceUtils.getURL("classpath:").getPath() + "static/upload/" + (StringUtils.isNotBlank(uploadUrl) ? (uploadUrl + "/") : "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(basePath);
        System.out.println(fileDir);


        //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
        //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
      /*  String path= Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("static")).getPath();
        System.out.println("------------------------------------------------"+path);*/
    }
}
