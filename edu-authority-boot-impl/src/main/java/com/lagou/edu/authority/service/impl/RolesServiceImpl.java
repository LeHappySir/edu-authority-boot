package com.lagou.edu.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.lagou.edu.authority.client.dto.AllocateUserRoleDTO;
import com.lagou.edu.authority.client.param.RoleQueryParam;
import com.lagou.edu.authority.entity.Roles;
import com.lagou.edu.authority.entity.UserRoleRelation;
import com.lagou.edu.authority.mapper.RolesMapper;
import com.lagou.edu.authority.service.IRoleMenuRelationService;
import com.lagou.edu.authority.service.IRoleResourceRelationService;
import com.lagou.edu.authority.service.IRolesService;
import com.lagou.edu.authority.service.IUserRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-27
 */
@Service
@Slf4j
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

    @Autowired
    private IUserRoleRelationService userRoleService;

    @Autowired
    private IRoleMenuRelationService roleMenuService;

    @Autowired
    private IRoleResourceRelationService roleResourceService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean deleteWithAssociation(Integer id) {
        userRoleService.removeByRoleId(id);
        roleMenuService.removeByRoleId(id);
        roleResourceService.removeByRoleId(id);
        return this.removeById(id);
    }

    @Override
    public Roles get(Integer id) {
        Roles role = this.getById(id);
        if (Objects.isNull(role)) {
            return null;
        }
        role.setResourceIds(roleResourceService.queryByRoleId(id));
        return role;
    }

    @Override
    public List<Roles> getAll() {
        return this.list();
    }

    @Override
    public List<Roles> queryByUserId(Integer userId) {
        Set<Integer> roleIds = userRoleService.queryByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        return this.listByIds(roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void allocateUserRoles(AllocateUserRoleDTO allocateUserRoleDTO) {
        if (CollectionUtils.isEmpty(allocateUserRoleDTO.getRoleIdList())) {
            // 如果id列表为空，表示要删除所有角色
            allocateUserRoleDTO.setRoleIdList(Lists.newArrayList());
        }
        // 用户已拥有的角色(id列表）
        Set<Integer> userRoleIds = userRoleService.queryByUserId(allocateUserRoleDTO.getUserId());
        // 当前要分配给用户的角色(id列表）
        Set<Integer> allocatedRoleIds = allocateUserRoleDTO.getRoleIdList().stream().collect(Collectors.toSet());

        // 找出本次删除的
        Set<Integer> needToDelRoles = userRoleIds.stream().filter(id -> !allocatedRoleIds.contains(id)).collect(Collectors.toSet());
        // 找出本次新增的
        Set<Integer> needToInsertRoles = allocatedRoleIds.stream().filter(id -> !userRoleIds.contains(id)).collect(Collectors.toSet());

        if (CollectionUtils.isNotEmpty(needToDelRoles)) {
            userRoleService.removeByRoleIds(allocateUserRoleDTO.getUserId(), needToDelRoles);
        }

        if (CollectionUtils.isNotEmpty(needToInsertRoles)) {
            List<UserRoleRelation> newUserRoles = needToInsertRoles.stream().map(roleId -> {
                UserRoleRelation userRole = new UserRoleRelation();
                userRole.setUserId(allocateUserRoleDTO.getUserId());
                userRole.setRoleId(roleId);
                userRole.setCreatedBy(allocateUserRoleDTO.getCreatedBy());
                userRole.setUpdatedBy(allocateUserRoleDTO.getUpdatedBy());
                userRole.setCreatedTime(new Date());
                userRole.setUpdatedTime(new Date());
                return userRole;
            }).collect(Collectors.toList());
            userRoleService.saveBatch(newUserRoles);
        }
    }

    @Override
    public Page<Roles> getRolePages(RoleQueryParam roleQueryParam) {
        Page<Roles> page = new Page<>(roleQueryParam.getCurrent(), roleQueryParam.getSize());
        QueryWrapper<Roles> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(Objects.nonNull(roleQueryParam.getId()), "id", roleQueryParam.getId())
                .like(StringUtils.isNotBlank(roleQueryParam.getName()), "name", roleQueryParam.getName())
                .eq(StringUtils.isNotBlank(roleQueryParam.getCode()), "code", roleQueryParam.getCode())
                .ge(Objects.nonNull(roleQueryParam.getStartCreateTime()), "created_time", roleQueryParam.getStartCreateTime())
                .le(Objects.nonNull(roleQueryParam.getEndCreateTime()), "created_time", roleQueryParam.getEndCreateTime())
                .orderByDesc("id");
        return this.page(page, queryWrapper);
    }
}
