package com.sunits.work_test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sunits.work_test.entity.Emp;
import com.sunits.work_test.mapper.EmpMapper;
import com.sunits.work_test.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xys
 * @since 2021-06-03
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    @Resource
    private EmpMapper empMapper;
    @Override
    public IPage<Emp> selectPage(IPage<Emp> pageInfo, QueryWrapper<Emp> empQueryWrapper) {
        return empMapper.selectPage(pageInfo,empQueryWrapper);
    }
}
