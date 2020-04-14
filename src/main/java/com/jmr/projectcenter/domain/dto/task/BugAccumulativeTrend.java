package com.jmr.projectcenter.domain.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugAccumulativeTrend {
    private String date;
    private String type;
    private Integer value;
}
