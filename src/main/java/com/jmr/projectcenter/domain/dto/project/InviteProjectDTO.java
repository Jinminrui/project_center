package com.jmr.projectcenter.domain.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteProjectDTO {
    @NotNull
    private String projectId;
    @NotNull
    private String userId;
}
