package top.chaser.admin.api.service.impl;


import top.chaser.admin.api.service.UmsUserRoleRelationService;

import top.chaser.admin.api.entity.UmsUserRoleRelation;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 后台用户和角色关系表(UmsUserRoleRelation)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:27
 */
@Slf4j
@Service("umsUserRoleRelationService")
public class UmsUserRoleRelationServiceImpl extends TkServiceImpl<UmsUserRoleRelation> implements UmsUserRoleRelationService {

}
