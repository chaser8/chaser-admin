package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台角色菜单关系表(UmsRoleMenuRelation)实体类
 *
 * @author yzb
 * @since 2021-10-25 10:14:54
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_role_menu_relation")
public class UmsRoleMenuRelation extends TkBaseEntity {
    private static final long serialVersionUID = 718795276135632928L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;
    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Long menuId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_user")
    private Long createUser;
    @Column(name = "update_user")
    private Long updateUser;
    @Column(name = "update_date")
    private Date updateDate;
}
