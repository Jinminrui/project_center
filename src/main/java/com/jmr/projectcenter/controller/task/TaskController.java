package com.jmr.projectcenter.controller.task;

import com.alibaba.fastjson.JSONObject;
import com.jmr.projectcenter.auth.CheckLogin;
import com.jmr.projectcenter.domain.dto.CommonResponseDTO;
import com.jmr.projectcenter.domain.dto.task.TaskDTO;
import com.jmr.projectcenter.domain.dto.user.User;
import com.jmr.projectcenter.domain.entity.task.Task;
import com.jmr.projectcenter.domain.entity.task_class.TaskClass;
import com.jmr.projectcenter.feignclient.UserCenterFeignClient;
import com.jmr.projectcenter.service.project.ProjectService;
import com.jmr.projectcenter.service.task.TaskService;
import com.jmr.projectcenter.service.taskclass.TaskClassService;
import com.jmr.projectcenter.service.user.UserService;
import com.jmr.projectcenter.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {
    private final TaskService taskService;
    private final TaskClassService taskClassService;
    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping("/{id}")
    public CommonResponseDTO<TaskDTO> getTaskDetail(@PathVariable String id) {
        Task task = taskService.findById(id);
        TaskDTO taskDTO = new TaskDTO();
        BeanUtils.copyProperties(task, taskDTO);
        // 获取任务创建者详细信息
        if (task.getCreatorId() != null) {
            taskDTO.setCreatorInfo(userService.getUserInfo(task.getCreatorId()));
        }

        // 获取任务执行者详细信息
        if (!task.getExecutor().equals("none")) {
            taskDTO.setExecutorInfo(userService.getUserInfo(task.getExecutor()));
        }

        // 获取需求分类的详情
        if (task.getTaskClass().equals("default")) {
            taskDTO.setTaskClassDetail(TaskClass.builder().pkId("default").name("未分类需求").build());
        } else {
            taskDTO.setTaskClassDetail(taskClassService.findById(task.getTaskClass()));
        }
        return CommonResponseDTO.<TaskDTO>builder().code(200).data(taskDTO).desc("success").build();
    }

    @CheckLogin
    @PostMapping("/create")
    public CommonResponseDTO<Object> create(@RequestBody @Valid Task task) {
        int result = taskService.create(task);
        if (result == 1) {
            return CommonResponseDTO.builder().code(200).desc("创建成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("创建失败").build();
    }

    @CheckLogin
    @PostMapping("/update")
    public CommonResponseDTO<Task> update(@RequestBody Task task) {
        int result = taskService.update(task);
        Task updatedTask = taskService.findById(task.getPkId());
        if (result == 1) {
            return CommonResponseDTO.<Task>builder().code(200).desc("更新成功").data(updatedTask).build();
        }
        return CommonResponseDTO.<Task>builder().code(500).desc("更新失败").data(updatedTask).build();
    }

    @CheckLogin
    @PostMapping("/delete/{taskId}")
    public CommonResponseDTO<Object> update(@PathVariable String taskId) {
        int result = taskService.delete(taskId);
        if (result == 1) {
            return CommonResponseDTO.builder().code(200).desc("删除成功").build();
        }
        return CommonResponseDTO.builder().code(500).desc("删除失败").build();
    }

    @GetMapping("/list")
    public CommonResponseDTO<List<TaskDTO>> list(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "projectId", required = false) String projectId,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "taskClass", required = false) String taskClass,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "creatorId", required = false) String creatorId,
            @RequestParam(value = "executor", required = false) String executor) {
        List<Task> list = taskService.getTaskList(projectId, type, taskClass, stage, creatorId,executor);
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (Task task : list) {
            TaskDTO taskDTO = new TaskDTO();
            BeanUtils.copyProperties(task, taskDTO);

            // 获取任务创建者详细信息
            if (task.getCreatorId() != null) {
                taskDTO.setCreatorInfo(userService.getUserInfo(task.getCreatorId()));
            }

            // 获取任务执行者详细信息
            if (!task.getExecutor().equals("none")) {
                taskDTO.setExecutorInfo(userService.getUserInfo(task.getExecutor()));
            }

            // 获取需求分类的详情
            if (task.getTaskClass().equals("default")) {
                taskDTO.setTaskClassDetail(TaskClass.builder().pkId("default").name("未分类需求").build());
            } else {
                taskDTO.setTaskClassDetail(taskClassService.findById(task.getTaskClass()));
            }
            taskDTOList.add(taskDTO);

            // 获取项目详情
            taskDTO.setProjectDetail(projectService.findById(task.getProjectId(), userId));
        }
        return CommonResponseDTO.<List<TaskDTO>>builder().code(200).data(taskDTOList).desc("success").build();
    }

}
