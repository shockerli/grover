package com.upfor.grover.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseEntity {

    /**
     * 用户ID
     * <p>
     * 主键ID, 其他表的user_id
     */
    @JsonProperty(value = "id")
    private Long id;

    /**
     * 数据创建时间
     * <p>
     * 数据第一次创建时的时间，不可更改
     */
    @JsonProperty(value = "created_at")
    private Long createdAt;

    /**
     * 最后修改时间
     * <p>
     * 数据每次被修改都会更新该时间
     */
    @JsonProperty(value = "updated_at")
    private Long updatedAt;

}
