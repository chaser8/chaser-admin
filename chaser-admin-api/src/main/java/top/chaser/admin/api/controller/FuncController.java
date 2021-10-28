package top.chaser.admin.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chaser.admin.api.controller.request.FuncPutReq;
import top.chaser.admin.api.controller.request.FuncResourceRelPutReq;
import top.chaser.admin.api.entity.UmsFuncResourceRelation;
import top.chaser.admin.api.service.UmsFuncResourceRelationService;
import top.chaser.admin.api.service.UmsFuncService;
import top.chaser.framework.common.web.annotation.RestDeleteMapping;
import top.chaser.framework.common.web.annotation.RestGetMapping;
import top.chaser.framework.common.web.annotation.RestPutMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("func")
@Slf4j
public class FuncController extends BaseController {
    @Autowired
    private UmsFuncService funcService;

    @Autowired
    private UmsFuncResourceRelationService funcResourceRelationService;

    @RestPutMapping
    public R<Void> put(@Valid @RequestBody FuncPutReq funcPutReq) {
        funcService.addFunc(funcPutReq);
        return R.success();
    }

    @RestDeleteMapping("{id}")
    public R<Void> delete(@Valid @NotNull @PathVariable("id") Long id) {
        funcService.deleteFunc(id);
        return R.success();
    }

    @RestGetMapping("{id}/resources")
    public R<List<UmsFuncResourceRelation>> getRelReources(@Valid @NotNull @PathVariable("id") Long id) {
        return R.success(funcResourceRelationService.select(new UmsFuncResourceRelation().setFuncId(id)));
    }

    @RestPutMapping("/resource")
    public R<Void> updateFuncRelResource(@Valid @RequestBody FuncResourceRelPutReq resourceRelPutReq ) {
        funcService.updateFuncRelResource(resourceRelPutReq);
        return R.success();
    }
}
