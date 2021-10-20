package top.chaser.admin.api.entity;


import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户角色表(UmsRole)实体类
 *
 * @author yzb
 * @since 2021-10-18 09:40:26
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_role")
public class UmsRole extends TkBaseEntity {
    private static final long serialVersionUID = 430426698456916385L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 描述
     */
    @Column(name = "description")
    private String description;
    /**
     * 后台用户数量
     */
    @Column(name = "admin_count")
    private Integer adminCount;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 启用状态：0->禁用；1->启用
     */
    @Column(name = "status")
    private String status;
    @Column(name = "sort")
    private Integer sort;
}
