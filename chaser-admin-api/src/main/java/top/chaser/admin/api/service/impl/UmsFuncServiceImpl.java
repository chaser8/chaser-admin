package top.chaser.admin.api.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.chaser.admin.api.controller.request.FuncPutReq;
import top.chaser.admin.api.controller.request.FuncResourceRelPutReq;
import top.chaser.admin.api.entity.UmsFunc;
import top.chaser.admin.api.entity.UmsFuncResourceRelation;
import top.chaser.admin.api.entity.UmsMenuFuncRelation;
import top.chaser.admin.api.service.UmsFuncResourceRelationService;
import top.chaser.admin.api.service.UmsFuncService;
import top.chaser.admin.api.service.UmsMenuFuncRelationService;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.common.web.session.SessionUtil;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户权限表(UmsFunc)表服务实现类
 *
 * @author yzb
 * @since 2021-10-27 09:41:30
 */
@Slf4j
@Service("umsFuncService")
public class UmsFuncServiceImpl extends TkServiceImpl<UmsFunc> implements UmsFuncService {
    @Resource
    private UmsMenuFuncRelationService menuFuncRelationService;

    @Resource
    private UmsFuncResourceRelationService funcResourceRelationService;

    @Override
    public void addFunc(FuncPutReq funcPutReq) {
        UmsFunc func = BeanUtil.toBean(funcPutReq, UmsFunc.class);
        Long userId = Convert.toLong(SessionUtil.getCurrentUser().getUserId());
        func.setUpdateUser(userId);
        if (func.getId() == null) {
            func.setCreateUser(userId);
            this.mapper.insertSelective(func);
            menuFuncRelationService.insertSelective(new UmsMenuFuncRelation()
                    .setFuncId(func.getId())
                    .setMenuId(funcPutReq.getParentId())
                    .setCreateUser(userId));
        } else {
            func.setUpdateUser(userId);
            func.setUpdateDate(new Date());
            this.mapper.updateByPrimaryKeySelective(func);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFunc(@NotNull Long id) {
        menuFuncRelationService.delete(new UmsMenuFuncRelation().setFuncId(id));
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFuncRelResource(FuncResourceRelPutReq resourceRelPutReq) {
        Long userId = Convert.toLong(SessionUtil.getCurrentUser().getUserId());
        funcResourceRelationService.delete(new UmsFuncResourceRelation().setFuncId(resourceRelPutReq.getFuncId()));
        if (!CollUtil.isEmpty(resourceRelPutReq.getResourceIds())) {
            List<UmsFuncResourceRelation> funcResourceRelations = resourceRelPutReq.getResourceIds().stream().map(resourceId ->
                    new UmsFuncResourceRelation().setResourceId(resourceId).setFuncId(resourceRelPutReq.getFuncId())
                            .setCreateUser(userId)).collect(Collectors.toList());
            funcResourceRelationService.insertListSelective(funcResourceRelations);
        }

    }
}
