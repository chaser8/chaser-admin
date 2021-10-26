package top.chaser.admin.api.service;

import com.github.pagehelper.PageInfo;
import top.chaser.admin.api.controller.request.RolePageReq;
import top.chaser.admin.api.controller.response.RolePageRes;
import top.chaser.admin.api.entity.UmsRole;
import top.chaser.framework.starter.tkmybatis.service.IService;

/**
 * 后台用户角色表(UmsRole)表服务接口
 *
 * @author yzb
 * @since 2021-10-18 09:40:26
 */
public interface UmsRoleService extends IService<UmsRole> {
    PageInfo<RolePageRes> getRolePage(RolePageReq rolePageReq);
}

