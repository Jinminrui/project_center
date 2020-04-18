package com.jmr.projectcenter.domain.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProjectRequestDTO {
    private String projectId;
    private String operatorId;
    private String teamId;
    private String projectName;
}
