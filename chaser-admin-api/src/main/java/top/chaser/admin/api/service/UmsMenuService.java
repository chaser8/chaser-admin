package top.chaser.admin.api.service;

import top.chaser.admin.api.controller.response.MenuRes;
import top.chaser.admin.api.entity.UmsMenu;
import top.chaser.framework.starter.tkmybatis.service.IService;

import java.util.List;

/**
 * 后台菜单表(UmsMenu)表服务接口
 *
 * @author yzb
 * @since 2021-10-18 09:40:24
 */
public interface UmsMenuService extends IService<UmsMenu> {
    List<MenuRes> allLevelMenu();

    List<MenuRes> getChildren(Long menuId);
}

