package com.jmr.projectcenter.controller.activity;

import com.jmr.projectcenter.domain.dto.CommonResponseDTO;
import com.jmr.projectcenter.domain.dto.activity.ActivityDTO;
import com.jmr.projectcenter.domain.entity.activity.Activity;
import com.jmr.projectcenter.service.activity.ActivityService;
import com.jmr.projectcenter.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/activity")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityController {
    private final ActivityService activityService;
    private final UserService userService;

    @GetMapping("/list")
    public CommonResponseDTO<List<ActivityDTO>> list(@RequestParam(value = "teamId") String teamId,
                                                     @RequestParam(value = "projectId", required = false) String projectId) {
        List<Activity> activities = activityService.list(teamId, projectId);
        List<ActivityDTO> result = new ArrayList<>();

        for(Activity activity : activities) {
            ActivityDTO activityDTO = new ActivityDTO();
            BeanUtils.copyProperties(activity, activityDTO);
            activityDTO.setCreatorDetail(userService.getUserInfo(activity.getCreatorId()));
            result.add(activityDTO);
        }
        return CommonResponseDTO.<List<ActivityDTO>>builder().code(200).data(result).build();
    }

}
