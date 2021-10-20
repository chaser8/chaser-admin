package top.chaser.admin.api.entity;


import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户角色和权限关系表(UmsRolePermissionRelation)实体类
 *
 * @author yzb
 * @since 2021-10-18 09:40:26
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_role_permission_relation")
public class UmsRolePermissionRelation extends TkBaseEntity {
    private static final long serialVersionUID = -54207454243876114L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "permission_id")
    private Long permissionId;
}
