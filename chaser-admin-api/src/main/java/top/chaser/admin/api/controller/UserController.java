package top.chaser.admin.api.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chaser.admin.api.controller.request.*;
import top.chaser.admin.api.controller.response.UserNameCheckRes;
import top.chaser.admin.api.controller.response.UserPageRes;
import top.chaser.admin.api.controller.response.UserRoleGetRes;
import top.chaser.admin.api.entity.UmsUser;
import top.chaser.admin.api.service.UmsUserRoleRelationService;
import top.chaser.admin.api.service.UmsUserService;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.common.web.annotation.RestPatchMapping;
import top.chaser.framework.common.web.annotation.RestPostMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private UmsUserService userService;

    @Autowired
    private UmsUserRoleRelationService userRoleRelationService;

    @RestPostMapping(value = "page")
    public R<PageInfo<UserPageRes>> page(@RequestBody UserPageReq userPageReq) {
        return R.success(userService.query(userPageReq));
    }

    @RestPostMapping(value = "add")
    public R<Void> add(@RequestBody UserAddReq userAddReq) {
        return userService.insertSelective(BeanUtil.toBean(userAddReq, UmsUser.class)) == 1 ? R.success() : R.fail("新增用户失败");
    }

    @RestPostMapping(value = "usernameCheck")
    public R<UserNameCheckRes> usernameCheck(@RequestBody UserNameCheckReq userNameCheckReq) {
        return userService.selectCount(BeanUtil.toBean(userNameCheckReq, UmsUser.class)) < 1 ? R.success(new UserNameCheckRes().setFlag(true)) : R.success(new UserNameCheckRes().setFlag(false));
    }

    @RestPatchMapping()
    public R<Void> update(@RequestBody UserUpdateReq userUpdateReq) {
        return userService.updateByPrimaryKeySelective(BeanUtil.toBean(userUpdateReq, UmsUser.class)) > 0 ? R.success() : R.fail("更新失败");
    }

    @RestPostMapping("getUserRoles")
    public R<List<UserRoleGetRes>> getUserRoles(@RequestBody @Valid UserRoleGetReq userRoleGetReq) {
        return R.success(userRoleRelationService.getUserRoles(userRoleGetReq));
    }

    @RestPostMapping("updateUserRoles")
    public R<Void> updateUserRoles(@RequestBody @Valid UserRoleUpdateReq userRoleUpdateReq) {
        userService.updateUserRoles(userRoleUpdateReq);
        return R.success();
    }

    @RestPostMapping("freeze")
    public R<Void> freeze(@RequestBody @Valid UserFreezeReq userFreezeReq) {
        userService.updateByPrimaryKeySelective(new UmsUser().setUserId(userFreezeReq.getUserId()).setStatus("2000"));
        return R.success();
    }

    @RestPostMapping("unfreeze")
    public R<Void> unfreeze(@RequestBody @Valid UserFreezeReq userFreezeReq) {
        userService.updateByPrimaryKeySelective(new UmsUser().setUserId(userFreezeReq.getUserId()).setStatus("1000").setPwdErrorCnt(0));
        return R.success();
    }

}
