package top.chaser.admin.api.mapper;


import org.apache.ibatis.annotations.Mapper;
import top.chaser.admin.api.controller.response.LevelMenuGetRes;
import top.chaser.admin.api.entity.UmsMenu;
import top.chaser.framework.starter.tkmybatis.mapper.TkBaseMapper;

import java.util.List;


/**
 * @author yzb
 * @since 2021-10-18 09:40:24
 */
@Mapper
public interface UmsMenuMapper extends TkBaseMapper<UmsMenu> {

    List<LevelMenuGetRes> selectMenuFuncs(Long menuId);
}
