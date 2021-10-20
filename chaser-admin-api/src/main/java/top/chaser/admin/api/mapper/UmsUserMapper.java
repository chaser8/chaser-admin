package top.chaser.admin.api.mapper;


import org.apache.ibatis.annotations.Param;
import top.chaser.admin.api.controller.request.UserPageReq;
import top.chaser.admin.api.controller.response.UserPageRes;
import top.chaser.framework.starter.tkmybatis.mapper.TkBaseMapper;
import top.chaser.admin.api.entity.UmsUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author yzb
 * @since 2021-10-18 09:44:36
 */
@Mapper
public interface UmsUserMapper extends TkBaseMapper<UmsUser> {
    void incrementPasswordErrorTimes(@Param("userCode") String userCode);

    void lock(@Param("userCode") String userCode, @Param("maxPasswordErrorTimes") int maxPasswordErrorTimes);

    List<UserPageRes> queryUser(UserPageReq userPageReq);

}
