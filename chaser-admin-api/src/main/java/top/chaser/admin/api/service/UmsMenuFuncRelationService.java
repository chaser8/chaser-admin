package top.chaser.admin.api.service;

import top.chaser.admin.api.controller.response.MenuFuncGetRes;
import top.chaser.admin.api.entity.UmsMenuFuncRelation;
import top.chaser.framework.starter.tkmybatis.service.IService;

import java.util.List;

/**
 * 后台角色菜单关系表(UmsMenuFuncRelation)表服务接口
 *
 * @author yzb
 * @since 2021-10-27 09:41:32
 */
public interface UmsMenuFuncRelationService extends IService<UmsMenuFuncRelation> {
    List<MenuFuncGetRes> getMenuFunc(Long menuId);
}

