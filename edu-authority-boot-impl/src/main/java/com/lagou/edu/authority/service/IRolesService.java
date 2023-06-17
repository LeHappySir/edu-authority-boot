package com.lagou.edu.authority.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lagou.edu.authority.client.dto.AllocateUserRoleDTO;
import com.lagou.edu.authority.client.param.RoleQueryParam;
import com.lagou.edu.authority.entity.Roles;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xianhongle
 * @since 2022-05-27
 */
public interface IRolesService extends IService<Roles> {

    /**
     * 获取角色
     *
     * @param id
     * @return
     */
    Roles get(Integer id);

    /**
     * 获取所有角色
     *
     * @return
     */
    List<Roles> getAll();

    /**
     * 根据用户id查询用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<Roles> queryByUserId(Integer userId);

    /**
     * 根据id删除角色
     * 并且关联删除用户-角色，角色-菜单，角色-资源关系
     *
     * @param id
     * @return
     */
    boolean deleteWithAssociation(Integer id);

    /**
     * 给用户分配角色
     *
     * @param allocateUserRoleDTO
     * @return
     */
    void allocateUserRoles(AllocateUserRoleDTO allocateUserRoleDTO);

    Page<Roles> getRolePages(RoleQueryParam roleQueryParam);

}
