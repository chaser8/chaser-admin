package top.chaser.admin.api.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import top.chaser.admin.api.controller.request.RoleMenusUpdateReq;
import top.chaser.admin.api.controller.request.RolePageReq;
import top.chaser.admin.api.controller.response.RolePageRes;
import top.chaser.admin.api.entity.UmsRole;
import top.chaser.admin.api.entity.UmsRoleFuncRelation;
import top.chaser.admin.api.service.UmsRoleFuncRelationService;
import top.chaser.admin.api.service.UmsRoleMenuRelationService;
import top.chaser.admin.api.service.UmsRoleService;
import top.chaser.framework.common.base.bean.Status;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.common.web.session.SessionUtil;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户角色表(UmsRole)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:26
 */
@Slf4j
@Service("umsRoleService")
public class UmsRoleServiceImpl extends TkServiceImpl<UmsRole> implements UmsRoleService {
    @Resource
    private UmsRoleMenuRelationService roleMenuRelationService;
    @Resource
    private UmsRoleFuncRelationService roleFuncRelationService;

    @Override
    public PageInfo<RolePageRes> getRolePage(RolePageReq rolePageReq) {
        Sqls sqls = Sqls.custom()
                .andEqualTo("status", Status.COMMON_EFFECTIVE);

        if (StrUtil.isNotBlank(rolePageReq.getValue())) {
            sqls.andLike("name", "%" + rolePageReq.getValue() + "%");
        }
        Example.Builder builder = Example.builder(UmsRole.class).where(sqls)
                .orderBy("sort");
        PageHelper.startPage(rolePageReq.getPageNum(), rolePageReq.getPageSize());
        List<RolePageRes> list = mapper.selectByExample(builder.build())
                .stream().map(umsRole -> BeanUtil.toBean(umsRole, RolePageRes.class))
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePermission(RoleMenusUpdateReq roleMenusUpdateReq) {
        roleMenuRelationService.updateRoleMenus(roleMenusUpdateReq.getRoleId(), roleMenusUpdateReq.getMenuIds());
        roleFuncRelationService.delete(new UmsRoleFuncRelation().setRoleId(roleMenusUpdateReq.getRoleId()));

        if(!CollUtil.isEmpty(roleMenusUpdateReq.getFuncIds())){
            List<UmsRoleFuncRelation> roleFuncRelations = roleMenusUpdateReq.getFuncIds().stream().map(funcId ->
                    new UmsRoleFuncRelation()
                            .setRoleId(roleMenusUpdateReq.getRoleId())
                            .setFuncId(funcId)
                            .setCreateTime(new Date())
                            .setCreateUser(Convert.toLong(SessionUtil.getCurrentUser().getUserId()))
            ).collect(Collectors.toList());
            roleFuncRelationService.insertListSelective(roleFuncRelations);
        }


    }
}
