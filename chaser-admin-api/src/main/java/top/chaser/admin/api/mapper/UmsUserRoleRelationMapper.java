package top.chaser.admin.api.mapper;


import top.chaser.admin.api.controller.request.UserRoleGetReq;
import top.chaser.admin.api.controller.response.UserRoleGetRes;
import top.chaser.framework.common.web.response.R;
import top.chaser.framework.starter.tkmybatis.mapper.TkBaseMapper;
import top.chaser.admin.api.entity.UmsUserRoleRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author yzb
 * @since 2021-10-18 09:40:27
 */
@Mapper
public interface UmsUserRoleRelationMapper extends TkBaseMapper<UmsUserRoleRelation> {
    List<UserRoleGetRes> getUserRoles(UserRoleGetReq userRoleGetReq);
}
