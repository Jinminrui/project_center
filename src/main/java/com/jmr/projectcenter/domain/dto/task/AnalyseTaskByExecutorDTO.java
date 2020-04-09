package com.jmr.projectcenter.domain.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseTaskByExecutorDTO {
    private String executor;
    private Integer taskNum;
}
