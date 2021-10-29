package top.chaser.admin.api.service;

import top.chaser.admin.api.controller.request.ResourceEditReq;
import top.chaser.admin.api.entity.UmsResource;
import top.chaser.framework.starter.tkmybatis.service.IService;

/**
 * 后台资源表(UmsResource)表服务接口
 *
 * @author yzb
 * @since 2021-10-18 09:40:25
 */
public interface UmsResourceService extends IService<UmsResource> {
    void merge(ResourceEditReq resourceEditReq);

    void delete(Long id);
}

