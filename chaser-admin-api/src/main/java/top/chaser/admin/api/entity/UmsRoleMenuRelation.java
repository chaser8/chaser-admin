package top.chaser.admin.api.entity;


import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台角色菜单关系表(UmsRoleMenuRelation)实体类
 *
 * @author yzb
 * @since 2021-10-18 09:40:26
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_role_menu_relation")
public class UmsRoleMenuRelation extends TkBaseEntity {
    private static final long serialVersionUID = 892256579292695590L;

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
}
