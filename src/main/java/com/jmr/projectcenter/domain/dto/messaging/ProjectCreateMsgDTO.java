package com.jmr.projectcenter.domain.dto.messaging;

import com.jmr.projectcenter.domain.entity.project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectCreateMsgDTO {
    private Project project;

    private String creatorId;
}
