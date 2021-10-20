package top.chaser.admin.api.service.impl;


import top.chaser.admin.api.service.UmsMenuService;

import top.chaser.admin.api.entity.UmsMenu;
import top.chaser.framework.starter.tkmybatis.service.TkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 后台菜单表(UmsMenu)表服务实现类
 *
 * @author yzb
 * @since 2021-10-18 09:40:24
 */
@Slf4j
@Service("umsMenuService")
public class UmsMenuServiceImpl extends TkServiceImpl<UmsMenu> implements UmsMenuService {

}
