package top.chaser.admin.api.service.impl;


import top.chaser.admin.api.controller.request.UserRoleGetReq;
import top.chaser.admin.api.controller.response.UserRoleGetRes;
import top.chaser.admin.api.mapper.UmsUserRoleRelationMapper;
import top.chaser.admin.api.service.UmsUserRoleRelationService;

import top.chaser.admin.api.entity.UmsUserRoleRelation;
import top.chaser.framework.common.web.response.R;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台用户和角色关系表(UmsUserRoleRelation)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:27
 */
@Slf4j
@Service("umsUserRoleRelationService")
public class UmsUserRoleRelationServiceImpl extends TkServiceImpl<UmsUserRoleRelation> implements UmsUserRoleRelationService {
    @Override
    public List<UserRoleGetRes> getUserRoles(UserRoleGetReq userRoleGetReq) {
        return ((UmsUserRoleRelationMapper)mapper).getUserRoles(userRoleGetReq);
    }
}
