package top.chaser.admin.api.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.chaser.admin.api.controller.response.MenuFuncGetRes;
import top.chaser.admin.api.entity.UmsMenuFuncRelation;
import top.chaser.admin.api.mapper.UmsMenuFuncRelationMapper;
import top.chaser.admin.api.service.UmsMenuFuncRelationService;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

import java.util.List;

/**
 * 后台角色菜单关系表(UmsMenuFuncRelation)表服务实现类
 *
 * @author yzb
 * @since 2021-10-27 09:41:32
 */
@Slf4j
@Service("umsMenuFuncRelationService")
public class UmsMenuFuncRelationServiceImpl extends TkServiceImpl<UmsMenuFuncRelation> implements UmsMenuFuncRelationService {
    @Override
    public List<MenuFuncGetRes> getMenuFunc(Long menuId) {
        return ((UmsMenuFuncRelationMapper)this.mapper).getMenuFunc(menuId);
    }
}
