package com.upfor.grover.handler.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * MyBatis TypeHandler: LocalDateTime 类型与 BigInt 类型的转换处理器
 * <p>
 * 1. 数据库中存储的是毫秒时间戳，类型为 BIGINT<br>
 * 2. Java 中的类型为 LocalDateTime<br>
 */
@MappedJdbcTypes(JdbcType.BIGINT)
public class LocalDateTimeBigIntTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setLong(i, parameter.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        } else {
            ps.setNull(i, Types.BIGINT);
        }
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long timestamp = rs.getLong(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        }
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long timestamp = rs.getLong(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        }
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long timestamp = cs.getLong(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        }
    }

}
