package com.sunits.work_test.test;

import com.sunits.work_test.entity.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class DailyTest {
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
        try {
            basePath = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(basePath);
        String path = Class.class.getClassLoader().getResource("/").getPath();
        System.out.println(path);
    }
}
