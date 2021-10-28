package top.chaser.admin.api.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.chaser.admin.api.entity.UmsRoleFuncRelation;
import top.chaser.admin.api.service.UmsRoleFuncRelationService;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

/**
 * 后台用户角色和权限关系表(UmsRoleFuncRelation)表服务实现类
 *
 * @author yzb
 * @since 2021-10-27 09:41:34
 */
@Slf4j
@Service("umsRoleFuncRelationService")
public class UmsRoleFuncRelationServiceImpl extends TkServiceImpl<UmsRoleFuncRelation> implements UmsRoleFuncRelationService {

}
