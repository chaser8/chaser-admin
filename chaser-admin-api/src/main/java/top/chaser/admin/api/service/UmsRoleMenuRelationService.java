package top.chaser.admin.api.service;

import top.chaser.admin.api.controller.request.RoleMenusUpdateReq;
import top.chaser.admin.api.entity.UmsRoleMenuRelation;
import top.chaser.framework.starter.tkmybatis.service.IService;

/**
 * 后台角色菜单关系表(UmsRoleMenuRelation)表服务接口
 *
 * @author yzb
 * @since 2021-10-18 09:40:26
 */
public interface UmsRoleMenuRelationService extends IService<UmsRoleMenuRelation> {
    void updateRoleMenus(RoleMenusUpdateReq roleMenusUpdateReq);
}

