package com.sunits.work_test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sunits.work_test.entity.Emp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xys
 * @since 2021-06-03
 */
public interface EmpService extends IService<Emp> {

    IPage<Emp> selectPage(IPage<Emp> pageInfo, QueryWrapper<Emp> empQueryWrapper);

    List<Map<String, Object>> getEmpGroupByDate(LocalDate startDate, LocalDate endDate);
}
