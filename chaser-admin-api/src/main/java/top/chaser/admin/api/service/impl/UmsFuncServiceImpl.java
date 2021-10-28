package top.chaser.admin.api.service.impl;


import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.chaser.admin.api.controller.request.FuncPutReq;
import top.chaser.admin.api.entity.UmsFunc;
import top.chaser.admin.api.entity.UmsMenuFuncRelation;
import top.chaser.admin.api.service.UmsFuncService;
import top.chaser.admin.api.service.UmsMenuFuncRelationService;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.common.web.session.SessionUtil;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

import javax.annotation.Resource;
import java.util.Date;

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
    @Override
    public void addFunc(FuncPutReq funcPutReq) {
        UmsFunc func = BeanUtil.toBean(funcPutReq, UmsFunc.class);
        Long userId = Convert.toLong(SessionUtil.getCurrentUser().getUserId());
        func.setUpdateUser(userId);
        if (func.getId() == null) {
            func.setCreateUser(userId);
            this.mapper.insertSelective(func);
            menuFuncRelationService.insertSelective(new UmsMenuFuncRelation().setFuncId(func.getId()).setMenuId(funcPutReq.getParentId()));
        } else {
            func.setUpdateUser(userId);
            func.setUpdateDate(new Date());
            this.mapper.updateByPrimaryKeySelective(func);
        }
    }
}
