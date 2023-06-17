package com.lagou.edu.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.edu.authority.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author xianhongle
 * @since 2022-05-27
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色id列表关联menu,role_menu_relation表，查询角色下所拥有的菜单
     *
     * @param roleIds
     * @return
     */
    List<Menu> queryByRoleIds(@Param("roleIds") Set<Integer> roleIds);

    /**
     * 查询角色菜单，忽略shown字段值
     *
     * @param roleId
     * @return
     */
    List<Menu> queryByRoleIdIgnoreIsShown(@Param("roleId") Integer roleId);
}
