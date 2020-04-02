package com.jmr.projectcenter.domain.dto.taskclass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskClassInfoDTO {
    private String pkId;
    private String name;
    private String description;
    private Integer taskNum;
}
