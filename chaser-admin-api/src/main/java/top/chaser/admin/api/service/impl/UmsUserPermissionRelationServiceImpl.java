package top.chaser.admin.api.service.impl;


import top.chaser.admin.api.service.UmsUserPermissionRelationService;

import top.chaser.admin.api.entity.UmsUserPermissionRelation;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)(UmsUserPermissionRelation)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:27
 */
@Slf4j
@Service("umsUserPermissionRelationService")
public class UmsUserPermissionRelationServiceImpl extends TkServiceImpl<UmsUserPermissionRelation> implements UmsUserPermissionRelationService {

}
