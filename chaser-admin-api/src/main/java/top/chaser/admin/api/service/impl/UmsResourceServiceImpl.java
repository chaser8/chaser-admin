package top.chaser.admin.api.service.impl;


import top.chaser.admin.api.service.UmsResourceService;

import top.chaser.admin.api.entity.UmsResource;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 后台资源表(UmsResource)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:25
 */
@Slf4j
@Service("umsResourceService")
public class UmsResourceServiceImpl extends TkServiceImpl<UmsResource> implements UmsResourceService {

}
