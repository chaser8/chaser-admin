package top.chaser.admin.api.controller;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import top.chaser.admin.api.controller.request.ResourceEditReq;
import top.chaser.admin.api.controller.request.ResourcePageReq;
import top.chaser.admin.api.entity.UmsResource;
import top.chaser.admin.api.service.UmsResourceService;
import top.chaser.framework.common.web.annotation.RestDeleteMapping;
import top.chaser.framework.common.web.annotation.RestGetMapping;
import top.chaser.framework.common.web.annotation.RestPostMapping;
import top.chaser.framework.common.web.annotation.RestPutMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @RestPostMapping
    public R<PageInfo<UmsResource>> page(@RequestBody ResourcePageReq resourcePageReq) {
        Sqls sqls = Sqls.custom();
        if (StrUtil.isNotBlank(resourcePageReq.getValue())) {
            sqls.andLike("name", "%" + resourcePageReq.getValue() + "%")
                    .orLike("url", "%" + resourcePageReq.getValue() + "%");
        }

        Example example = Example.builder(UmsResource.class)
                .where(sqls)
                .orderByDesc("id")
                .build();
        return R.success(resourceService.page(example, resourcePageReq.getPageNum(), resourcePageReq.getPageSize()));
    }

    @RestPutMapping
    public R<Void> merge(@Valid @RequestBody ResourceEditReq resourceEditReq) {
        resourceService.merge(resourceEditReq);
        return R.success();
    }

    @RestDeleteMapping("{id}")
    public R<Void> delete(@Valid @NotNull @PathVariable("id") Long id) {
        resourceService.delete(id);
        return R.success();
    }
}
