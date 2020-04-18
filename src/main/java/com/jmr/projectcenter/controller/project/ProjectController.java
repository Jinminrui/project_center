package com.jmr.projectcenter.controller.project;

import com.jmr.projectcenter.auth.CheckLogin;
import com.jmr.projectcenter.domain.dto.CommonResponseDTO;
import com.jmr.projectcenter.domain.dto.project.*;
import com.jmr.projectcenter.domain.dto.user.User;
import com.jmr.projectcenter.domain.entity.project.Project;
import com.jmr.projectcenter.domain.entity.user_project_relation.UserProjectRelation;
import com.jmr.projectcenter.service.project.ProjectService;
import com.jmr.projectcenter.service.user.UserService;
import com.jmr.projectcenter.utils.UUIDOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {
    private final ProjectService projectService;
    private final UUIDOperator uuidOperator;
    private final UserService userService;

    @CheckLogin
    @PostMapping("/delete")
    public CommonResponseDTO<Object> delete(@RequestBody DeleteProjectRequestDTO deleteProjectRequestDTO) {
        int result = projectService.delete(deleteProjectRequestDTO);
        if(result == 1) {
            return CommonResponseDTO.builder().code(200).desc("删除成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("删除失败").build();
    }

    @PostMapping("/update")
    public CommonResponseDTO<Object> update(@RequestBody Project project) {
        int result = projectService.update(project);
        if(result == 1) {
            return CommonResponseDTO.builder().code(200).desc("更新成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("更新失败").build();
    }

    @CheckLogin
    @PostMapping("/create")
    public CommonResponseDTO<Object> create(@RequestBody @Valid CreateProjectRequestDTO createProjectRequestDTO) {
        Project project = Project.builder()
                .pkId(uuidOperator.getUUid())
                .id(createProjectRequestDTO.getId())
                .name(createProjectRequestDTO.getName())
                .cover(createProjectRequestDTO.getCover())
                .description(createProjectRequestDTO.getDescription())
                .teamId(createProjectRequestDTO.getTeamId())
                .createTime(new Date())
                .updateTime(new Date())
                .status(1)
                .build();
        int result = projectService.create(project, createProjectRequestDTO.getCreatorId());
        if(result == 1) {
            return CommonResponseDTO.builder().code(200).desc("新建成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("新建失败").build();
    }

    @CheckLogin
    @GetMapping("/list/{userId}")
    public CommonResponseDTO<List<ProjectDTO>> list(@PathVariable String userId) {
        List<ProjectDTO> list = projectService.list(userId);
        return CommonResponseDTO.<List<ProjectDTO>>builder().code(200).data(list).desc("success").build();
    }

    @CheckLogin
    @GetMapping("/{projectId}/members")
    public CommonResponseDTO<List<ProjectMemberDTO>> getProjectMembers(@PathVariable String projectId){
        List<UserProjectRelation> list = projectService.getProjectMembers(projectId);
        List<ProjectMemberDTO> result = new ArrayList<>();
        for(UserProjectRelation relation : list) {
            String userId = relation.getUserId();
            User user = userService.getUserInfo(userId);
            ProjectMemberDTO projectMemberDTO = new ProjectMemberDTO(user, relation.getRole());
            result.add(projectMemberDTO);
        }
        return CommonResponseDTO.<List<ProjectMemberDTO>>builder().code(200).data(result).desc("success").build();
    }

    @CheckLogin
    @GetMapping("/{projectId}/{userId}")
    public CommonResponseDTO<ProjectDTO> findById(@PathVariable String projectId,@PathVariable String userId) {
        ProjectDTO project = projectService.findById(projectId,userId);
        return CommonResponseDTO.<ProjectDTO>builder().code(200).data(project).desc("success").build();
    }

    @CheckLogin
    @PostMapping("/invite")
    public CommonResponseDTO<Object> invite(@RequestBody InviteProjectDTO inviteProjectDTO) {
        int result = projectService.invite(inviteProjectDTO.getProjectId(), inviteProjectDTO.getUserId());
        if(result == 1) {
            return CommonResponseDTO.builder().code(200).desc("邀请成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("邀请失败").build();
    }
}
