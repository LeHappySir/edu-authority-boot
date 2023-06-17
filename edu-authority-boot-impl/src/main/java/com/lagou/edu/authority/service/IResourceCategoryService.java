package com.lagou.edu.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lagou.edu.authority.entity.ResourceCategory;

/**
 * <p>
 * 资源分类表 服务类
 * </p>
 *
 * @author xianhongle
 * @since 2022-05-27
 */
public interface IResourceCategoryService extends IService<ResourceCategory> {

    /**
     * 删除资源分类，分类下有资源的不允许删除
     *
     * @param id
     * @return
     */
    boolean deleteById(Integer id);
}
