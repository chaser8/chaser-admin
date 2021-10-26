package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户角色和权限关系表(UmsRolePermissionRelation)实体类
 *
 * @author yzb
 * @since 2021-10-25 10:14:54
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_role_permission_relation")
public class UmsRolePermissionRelation extends TkBaseEntity {
    private static final long serialVersionUID = -71286925832264472L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "permission_id")
    private Long permissionId;
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
