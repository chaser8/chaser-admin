package top.chaser.admin.api.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import top.chaser.admin.api.controller.request.UserPageReq;
import top.chaser.admin.api.controller.response.UserPageRes;
import top.chaser.admin.api.mapper.UmsUserMapper;
import top.chaser.admin.api.service.UmsUserService;

import top.chaser.admin.api.entity.UmsUser;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台用户表(UmsUser)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:44:37
 */
@Slf4j
@Service("umsUserService")
public class UmsUserServiceImpl extends TkServiceImpl<UmsUser> implements UmsUserService {
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
    public void lock(String userCode,int maxPasswordErrorTimes) {
        UmsUserMapper mapper = (UmsUserMapper) this.mapper;
        mapper.lock(userCode,maxPasswordErrorTimes);
    }

    @Override
    public PageInfo<UserPageRes> query(UserPageReq userPageReq) {
        PageInfo<UmsUser> page = page(BeanUtil.toBean(userPageReq, UmsUser.class), userPageReq.getPageNum(), userPageReq.getPageSize());
        PageHelper.startPage(userPageReq.getPageNum(), userPageReq.getPageSize());
        List<UserPageRes> list = ((UmsUserMapper) mapper).queryUser(userPageReq);
        PageInfo pageInfo = new PageInfo<>(list);

        return pageInfo;
    }
}
