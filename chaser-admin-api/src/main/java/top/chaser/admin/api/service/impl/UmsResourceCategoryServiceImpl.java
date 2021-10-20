package top.chaser.admin.api.service.impl;


import top.chaser.admin.api.service.UmsResourceCategoryService;

import top.chaser.admin.api.entity.UmsResourceCategory;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 资源分类表(UmsResourceCategory)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:25
 */
@Slf4j
@Service("umsResourceCategoryService")
public class UmsResourceCategoryServiceImpl extends TkServiceImpl<UmsResourceCategory> implements UmsResourceCategoryService {

}
