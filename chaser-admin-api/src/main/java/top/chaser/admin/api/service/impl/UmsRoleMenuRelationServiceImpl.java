package top.chaser.admin.api.service.impl;


import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.chaser.admin.api.controller.request.RoleMenusUpdateReq;
import top.chaser.admin.api.entity.UmsRoleMenuRelation;
import top.chaser.admin.api.service.UmsRoleMenuRelationService;
import top.chaser.framework.common.web.session.SessionUtil;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台角色菜单关系表(UmsRoleMenuRelation)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:26
 */
@Slf4j
@Service("umsRoleMenuRelationService")
public class UmsRoleMenuRelationServiceImpl extends TkServiceImpl<UmsRoleMenuRelation> implements UmsRoleMenuRelationService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenus(RoleMenusUpdateReq roleMenusUpdateReq) {
        this.mapper.delete(new UmsRoleMenuRelation().setRoleId(roleMenusUpdateReq.getRoleId()));

        List<UmsRoleMenuRelation> roleMenuRelations = roleMenusUpdateReq.getMenus().stream().map(menuId ->
                new UmsRoleMenuRelation()
                        .setRoleId(roleMenusUpdateReq.getRoleId())
                        .setMenuId(menuId)
                        .setCreateTime(new Date())
                        .setCreateUser(Convert.toLong(SessionUtil.getCurrentUser().getUserId()))
        ).collect(Collectors.toList());
        this.mapper.insertListSelective(roleMenuRelations);
    }
}
