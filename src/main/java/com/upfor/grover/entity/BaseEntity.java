package com.upfor.grover.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.upfor.grover.handler.mybatis.LocalDateTimeBigIntTypeHandler;
import com.upfor.grover.handler.serializer.LocalDateTimeToLongSerializer;
import com.upfor.grover.helper.JSON;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

/**
 * 基础实体类（抽象类）
 * <p>
 * 按理所有数据表的实体类都应该继承该类
 */
@Data
abstract public class BaseEntity {

    /**
     * MySQL 获取当前毫秒时间戳BigInt函数
     */
    private static final String NOW = "ROUND(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(6)) * 1000)";

    /**
     * 用户ID
     * <p>
     * 主键ID, 其他表的user_id
     */
    @Id(value = "id", keyType = KeyType.Auto) // 字段名、主键注解
    private Long id;

    /**
     * 数据创建时间
     * <p>
     * 1. 数据第一次创建时的时间，不可更改<br>
     * 2. 数据库中的字段类型为BIGINT，存储的是毫秒时间戳<br>
     * 3. Java 中的字段类型为 LocalDateTime，方便操作<br>
     * 4. 序列化成JSON时，会转换为毫秒时间戳<br>
     */
    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    @Column(value = "created_at", onInsertValue = NOW, jdbcType = JdbcType.BIGINT, typeHandler = LocalDateTimeBigIntTypeHandler.class)
    private LocalDateTime createdAt;

    /**
     * 最后修改时间
     * <p>
     * 1. 数据每次被修改都会更新该时间<br>
     * 2. 数据第一次创建时也会更新该时间<br>
     * 3. 数据库中的字段类型为BIGINT，存储的是毫秒时间戳<br>
     * 4. Java 中的字段类型为 LocalDateTime，方便操作<br>
     * 5. 该字段不可手动修改，也无需手动修改<br>
     * 6. 序列化成JSON时，会转换为毫秒时间戳<br>
     */
    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    @Column(value = "updated_at", onInsertValue = NOW, onUpdateValue = NOW, jdbcType = JdbcType.BIGINT, typeHandler = LocalDateTimeBigIntTypeHandler.class)
    private LocalDateTime updatedAt;


    /**
     * 将当前对象转换为JSON字符串
     */
    public String toJSON() {
        return JSON.toJSON(this);
    }

}
