package top.chaser.admin.api.service;

import top.chaser.admin.api.controller.request.FuncPutReq;
import top.chaser.admin.api.controller.request.FuncResourceRelPutReq;
import top.chaser.admin.api.entity.UmsFunc;
import top.chaser.framework.starter.tkmybatis.service.IService;

/**
 * 后台用户权限表(UmsFunc)表服务接口
 *
 * @author yzb
 * @since 2021-10-27 09:41:30
 */
public interface UmsFuncService extends IService<UmsFunc> {
    void addFunc(FuncPutReq funcPutReq);

    void deleteFunc(Long id);

    void updateFuncRelResource(FuncResourceRelPutReq resourceRelPutReq);
}

