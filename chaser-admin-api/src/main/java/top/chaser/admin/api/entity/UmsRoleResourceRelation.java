package top.chaser.admin.api.entity;


import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台角色资源关系表(UmsRoleResourceRelation)实体类
 *
 * @author yzb
 * @since 2021-10-18 09:40:27
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_role_resource_relation")
public class UmsRoleResourceRelation extends TkBaseEntity {
    private static final long serialVersionUID = 859056415304898481L;

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
