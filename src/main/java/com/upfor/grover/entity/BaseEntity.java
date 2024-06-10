package com.upfor.grover.entity;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upfor.grover.constant.DateTimeConst;
import com.upfor.grover.helper.JSON;
import lombok.Data;

import java.time.format.DateTimeFormatter;

/**
 * 基础实体类
 * <p>
 * 按理所有实体类都可以继承该类
 */
@Data
public class BaseEntity {

    /**
     * 用户ID
     * <p>
     * 主键ID, 其他表的user_id
     */
    @JsonProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO) // 字段名、主键注解
    private Long id;

    /**
     * 数据创建时间
     * <p>
     * 数据第一次创建时的时间，不可更改
     */
    @JsonProperty(value = "created_at")
    @TableField(value = "created_at", fill = FieldFill.INSERT) // 字段名、插入时填充
    private Long createdAt;

    /**
     * 格式化后的创建时间
     *
     * @param formatter 时间格式化器
     * @return 格式化后的时间
     */
    public String formattedCreatedAt(DateTimeFormatter formatter) {
        return LocalDateTimeUtil.of(this.getCreatedAt())
                .format(formatter != null ? formatter : DateTimeConst.DATE_TIME_FORMATTER);
    }

    /**
     * 最后修改时间
     * <p>
     * 数据每次被修改都会更新该时间
     */
    @JsonProperty(value = "updated_at")
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE) // 字段名、插入和更新时填充
    private Long updatedAt;

    /**
     * 格式化后的最后修改时间
     *
     * @param formatter 时间格式化器
     * @return 格式化后的时间
     */
    public String formattedUpdatedAt(DateTimeFormatter formatter) {
        return LocalDateTimeUtil.of(this.getUpdatedAt())
                .format(formatter != null ? formatter : DateTimeConst.DATE_TIME_FORMATTER);
    }

    public String toJSON() {
        return JSON.toJSON(this);
    }

}
