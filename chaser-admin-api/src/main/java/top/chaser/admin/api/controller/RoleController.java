package top.chaser.admin.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import top.chaser.admin.api.controller.response.RolesGetRes;
import top.chaser.admin.api.entity.UmsRole;
import top.chaser.admin.api.service.UmsRoleService;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.common.web.annotation.RestPostMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("role")
@Slf4j
public class RoleController extends BaseController {
    @Autowired
    private UmsRoleService roleService;

    @RestPostMapping("getRoles")
    public R<List<RolesGetRes>> getRoles() {
        List<RolesGetRes> roles = roleService.selectByExample(Example.builder(UmsRole.class)
                        .where(Sqls.custom().andEqualTo("status", "1000"))
                        .orderBy("sort")
                        .build())
                .stream().map(umsRole -> BeanUtil.toBean(umsRole, RolesGetRes.class))
                .collect(Collectors.toList());
        return R.success(roles);
    }

}
