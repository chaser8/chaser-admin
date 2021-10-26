package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户权限表(UmsPermission)实体类
 *
 * @author yzb
 * @since 2021-10-25 10:14:53
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_permission")
public class UmsPermission extends TkBaseEntity {
    private static final long serialVersionUID = -87051559384501485L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 父级权限id
     */
    @Column(name = "pid")
    private Long pid;
    /**
     * 名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 权限值
     */
    @Column(name = "value")
    private String value;
    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;
    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    @Column(name = "type")
    private Integer type;
    /**
     * 前端资源路径
     */
    @Column(name = "uri")
    private String uri;
    /**
     * 启用状态；0->禁用；1->启用
     */
    @Column(name = "status")
    private String status;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
}
