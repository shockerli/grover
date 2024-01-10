package com.upfor.grover.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 分页参数
 * <p>
 * 1. 如果使用类接收参数，请继承此类<br/>
 * 2. 如果只想获取分页参数，请使用 {@code new PageDto(request)}
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {

    public static Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 分页页码
     */
    @JsonProperty("page")
    @JsonAlias({"pageNum"})
    private Integer page = 1;

    /**
     * 分页大小
     */
    @JsonProperty("size")
    @JsonAlias({"pageSize"})
    private Integer size = DEFAULT_PAGE_SIZE;

    // 支持从HttpServletRequest解析GET请求中的分页参数
    public PageDto(HttpServletRequest request) {
        // 分页页码
        // 兼容pageNum和page两种参数名
        Optional.ofNullable(request.getParameter("pageNum")).ifPresent(s -> this.page = Integer.parseInt(s));
        Optional.ofNullable(request.getParameter("page")).ifPresent(s -> this.page = Integer.parseInt(s));

        // 分页大小
        // 兼容pageSize和size两种参数名
        Optional.ofNullable(request.getParameter("pageSize")).ifPresent(s -> this.size = Integer.parseInt(s));
        Optional.ofNullable(request.getParameter("size")).ifPresent(s -> this.size = Integer.parseInt(s));
    }

    /**
     * 获取可直接用的分页大小
     */
    public Integer getSize() {
        if (this.size == null || this.size < 1) {
            size = DEFAULT_PAGE_SIZE;
        }
        return size;
    }

    /**
     * 获取可直接用的页码
     */
    public Integer getPage() {
        if (this.page == null || this.page < 1) {
            page = 1;
        }
        return page;
    }

    /**
     * 获取可用于SQL的分页起始位置
     */
    @JsonIgnore
    public Integer getLimitStart() {
        return (this.getPage() - 1) * this.getSize();
    }

    /**
     * 获取可用于SQL的分页结束位置
     */
    @JsonIgnore
    public Integer getLimitEnd() {
        return this.getSize();
    }

}
