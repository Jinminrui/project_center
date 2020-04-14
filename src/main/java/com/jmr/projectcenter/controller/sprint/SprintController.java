package com.jmr.projectcenter.controller.sprint;

import com.jmr.projectcenter.auth.CheckLogin;
import com.jmr.projectcenter.domain.dto.CommonResponseDTO;
import com.jmr.projectcenter.domain.dto.sprint.SprintDTO;
import com.jmr.projectcenter.domain.dto.sprint.StartSprintDTO;
import com.jmr.projectcenter.domain.entity.sprint.Sprint;
import com.jmr.projectcenter.service.sprint.SprintService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprint")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SprintController {
    private final SprintService sprintService;

    @CheckLogin
    @GetMapping("/{id}")
    public CommonResponseDTO<Sprint> findById(@PathVariable String id){
        Sprint sprint = sprintService.findById(id);
        return CommonResponseDTO.<Sprint>builder().code(200).data(sprint).desc("success").build();
    }

    @CheckLogin
    @GetMapping("/list")
    public CommonResponseDTO<List<SprintDTO>> list(@RequestParam(value = "projectId") String projectId){
        List<SprintDTO> sprints = sprintService.list(projectId);
        return CommonResponseDTO.<List<SprintDTO>>builder().code(200).data(sprints).desc("success").build();
    }

    @CheckLogin
    @PostMapping("/create")
    public CommonResponseDTO<Object> create(@RequestBody Sprint sprint){
        int result = sprintService.create(sprint);
        if (result == 1) {
            return CommonResponseDTO.builder().code(200).desc("创建成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("创建失败").build();
    }

    @CheckLogin
    @PostMapping("/update")
    public CommonResponseDTO<Object> update(@RequestBody Sprint sprint) {
        int result = sprintService.update(sprint);
        if (result == 1) {
            return CommonResponseDTO.builder().code(200).desc("更新成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("更新失败").build();
    }

    @CheckLogin
    @PostMapping("/delete/{id}")
    public CommonResponseDTO<Object> delete(@PathVariable String id){
        int result = sprintService.delete(id);
        if (result == 1) {
            return CommonResponseDTO.builder().code(200).desc("删除成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("删除失败").build();
    }

    @CheckLogin
    @PostMapping("/start")
    public CommonResponseDTO<Object> start(@RequestBody StartSprintDTO startSprintDTO) {
        log.info(startSprintDTO.toString());
        Sprint sprint = Sprint.builder()
                .pkId(startSprintDTO.getPkId())
                .startTime(startSprintDTO.getStartTime())
                .endTime(startSprintDTO.getEndTime())
                .build();
        int result = sprintService.start(sprint);
        if (result == 1) {
            return CommonResponseDTO.builder().code(200).desc("迭代开始").build();
        }
        return CommonResponseDTO.builder().code(500).desc("无法开始迭代").build();
    }

    @CheckLogin
    @PostMapping("/{id}/complete")
    public CommonResponseDTO<Object> complete(@PathVariable String id) {
        int result = sprintService.complete(id);
        if (result == 1) {
            return CommonResponseDTO.builder().code(200).desc("已完成迭代").build();
        }
        if(result == 2) {
            return CommonResponseDTO.builder().code(500).desc("存在未完成的任务，无法结束迭代").build();
        }
        return CommonResponseDTO.builder().code(500).desc("无法结束迭代").build();
    }
}
