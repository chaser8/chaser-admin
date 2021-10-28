package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台角色菜单关系表(UmsMenuFuncRelation)实体类
 *
 * @author yzb
 * @since 2021-10-27 17:22:53
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_menu_func_relation")
public class UmsMenuFuncRelation extends TkBaseEntity {
    private static final long serialVersionUID = -45421590573256073L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Long menuId;
    /**
     * 功能点标识
     */
    @Column(name = "func_id")
    private Long funcId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_user")
    private Long createUser;
}
