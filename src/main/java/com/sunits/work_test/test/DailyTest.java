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
import java.util.*;
import java.util.stream.Collectors;

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
    @Test
    public void test() {
        LocalDate checkDate = LocalDate.parse("2022-01-23", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        boolean before = checkDate.plusDays(31).isBefore(LocalDate.now());
        System.out.println(before);
        Integer model = 1;
        List<Long> deptIds = new ArrayList<>();
        deptIds.add(100L);
        deptIds.add(200L);
        Long deptId = 300L;
        if (deptIds.contains(deptId) || model == 1){{
            System.out.println("进来了");
        }}
        String aaa = "其他资料/重大事件说明函数.doc";
        StringBuilder originName = new StringBuilder(aaa);
        System.out.println(originName.substring(originName.lastIndexOf("/") + 1));
        Map<String, Long>  mapResult = new HashMap<>();
        mapResult.put("132",900L);
        Collection<Long> values = mapResult.values();
        List<Long> collect = values.stream().collect(Collectors.toList());
        System.out.println(values);
    }
    @Test
    public void test2() {

        boolean isLeader = true;
        boolean isProjectTrack = false;
        System.out.println(!isLeader || isProjectTrack);

    }

}
