package com.mileageservice.dto.common.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResDto {
    private String msg;
    private Object data;
}
