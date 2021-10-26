package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户和角色关系表(UmsUserRoleRelation)实体类
 *
 * @author yzb
 * @since 2021-10-26 09:18:20
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_user_role_relation")
public class UmsUserRoleRelation extends TkBaseEntity {
    private static final long serialVersionUID = -28133148541115784L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "user_id")
    private Long userId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_user")
    private Long createUser;
}
