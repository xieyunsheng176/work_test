package com.sunits.work_test.mapper;

import com.sunits.work_test.entity.Emp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xys
 * @since 2021-06-03
 */
@Mapper
public interface EmpMapper extends BaseMapper<Emp> {

    List<Map<String, Object>> getEmpGroupByDate(LocalDate startDate, LocalDate endDate);
}
