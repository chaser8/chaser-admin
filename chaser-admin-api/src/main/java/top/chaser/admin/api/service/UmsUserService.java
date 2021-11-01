package top.chaser.admin.api.service;

import com.github.pagehelper.PageInfo;
import top.chaser.admin.api.controller.request.UserPageReq;
import top.chaser.admin.api.controller.request.UserRoleUpdateReq;
import top.chaser.admin.api.controller.response.UserPageRes;
import top.chaser.framework.common.web.session.Privilege;
import top.chaser.framework.common.web.session.Role;
import top.chaser.framework.starter.tkmybatis.service.IService;
import top.chaser.admin.api.entity.UmsUser;

import java.io.Serializable;
import java.util.Set;

/**
 * 后台用户表(UmsUser)表服务接口
 *
 * @author yzb
 * @since 2021-10-18 09:44:37
 */
public interface UmsUserService extends IService<UmsUser> {
    /**
     * 更新密码错误次数
     *
     * @param userCode
     * @return void
     * @author yangzb
     * @date 2021/6/10 2:57 下午
     */
    void incrementPasswordErrorTimes(String userCode);

    /**
     * 锁定账户
     *
     * @param userCode
     * @param maxPasswordErrorTimes
     * @return void
     * @author yangzb
     * @date 2021/6/10 3:12 下午
     */
    void lock(String userCode, int maxPasswordErrorTimes);

    PageInfo<UserPageRes> query(UserPageReq userPageReq);

    void updateUserRoles(UserRoleUpdateReq userRoleUpdateReq);

    Set<Role> selectUserRoles(Long userId);

    Set<Privilege> getUserPrivileges(Set<Role> roles, Long userId);
}

