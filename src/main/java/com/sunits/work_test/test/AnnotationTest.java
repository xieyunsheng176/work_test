package com.sunits.work_test.test;

import com.sunits.work_test.annotation.Info;
import com.sunits.work_test.entity.Person;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnnotationTest {
    public static void main2(String[] args) {
        try {
            //获取Person的Class对象
            Person person = Person.builder().build();
            Class clazz = person.getClass();
            //判断person对象上是否有Info注解
            if (clazz.isAnnotationPresent(Info.class)) {
                System.out.println("Person类上配置了Info注解！");
                //获取该对象上Info类型的注解
                Info infoAnno = (Info) clazz.getAnnotation(Info.class);
                System.out.println("person.name :" + infoAnno.value() + ",person.isDelete:" + infoAnno.isDelete());
            } else {
                System.out.println("Person类上没有配置Info注解！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Integer aaa= null;
        System.out.println(aaa);
        System.out.println(LocalDateTime.parse("2021-12-08 06:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm")));
    }
}