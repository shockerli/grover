package com.upfor.grover.result;

import com.upfor.grover.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 通用分页数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private Integer page; // 当前页码
    private Integer size; // 每页条数
    private Integer total; // 总条数
    private List<T> list; // 数据列表

    public static <T> PageResult<T> of(PageDto pageDto) {
        return PageResult.<T>builder()
                .page(pageDto.getPage())
                .size(pageDto.getSize())
                .build();
    }

}
