package com.lagou.edu.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lagou.edu.authority.entity.RoleMenuRelation;
import com.lagou.edu.authority.mapper.RoleMenuRelationMapper;
import com.lagou.edu.authority.service.IRoleMenuRelationService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色和菜单关系表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-27
 */
@Service
public class RoleMenuRelationServiceImpl extends ServiceImpl<RoleMenuRelationMapper, RoleMenuRelation> implements IRoleMenuRelationService {

    @Override
    public Set<Integer> queryByRoleIds(Set<Integer> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }
        QueryWrapper<RoleMenuRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        List<RoleMenuRelation> roleMenus = this.list(queryWrapper);
        return roleMenus.stream().map(RoleMenuRelation::getMenuId).collect(Collectors.toSet());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(Integer roleId) {
        QueryWrapper<RoleMenuRelation> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(RoleMenuRelation::getRoleId, roleId);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean removeByMenuId(Integer menuId) {
        QueryWrapper<RoleMenuRelation> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(RoleMenuRelation::getMenuId, menuId);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleIdAndMenuIds(Integer roleId, Set<Integer> needToDelMenus) {
        QueryWrapper<RoleMenuRelation> queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId).in("menu_id", needToDelMenus);
        return this.remove(queryWrapper);
    }
}
