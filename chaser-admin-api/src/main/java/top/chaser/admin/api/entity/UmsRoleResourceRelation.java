package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;

/**
 * 后台角色资源关系表(UmsRoleResourceRelation)实体类
 *
 * @author yzb
 * @since 2021-10-25 10:14:54
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_role_resource_relation")
public class UmsRoleResourceRelation extends TkBaseEntity {
    private static final long serialVersionUID = -71189526042422535L;

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
     * 资源ID
     */
    @Column(name = "resource_id")
    private Long resourceId;
}
