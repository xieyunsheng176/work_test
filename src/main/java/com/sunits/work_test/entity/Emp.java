package com.sunits.work_test.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xys
 * @since 2021-06-03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("emp")
@ApiModel(value="emp对象", description="emp表")
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelProperty(value = "id",index = 1)
    private String id;

    @ExcelProperty(value = "名字",index = 2)
    @TableField(value = "name")
    private String name;

    @ExcelProperty(value = "编号",index = 0)
    @TableField(value = "code")
    private String code;

    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @Version
    @TableField(value = "version",fill = FieldFill.INSERT)
    private String version;

    @TableLogic
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private String deleted;

    @TableField(exist = false)
    private List<Dept> dept;
}
