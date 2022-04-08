package com.sunits.work_test;

import com.sunits.work_test.entity.Emp;
import com.sunits.work_test.exception.TestException;
import com.sunits.work_test.exception.TestExceptions;
import com.sunits.work_test.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2022/3/10--14:21
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExceptionTest {

    @Resource
    private EmpMapper empMapper;

    @Test
    @Transactional(rollbackFor = TestExceptions.class)
    public void testException() throws Exception {
        Emp emp = Emp.builder()
                .code("111")
                .name("无双城主")
                .build();
        empMapper.insert(emp);
        if (emp.getCode().equals("111")){
            throw new TestException();
        }else{
            throw new TestExceptions();
        }
    }


}
