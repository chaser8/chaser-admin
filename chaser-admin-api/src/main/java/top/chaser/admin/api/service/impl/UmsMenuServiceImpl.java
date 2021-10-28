package top.chaser.admin.api.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import top.chaser.admin.api.controller.response.MenuRes;
import top.chaser.admin.api.entity.UmsMenu;
import top.chaser.admin.api.entity.UmsMenuFuncRelation;
import top.chaser.admin.api.entity.UmsRole;
import top.chaser.admin.api.service.UmsMenuFuncRelationService;
import top.chaser.admin.api.service.UmsMenuService;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台菜单表(UmsMenu)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:24
 */
@Slf4j
@Service("umsMenuService")
public class UmsMenuServiceImpl extends TkServiceImpl<UmsMenu> implements UmsMenuService {
    @Resource
    private UmsMenuFuncRelationService menuFuncRelationService;

    @Override
    public List<MenuRes> allLevelMenu() {
        List<UmsMenu> umsMenus = mapper.selectByExample(Example.builder(UmsRole.class).orderBy("sort").build());
        List<MenuRes> levelMenus = new ArrayList<>();
        for (UmsMenu umsMenu : umsMenus) {
            if (umsMenu.getLevel().intValue() == 1 && umsMenu.getParentId().longValue() == 0L) {
                levelMenus.add(BeanUtil.toBean(umsMenu, MenuRes.class));
            }
        }
        for (MenuRes levelMenu : levelMenus) {
            List<MenuRes> children = new ArrayList<>();
            for (UmsMenu umsMenu : umsMenus) {
                if (levelMenu.getId().longValue() == umsMenu.getParentId().longValue()) {
                    children.add(BeanUtil.toBean(umsMenu, MenuRes.class));
                }
            }
            levelMenu.setChildren(children);
        }
        return levelMenus;
    }

    @Override
    public List<MenuRes> getChildren(Long menuId) {
        return mapper.selectByExample(Example.builder(UmsMenu.class)
                        .where(Sqls.custom().andEqualTo("parentId", menuId)).orderBy("sort")
                        .build())
                .stream()
                .map(umsMenu -> BeanUtil.toBean(umsMenu, MenuRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMenu(Long id) {
        menuFuncRelationService.delete(new UmsMenuFuncRelation().setMenuId(id));
        mapper.deleteByPrimaryKey(id);
    }
}
