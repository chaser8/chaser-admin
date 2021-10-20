package top.chaser.admin.api.service.impl;


import top.chaser.admin.api.service.UmsRolePermissionRelationService;

import top.chaser.admin.api.entity.UmsRolePermissionRelation;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 后台用户角色和权限关系表(UmsRolePermissionRelation)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:26
 */
@Slf4j
@Service("umsRolePermissionRelationService")
public class UmsRolePermissionRelationServiceImpl extends TkServiceImpl<UmsRolePermissionRelation> implements UmsRolePermissionRelationService {

}
