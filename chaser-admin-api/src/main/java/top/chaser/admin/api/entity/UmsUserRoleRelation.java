package top.chaser.admin.api.entity;


import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户和角色关系表(UmsUserRoleRelation)实体类
 *
 * @author yzb
 * @since 2021-10-18 09:40:27
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_user_role_relation")
public class UmsUserRoleRelation extends TkBaseEntity {
    private static final long serialVersionUID = 999529733863094428L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "admin_id")
    private Long adminId;
    @Column(name = "role_id")
    private Long roleId;
}
