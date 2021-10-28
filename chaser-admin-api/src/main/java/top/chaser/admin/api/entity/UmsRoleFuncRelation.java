package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户角色和权限关系表(UmsRoleFuncRelation)实体类
 *
 * @author yzb
 * @since 2021-10-27 17:22:53
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_role_func_relation")
public class UmsRoleFuncRelation extends TkBaseEntity {
    private static final long serialVersionUID = 732380604552990537L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "func_id")
    private Long funcId;
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
