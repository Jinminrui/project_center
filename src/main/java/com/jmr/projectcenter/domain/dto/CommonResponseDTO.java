package com.jmr.projectcenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommonResponseDTO<T> {
    private Integer code;
    private T data;
    private String desc;
}
