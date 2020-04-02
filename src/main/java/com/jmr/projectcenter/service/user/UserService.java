package com.jmr.projectcenter.service.user;

import com.alibaba.fastjson.JSONObject;
import com.jmr.projectcenter.domain.dto.CommonResponseDTO;
import com.jmr.projectcenter.domain.dto.user.User;
import com.jmr.projectcenter.feignclient.UserCenterFeignClient;
import com.jmr.projectcenter.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserCenterFeignClient userCenterFeignClient;
    private final RedisUtil redisUtil;

    public User getUserInfo(String id) {
        if (redisUtil.hasKey(id) && redisUtil.get(id) != null) {
            return JSONObject.parseObject((String) redisUtil.get(id), User.class);
        } else {
            CommonResponseDTO<User> userCommonResponseDTO = userCenterFeignClient.getUserById(id);
            return userCommonResponseDTO.getData();
        }
    }
}
