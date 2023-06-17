package com.lagou.edu.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lagou.edu.authority.entity.Resource;
import com.lagou.edu.authority.entity.ResourceCategory;
import com.lagou.edu.authority.mapper.ResourceCategoryMapper;
import com.lagou.edu.authority.service.IResourceCategoryService;
import com.lagou.edu.authority.service.IResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 资源分类表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-27
 */
@Service
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, ResourceCategory> implements IResourceCategoryService {

    @Autowired
    private IResourceService resourceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Integer id) {
        List<Resource> resources = resourceService.getByCategoryId(id);
        if (CollectionUtils.isNotEmpty(resources)) {
            throw new RuntimeException("资源分类下有资源信息，不允许删除!");
        }
        return this.removeById(id);
    }
}
