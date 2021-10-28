package top.chaser.admin.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chaser.admin.api.controller.request.FuncPutReq;
import top.chaser.admin.api.service.UmsFuncService;
import top.chaser.framework.common.web.annotation.RestPutMapping;
import top.chaser.framework.common.web.controller.BaseController;
import top.chaser.framework.common.web.response.R;

import javax.validation.Valid;

@RestController
@RequestMapping("func")
@Slf4j
public class FuncController extends BaseController {
    @Autowired
    private UmsFuncService funcService;

    @RestPutMapping
    public R<Void> put(@Valid @RequestBody FuncPutReq funcPutReq) {
        funcService.addFunc(funcPutReq);
        return R.success();
    }
}
