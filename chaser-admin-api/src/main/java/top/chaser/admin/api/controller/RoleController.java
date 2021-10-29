package top.chaser.admin.api.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import top.chaser.admin.api.controller.request.*;
import top.chaser.admin.api.controller.response.RoleGetRes;
import top.chaser.admin.api.controller.response.RoleMenuFuncGetRes;
import top.chaser.admin.api.controller.response.RolePageRes;
import top.chaser.admin.api.entity.UmsRole;
import top.chaser.admin.api.entity.UmsRoleFuncRelation;
import top.chaser.admin.api.entity.UmsRoleMenuRelation;
import top.chaser.admin.api.service.UmsMenuFuncRelationService;
import top.chaser.admin.api.service.UmsRoleFuncRelationService;
import top.chaser.admin.api.service.UmsRoleMenuRelationService;
import top.chaser.admin.api.service.UmsRoleService;
import top.chaser.framework.common.base.bean.Status;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.common.web.annotation.RestDeleteMapping;
import top.chaser.framework.common.web.annotation.RestPostMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;
import top.chaser.framework.common.web.session.SessionUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("role")
@Slf4j
public class RoleController extends BaseController {
    @Autowired
    private UmsRoleService roleService;
    @Resource
    private UmsRoleMenuRelationService roleMenuRelationService;
    @Resource
    private UmsRoleFuncRelationService roleFuncRelationService;

    @Resource
    private UmsMenuFuncRelationService menuFuncRelationService;

    @RestPostMapping("getRoles")
    public R<List<RoleGetRes>> getRoles() {
        Example.Builder builder = Example.builder(UmsRole.class)
                .where(Sqls.custom().andEqualTo("status", "1000"))
                .orderBy("sort");
        List<RoleGetRes> roles = roleService.selectByExample(builder.build())
                .stream().map(umsRole -> BeanUtil.toBean(umsRole, RoleGetRes.class))
                .collect(Collectors.toList());
        return R.success(roles);
    }

    @RestPostMapping("getRoleMenus")
    public R<List<RoleMenuFuncGetRes>> getRoleMenus(@Valid @RequestBody RoleMenusGetReq roleMenusGetReq) {
        List<RoleMenuFuncGetRes> menuFuncGetRes = roleMenuRelationService.select(new UmsRoleMenuRelation().setRoleId(roleMenusGetReq.getRoleId()))
                .stream()
                .map(umsRoleMenuRelation -> new RoleMenuFuncGetRes()
                        .setId(umsRoleMenuRelation.getMenuId())
                        .setType("menu"))
                .collect(Collectors.toList());
        menuFuncGetRes.addAll(roleFuncRelationService.select(new UmsRoleFuncRelation().setRoleId(roleMenusGetReq.getRoleId())).stream()
                .map(roleFuncRelation -> new RoleMenuFuncGetRes()
                        .setId(roleFuncRelation.getFuncId())
                        .setType("func"))
                .collect(Collectors.toList()));
        return R.success(menuFuncGetRes);
    }

    @RestPostMapping("updatePermission")
    public R<Void> updatePermission(@Valid @RequestBody RoleMenusUpdateReq roleMenusUpdateReq) {
        roleService.updatePermission(roleMenusUpdateReq);
        return R.success();
    }

    @RestPostMapping("getRolePage")
    public R<PageInfo<RolePageRes>> getRolePage(@RequestBody RolePageReq rolePageReq) {
        Sqls sqls = Sqls.custom()
                .andEqualTo("status", Status.COMMON_EFFECTIVE);

        if (StrUtil.isNotBlank(rolePageReq.getValue())) {
            sqls.andLike("name", "%" + rolePageReq.getValue() + "%");
        }
        Example.Builder builder = Example.builder(UmsRole.class).where(sqls)
                .orderBy("sort");
        PageInfo page = roleService.page(builder.build(), rolePageReq.getPageNum(), rolePageReq.getPageSize(), RolePageRes.class);
        return R.success(page);
    }

    @RestPostMapping("merge")
    public R<Void> merge(@RequestBody RoleMergeReq mergeReq) {
        UmsRole umsRole = BeanUtil.toBean(mergeReq, UmsRole.class);
        Long userId = Convert.toLong(SessionUtil.getCurrentUser().getUserId());
        umsRole.setUpdateUser(userId);
        if (mergeReq.getId() == null) {
            umsRole.setStatus("1000");
            umsRole.setCreateUser(userId);
            roleService.insertSelective(umsRole);
        } else {
            umsRole.setUpdateUser(userId);
            umsRole.setUpdateDate(new Date());
            roleService.updateByPrimaryKeySelective(umsRole);
        }

        return R.success();
    }

    @RestDeleteMapping()
    public R<Void> delete(@Valid @RequestBody RoleDeleteReq deleteReq) {
        UmsRole umsRole = BeanUtil.toBean(deleteReq, UmsRole.class);
        Long userId = Convert.toLong(SessionUtil.getCurrentUser().getUserId());
        umsRole.setUpdateUser(userId);
        umsRole.setUpdateDate(new Date());
        umsRole.setStatus("2000");
        return roleService.updateByPrimaryKeySelective(umsRole) == 1 ? R.success() : R.fail("删除角色失败");
    }
}
