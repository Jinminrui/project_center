package com.jmr.projectcenter.controller.taskclass;

import com.jmr.projectcenter.auth.CheckLogin;
import com.jmr.projectcenter.domain.dto.CommonResponseDTO;
import com.jmr.projectcenter.domain.dto.taskclass.TaskClassInfoDTO;
import com.jmr.projectcenter.domain.entity.task_class.TaskClass;
import com.jmr.projectcenter.service.task.TaskService;
import com.jmr.projectcenter.service.taskclass.TaskClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/taskclass")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskClassController {
    private final TaskClassService taskClassService;
    private final TaskService taskService;

    @CheckLogin
    @PostMapping("/create")
    public CommonResponseDTO<Object> create(@RequestBody TaskClass taskClass) {
        int result = taskClassService.create(taskClass);
        if(result == 1) {
            return CommonResponseDTO.builder().code(200).desc("创建成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("创建失败").build();
    }

    @CheckLogin
    @PostMapping("/delete/{id}")
    public CommonResponseDTO<Object> delete(@PathVariable String id) {
        // 删除类别
        int result1 = taskClassService.delete(id);
        // 将对应类别的需求归为未分类
        int result2 = taskService.setTaskClassDefault(id);
        log.info(result2+ "");
        if (result1 == 1) {
            return CommonResponseDTO.builder().code(200).desc("删除成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("删除失败").build();
    }

    @CheckLogin
    @PostMapping("/update")
    public CommonResponseDTO<Object> update(@RequestBody TaskClass taskClass) {
        int result = taskClassService.update(taskClass);
        if (result == 1) {
            return CommonResponseDTO.builder().code(200).desc("修改成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("修改失败").build();
    }

    @CheckLogin
    @GetMapping("/list/{projectId}/{type}")
    public CommonResponseDTO<HashMap<String, Object>> getClassList(@PathVariable String projectId, @PathVariable Integer type){
        List<TaskClassInfoDTO> list = taskClassService.getClassList(type, projectId);
        int noClassTaskNum = taskService.getNoClassTaskNum(type, projectId);
        int total = taskService.getTotalTaskNum(type, projectId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskClassList", list);
        map.put("noClassTaskNum",noClassTaskNum);
        map.put("total",total);
        return CommonResponseDTO.<HashMap<String, Object>>builder().code(200).data(map).desc("success").build();
    }
}
