package top.chaser.admin.api.service.impl;


import top.chaser.admin.api.service.UmsRoleService;

import top.chaser.admin.api.entity.UmsRole;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 后台用户角色表(UmsRole)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:26
 */
@Slf4j
@Service("umsRoleService")
public class UmsRoleServiceImpl extends TkServiceImpl<UmsRole> implements UmsRoleService {

}
