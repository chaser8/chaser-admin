package top.chaser.admin.api.mapper;


import org.apache.ibatis.annotations.Mapper;
import top.chaser.admin.api.controller.response.MenuFuncGetRes;
import top.chaser.admin.api.entity.UmsMenuFuncRelation;
import top.chaser.framework.starter.tkmybatis.mapper.TkBaseMapper;

import java.util.List;


/**
 * @author yzb
 * @since 2021-10-27 09:41:31
 */
@Mapper
public interface UmsMenuFuncRelationMapper extends TkBaseMapper<UmsMenuFuncRelation> {
    List<MenuFuncGetRes> getMenuFunc(Long menuId);
}
