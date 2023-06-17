package com.lagou.edu.authority.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 角色
 *
 * @author : chenrg
 * @create 2020/7/9 11:10
 **/
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends AuthorityBaseDTO {

    private String code;

    private String name;

    private String description;

}
