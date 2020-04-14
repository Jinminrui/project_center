package com.jmr.projectcenter.service.user;

import com.alibaba.fastjson.JSONObject;
import com.jmr.projectcenter.domain.dto.CommonResponseDTO;
import com.jmr.projectcenter.domain.dto.user.User;
import com.jmr.projectcenter.feignclient.UserCenterFeignClient;
import com.jmr.projectcenter.utils.RedisOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserCenterFeignClient userCenterFeignClient;
    private final RedisOperator redisOperator;

    public User getUserInfo(String id) {
        if (redisOperator.hasKey(id) && redisOperator.get(id) != null) {
            return JSONObject.parseObject((String) redisOperator.get(id), User.class);
        } else {
            CommonResponseDTO<User> userCommonResponseDTO = userCenterFeignClient.getUserById(id);
            return userCommonResponseDTO.getData();
        }
    }
}
