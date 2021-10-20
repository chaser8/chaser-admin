package top.chaser.admin.api.service.impl;

import cn.hutool.core.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import top.chaser.admin.api.entity.UmsUser;
import top.chaser.admin.api.service.UmsUserService;
import top.chaser.framework.common.base.bean.Status;
import top.chaser.framework.common.web.session.Organization;
import top.chaser.framework.common.web.session.Privilege;
import top.chaser.framework.common.web.session.Role;
import top.chaser.framework.common.web.session.User;
import top.chaser.framework.starter.uaa.authorization.service.UaaUserDetailsService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl  extends UaaUserDetailsService {
    @Autowired
    UmsUserService userService;
    @Override
    public UserDetails loadUserByPhone(String phone) {
        Optional<List<UmsUser>> users = Optional.ofNullable(this.userService.select(new UmsUser().setUserPhone(phone).setStatus(Status.COMMON_EFFECTIVE)));
        UmsUser sysUser = users.orElseGet(() -> new ArrayList<>())
                .stream()
                .filter(user -> user.getStatus().equals(Status.COMMON_EFFECTIVE))
                .findAny().orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        return loadUserByUsername(sysUser.getUsername());
    }

    @Override
    public User loadUserExtra(User user) {
        return super.loadUserExtra(user);
    }

    @Override
    public Set<Role> getUserRoles(Serializable userId) {
        return super.getUserRoles(userId);
    }

    @Override
    public Organization getUserOrganization(Serializable userId) {
        return super.getUserOrganization(userId);
    }

    @Override
    public Set<Privilege> getUserPrivileges(Set<Role> roles, Serializable userId) {
        return super.getUserPrivileges(roles, userId);
    }

    @Override
    public void incrementPasswordErrorTimesAndLock(String username) {
        int maxPasswordErrorTimes = serverProperties.getMaxPasswordErrorTimes();
        userService.incrementPasswordErrorTimes(username);
        if(maxPasswordErrorTimes>0){
            lock(username);
        }
    }

    @Override
    public void clearPasswordErrorTimes(String username) {
        Example example = new Example(UmsUser.class);
        example.createCriteria()
                .orNotEqualTo("pwdErrorCnt",0)
                .andEqualTo("username",username);
        userService.updateByExampleSelective(new UmsUser().setPwdErrorCnt(0),example);
    }

    @Override
    public void lock(String username) {
        userService.lock(username, serverProperties.getMaxPasswordErrorTimes());
    }

    @Override
    public User getUser(Serializable userCode) {
        Optional<List<UmsUser>> users = Optional.ofNullable(this.userService.select(new UmsUser().setUsername((String) userCode)));
        UmsUser sysUser = users.orElseGet(() -> new ArrayList<>())
                .stream()
                .findAny().orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        User user = new User();
        user.setUserId(Convert.toStr(sysUser.getUserId()));
        user.setUserCode(sysUser.getUsername());
        user.setPassword(sysUser.getPassword());
        user.setUserTel(sysUser.getUserPhone());
        user.setPasswordErrorTimes(sysUser.getPwdErrorCnt());

        if (Status.COMMON_EFFECTIVE.equals(sysUser.getStatus())) {
            user.setStatus(User.Status.NORMAL);
        }else if("3000".equals(sysUser.getStatus())){
            user.setStatus(User.Status.LOCKED);
        }else {
            user.setStatus(User.Status.UNKNOWN);
        }
        user.setNickname(Convert.toStr(sysUser.getNickName()));
        return user;
    }
}
