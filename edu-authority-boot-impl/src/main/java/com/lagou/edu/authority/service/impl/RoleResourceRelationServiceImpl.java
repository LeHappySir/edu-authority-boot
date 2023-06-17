package com.lagou.edu.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lagou.edu.authority.entity.RoleResourceRelation;
import com.lagou.edu.authority.mapper.RoleResourceRelationMapper;
import com.lagou.edu.authority.service.IRoleResourceRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色和资源关系表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-27
 */
@Service
public class RoleResourceRelationServiceImpl extends ServiceImpl<RoleResourceRelationMapper, RoleResourceRelation> implements IRoleResourceRelationService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(Integer roleId) {
        QueryWrapper<RoleResourceRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResourceRelation::getRoleId, roleId);
        return remove(queryWrapper);
    }

    @Override
    public Set<Integer> queryByRoleId(Integer roleId) {
        QueryWrapper<RoleResourceRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<RoleResourceRelation> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(RoleResourceRelation::getResourceId).collect(Collectors.toSet());
    }

    @Override
    public List<RoleResourceRelation> queryByRoleIds(Set<Integer> roleIds) {
        QueryWrapper<RoleResourceRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        return this.list(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByResourceId(Integer resourceId) {
        QueryWrapper<RoleResourceRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResourceRelation::getResourceId, resourceId);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByRoleIdAndResourceIds(Integer roleId, Set<Integer> resourceIds) {
        QueryWrapper<RoleResourceRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId).in("resource_id", resourceIds);
        this.remove(queryWrapper);
    }

}
