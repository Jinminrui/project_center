package com.jmr.projectcenter.domain.dto.project;

import com.jmr.projectcenter.domain.dto.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberDTO {
    private User info;
    private Integer role;
}
