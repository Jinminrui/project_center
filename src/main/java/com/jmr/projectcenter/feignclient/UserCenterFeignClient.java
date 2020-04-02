package com.jmr.projectcenter.feignclient;

import com.jmr.projectcenter.config.FeignConfiguration;
import com.jmr.projectcenter.domain.dto.CommonResponseDTO;
import com.jmr.projectcenter.domain.dto.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-center", configuration = FeignConfiguration.class)
public interface UserCenterFeignClient {
    /**
     * http://user-center/users/id
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    CommonResponseDTO<User> getUserById(@PathVariable String id);
}
