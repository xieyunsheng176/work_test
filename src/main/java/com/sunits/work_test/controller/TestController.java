package com.sunits.work_test.controller;

import com.sunits.work_test.entity.Emp;
import org.apache.commons.collections4.CollectionUtils;
import org.ehcache.core.internal.util.CollectionUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2022/3/22--14:19
 * @description:
 */
public class TestController {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {

        String aaaa = "";
        List<String> exchangeList = Arrays.asList(aaaa.split(","));

        if (CollectionUtils.isNotEmpty(exchangeList)){
            System.out.println(true);
            System.out.println(exchangeList.size());
        }

        List<Emp> emps = new ArrayList<>();
        Emp emp = new Emp();
        emp.setCode("111");
        Emp emp2 = new Emp();
        emp2.setCode("222");
        Emp emp3 = new Emp();
        emp3.setCode("111");
        Emp emp4 = new Emp();
        emp4.setCode("111");
        emps.add(emp);
        emps.add(emp2);
        emps.add(emp3);
        emps.add(emp4);
        List<Emp> collect = emps.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                        Comparator.comparing(tc -> tc.getCode())
                                )
                        ), ArrayList::new));
        System.out.println(collect);
        String aaa = "aaa";
        List<String> body = new ArrayList<>();
        List<String> body2 = new ArrayList<>();
        System.out.println(body.addAll(body2));
        System.out.println(body);
        body.add("BBB");
        String s = body.stream().filter(item -> item.equals(aaa)).findAny().get();
        System.out.println(s);
        String format = formatter.format(LocalDate.now());
        System.out.println();


    }
}
