package com.jmr.projectcenter.service.activity;

import com.jmr.projectcenter.dao.activity.ActivityMapper;
import com.jmr.projectcenter.domain.entity.activity.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityService {
    private final ActivityMapper activityMapper;

    public List<Activity> list(String teamId, String projectId) {
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        if(teamId != null) {
            criteria.andEqualTo("teamId",teamId);
        }
        if(projectId != null) {
            criteria.andEqualTo("projectId", projectId);
        }
        example.setOrderByClause("create_time desc");
        return activityMapper.selectByExample(example);
    }
}
