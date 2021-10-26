package top.chaser.admin.api.service.impl;


import cn.hutool.core.convert.Convert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import top.chaser.admin.api.controller.request.UserPageReq;
import top.chaser.admin.api.controller.request.UserRoleUpdateReq;
import top.chaser.admin.api.controller.response.UserPageRes;
import top.chaser.admin.api.entity.UmsUser;
import top.chaser.admin.api.entity.UmsUserRoleRelation;
import top.chaser.admin.api.mapper.UmsUserMapper;
import top.chaser.admin.api.service.UmsUserRoleRelationService;
import top.chaser.admin.api.service.UmsUserService;
import top.chaser.framework.common.web.session.SessionUtil;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户表(UmsUser)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:44:37
 */
@Slf4j
@Service("umsUserService")
public class UmsUserServiceImpl extends TkServiceImpl<UmsUser> implements UmsUserService {
    @Autowired
    private UmsUserRoleRelationService userRoleRelationService;

    /**
     * incrementPasswordErrorTimes
     *
     * @param userCode
     * @return void
     * @author yangzb
     * @date 2021/6/10 3:12 下午
     */
    @Override
    public void incrementPasswordErrorTimes(String userCode) {
        UmsUserMapper mapper = (UmsUserMapper) this.mapper;
        mapper.incrementPasswordErrorTimes(userCode);
    }

    /**
     * lock
     *
     * @param userCode
     * @param maxPasswordErrorTimes
     * @return void
     * @author yangzb
     * @date 2021/6/10 3:13 下午
     */
    @Override
    public void lock(String userCode, int maxPasswordErrorTimes) {
        UmsUserMapper mapper = (UmsUserMapper) this.mapper;
        mapper.lock(userCode, maxPasswordErrorTimes);
    }

    @Override
    public PageInfo<UserPageRes> query(UserPageReq userPageReq) {
        PageHelper.startPage(userPageReq.getPageNum(), userPageReq.getPageSize());
        List<UserPageRes> list = ((UmsUserMapper) mapper).queryUser(userPageReq);
        PageInfo pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    @Transactional
    public void updateUserRoles(UserRoleUpdateReq userRoleUpdateReq) {
        userRoleRelationService.deleteByExample(
                Example.builder(UmsUserRoleRelation.class)
                        .where(Sqls.custom()
                                .andEqualTo("userId", userRoleUpdateReq.getUserId()))
                        .build());
        Long userId = Convert.toLong(SessionUtil.getCurrentUser().getUserId());
        List<UmsUserRoleRelation> userRoles = userRoleUpdateReq
                .getRoleIds()
                .stream()
                .map(roleId -> new UmsUserRoleRelation().setRoleId(roleId).setUserId(userRoleUpdateReq.getUserId())
                        .setCreateUser(userId)
                )
                .collect(Collectors.toList());
        userRoleRelationService.insertList(userRoles);
    }
}
