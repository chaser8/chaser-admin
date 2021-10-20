package top.chaser.admin.api.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.chaser.admin.api.entity.UmsUser;
import top.chaser.admin.api.service.UmsUserService;
import top.chaser.framework.common.web.annotation.RestPostMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;

import java.util.Map;

@RestController
public class TestController extends BaseController {
    @Autowired
    private UmsUserService userService;

    @RestPostMapping(value = "/test")
    public R<PageInfo<UmsUser>> api(@RequestBody Map map) {
        return R.success(userService.page(new UmsUser(),1,100));
    }
}
