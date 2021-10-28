package top.chaser.admin.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chaser.admin.api.entity.UmsResource;
import top.chaser.admin.api.service.UmsResourceService;
import top.chaser.framework.common.web.annotation.RestGetMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;

import java.util.List;

@RestController
@RequestMapping("resource")
@Slf4j
public class ResourceController extends BaseController {
    @Autowired
    private UmsResourceService resourceService;

    @RestGetMapping
    public R<List<UmsResource>> getAll() {
        return R.success(resourceService.selectAll());
    }
}
