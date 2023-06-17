package com.lagou.edu.authority.client.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenrg
 * @date 2020年7月7日18:23:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleQueryParam extends BaseQueryParam {

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;


}
