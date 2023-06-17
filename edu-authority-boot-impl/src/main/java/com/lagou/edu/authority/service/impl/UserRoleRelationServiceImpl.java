package com.lagou.edu.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lagou.edu.authority.entity.UserRoleRelation;
import com.lagou.edu.authority.mapper.UserRoleRelationMapper;
import com.lagou.edu.authority.service.IUserRoleRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户和角色关系表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-27
 */
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation> implements IUserRoleRelationService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByUserId(Integer userId) {
        QueryWrapper<UserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRelation::getUserId, userId);
        return remove(queryWrapper);
    }

    @Override
    public Set<Integer> queryByUserId(Integer userId) {
        QueryWrapper<UserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserRoleRelation> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toSet());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(Integer roleId) {
        QueryWrapper<UserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleRelation::getRoleId, roleId);
        return remove(queryWrapper);
    }

    @Override
    public boolean removeByRoleIds(Integer userId, Set<Integer> roleIds) {
        QueryWrapper<UserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).in("role_id", roleIds);
        return remove(queryWrapper);
    }
}
