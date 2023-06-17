package com.lagou.edu.authority.client.remote;

import com.lagou.edu.authority.client.dto.PermissionDTO;
import com.lagou.edu.authority.client.remote.fallback.AuthProviderFallbackFactory;
import com.lagou.edu.common.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "edu-authority-boot", path = "/auth",
        fallbackFactory = AuthProviderFallbackFactory.class)
public interface AuthProvider {
    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication
     * @param userId
     * @param url
     * @param method
     * @return <pre>
     * Result:
     * {
     *   code:"000000"
     *   mesg:"请求成功"
     *   data: true/false
     * }
     * </pre>
     */
    @PostMapping(value = "/permission")
    Result auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication, @RequestParam("userId") String userId, @RequestParam("url") String url, @RequestParam("method") String method);

    /**
     * 查询用户所拥有的菜单、资源权限
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/listUserPermission")
    Result<PermissionDTO> listUserPermission(@RequestParam("userId") Integer userId);

}
