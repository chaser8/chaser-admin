package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台功能点和资源关系表(UmsFuncResourceRelation)实体类
 *
 * @author yzb
 * @since 2021-10-27 17:22:53
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_func_resource_relation")
public class UmsFuncResourceRelation extends TkBaseEntity {
    private static final long serialVersionUID = -12299097698884828L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 资源标识
     */
    @Column(name = "resource_id")
    private Long resourceId;
    /**
     * 功能点标识
     */
    @Column(name = "func_id")
    private Long funcId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_user")
    private Long createUser;
}
