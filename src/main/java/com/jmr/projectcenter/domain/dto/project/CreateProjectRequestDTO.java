package com.jmr.projectcenter.domain.dto.project;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class CreateProjectRequestDTO {
    @NotNull
    private String creatorId;

    @NotNull
    private String teamId;

    @NotNull
    private String name;

    @NotNull
    private String id;

    private String cover;

    private String description;
}
