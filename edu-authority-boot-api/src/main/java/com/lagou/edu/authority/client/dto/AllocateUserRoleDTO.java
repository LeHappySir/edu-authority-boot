package com.lagou.edu.authority.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 给用户分配角色请求DTO
 *
 * @author : chenrg
 * @create 2020/7/13 15:08
 **/
@Data
@NoArgsConstructor
@ToString
public class AllocateUserRoleDTO extends AuthorityBaseDTO {

    private Integer userId;

    private List<Integer> roleIdList;

}
