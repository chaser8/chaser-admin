package top.chaser.admin.api.controller;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chaser.admin.api.controller.request.MenuPutReq;
import top.chaser.admin.api.controller.response.LevelMenuGetRes;
import top.chaser.admin.api.controller.response.MenuFuncGetRes;
import top.chaser.admin.api.controller.response.MenuRes;
import top.chaser.admin.api.entity.UmsMenu;
import top.chaser.admin.api.service.UmsMenuFuncRelationService;
import top.chaser.admin.api.service.UmsMenuService;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.common.web.annotation.RestDeleteMapping;
import top.chaser.framework.common.web.annotation.RestGetMapping;
import top.chaser.framework.common.web.annotation.RestPostMapping;
import top.chaser.framework.common.web.annotation.RestPutMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;
import top.chaser.framework.common.web.session.SessionUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("menu")
@Slf4j
public class MenuController extends BaseController {
    @Autowired
    private UmsMenuService menuService;

    @Autowired
    private UmsMenuFuncRelationService menuFuncRelationService;

    @RestPostMapping("getLevelMenus")
    public R<List<LevelMenuGetRes>> getLevelMenus() {
        return R.success(menuService.allLevelMenu());
    }
    @RestPostMapping("getLevelMenuFuncs")
    public R<List<LevelMenuGetRes>> getLevelMenuFuncs() {
        return R.success(menuService.allLevelMenuFuncs());
    }

    @RestGetMapping("getChildren/{menuId}")
    public R<List<MenuRes>> getChildren(@PathVariable("menuId") Long menuId) {
        List<MenuRes> menuRes = menuService.getChildren(menuId);
        return R.success(menuRes);
    }

    @RestGetMapping("getMenuFunc/{menuId}")
    public R<List<MenuFuncGetRes>> getMenuFunc(@PathVariable("menuId") Long menuId) {
        List<MenuFuncGetRes> menuFuncGetRes = menuFuncRelationService.getMenuFunc(menuId);
        return R.success(menuFuncGetRes);
    }

    @RestPutMapping
    public R<Void> put(@Valid @RequestBody MenuPutReq menuPutReq) {
        UmsMenu umsMenu = BeanUtil.toBean(menuPutReq, UmsMenu.class);
        Long userId = Convert.toLong(SessionUtil.getCurrentUser().getUserId());
        umsMenu.setUpdateUser(userId);
        if (umsMenu.getId() == null) {
            umsMenu.setCreateUser(userId);
            menuService.insertSelective(umsMenu);
        } else {
            umsMenu.setUpdateUser(userId);
            umsMenu.setUpdateDate(new Date());
            menuService.updateByPrimaryKeySelective(umsMenu);
        }
        return R.success();
    }

    @RestDeleteMapping("{id}")
    public R<Void> delete(@Valid @NotNull @PathVariable("id") Long id) {
        menuService.deleteMenu(id);
        return R.success();
    }
}
