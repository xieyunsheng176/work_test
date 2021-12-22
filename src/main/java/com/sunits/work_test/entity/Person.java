package com.sunits.work_test.entity;

import com.sunits.work_test.annotation.Info;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
// 为Person类配置了刚刚定义的注解@Info
@Info(isDelete = true)
public class Person {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 是否有效
     */
    private boolean isDelete;
}