package com.upfor.grover.dto;

import com.upfor.grover.helper.JSON;

public class BaseDto {

    public String toJSON() {
        return JSON.toJSON(this);
    }

}
