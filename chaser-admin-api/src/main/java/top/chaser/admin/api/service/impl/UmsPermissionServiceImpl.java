package top.chaser.admin.api.service.impl;


import top.chaser.admin.api.service.UmsPermissionService;

import top.chaser.admin.api.entity.UmsPermission;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 后台用户权限表(UmsPermission)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:25
 */
@Slf4j
@Service("umsPermissionService")
public class UmsPermissionServiceImpl extends TkServiceImpl<UmsPermission> implements UmsPermissionService {

}
