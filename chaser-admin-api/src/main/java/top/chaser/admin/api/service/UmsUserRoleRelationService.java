package top.chaser.admin.api.service;

import top.chaser.admin.api.controller.request.UserRoleGetReq;
import top.chaser.admin.api.controller.response.UserRoleGetRes;
import top.chaser.framework.common.web.response.R;
import top.chaser.framework.starter.tkmybatis.service.IService;
import top.chaser.admin.api.entity.UmsUserRoleRelation;

import java.util.List;

/**
 * 后台用户和角色关系表(UmsUserRoleRelation)表服务接口
 *
 * @author yzb
 * @since 2021-10-18 09:40:27
 */
public interface UmsUserRoleRelationService extends IService<UmsUserRoleRelation> {
    List<UserRoleGetRes> getUserRoles(UserRoleGetReq userRoleGetReq);
}

