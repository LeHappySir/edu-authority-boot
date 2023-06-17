package com.lagou.edu.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.edu.authority.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author xianhongle
 * @since 2022-05-27
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据角色id列表查询关联的资源
     * @param roleIds
     * @return
     */
    List<Resource> queryByRoleIds(@Param("roleIds") Set<Integer> roleIds);

}
