package com.upfor.grover.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {

    /**
     * ObjectMapper
     * <p>
     * 全局统一使用一个ObjectMapper对象，如需配置，请在此处配置
     */
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJSON(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (Exception e) {
            return "";
        }
    }

}
