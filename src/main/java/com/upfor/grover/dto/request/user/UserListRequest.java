package com.upfor.grover.dto.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.upfor.grover.entity.table.UserTableDef.USER;

/**
 * User list request params
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 忽略未知属性，避免抛异常
public class UserListRequest {

    /**
     * UserID
     */
    private Long id;

    public QueryWrapper wrapper() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.where(USER.ID.eq(id));
        return wrapper;
    }

}
