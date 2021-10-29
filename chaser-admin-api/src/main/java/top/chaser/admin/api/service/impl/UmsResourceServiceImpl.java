package top.chaser.admin.api.service.impl;


import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.chaser.admin.api.controller.request.ResourceEditReq;
import top.chaser.admin.api.entity.UmsFuncResourceRelation;
import top.chaser.admin.api.entity.UmsResource;
import top.chaser.admin.api.service.UmsFuncResourceRelationService;
import top.chaser.admin.api.service.UmsResourceService;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.common.web.session.SessionUtil;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 后台资源表(UmsResource)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:25
 */
@Slf4j
@Service("umsResourceService")
public class UmsResourceServiceImpl extends TkServiceImpl<UmsResource> implements UmsResourceService {
    @Resource
    private UmsFuncResourceRelationService funcResourceRelationService;

    @Override
    public void merge(ResourceEditReq resourceEditReq) {
        UmsResource umsResource = BeanUtil.toBean(resourceEditReq, UmsResource.class);
        Long userId = Convert.toLong(SessionUtil.getCurrentUser().getUserId());
        umsResource.setUpdateUser(userId);
        if (umsResource.getId() == null) {
            umsResource.setCreateUser(userId);
            this.mapper.insertSelective(umsResource);
        } else {
            umsResource.setUpdateUser(userId);
            umsResource.setUpdateDate(new Date());
            this.mapper.updateByPrimaryKeySelective(umsResource);
        }
    }

    @Override
    public void delete(Long id) {
        funcResourceRelationService.delete(new UmsFuncResourceRelation().setResourceId(id));
        mapper.delete(new UmsResource().setId(id));
    }
}
