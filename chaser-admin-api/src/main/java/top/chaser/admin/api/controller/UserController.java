package top.chaser.admin.api.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import top.chaser.admin.api.controller.request.UserAddReq;
import top.chaser.admin.api.controller.request.UserNameCheckReq;
import top.chaser.admin.api.controller.request.UserPageReq;
import top.chaser.admin.api.controller.request.UserUpdateReq;
import top.chaser.admin.api.controller.response.UserNameCheckRes;
import top.chaser.admin.api.controller.response.UserPageRes;
import top.chaser.admin.api.entity.UmsUser;
import top.chaser.admin.api.service.UmsUserService;
import top.chaser.framework.common.base.util.BeanUtil;
import top.chaser.framework.common.base.util.JSONUtil;
import top.chaser.framework.common.web.annotation.RestPatchMapping;
import top.chaser.framework.common.web.annotation.RestPostMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;

import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private UmsUserService userService;

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
}
