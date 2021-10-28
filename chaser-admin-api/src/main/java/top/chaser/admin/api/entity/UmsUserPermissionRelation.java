package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;

/**
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限)(UmsUserPermissionRelation)实体类
 *
 * @author yzb
 * @since 2021-10-26 10:56:12
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_user_permission_relation")
public class UmsUserPermissionRelation extends TkBaseEntity {
    private static final long serialVersionUID = -73203263364303992L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "permission_id")
    private Long permissionId;
    @Column(name = "type")
    private Integer type;
    @Column(name = "user_id")
    private Long userId;
}
