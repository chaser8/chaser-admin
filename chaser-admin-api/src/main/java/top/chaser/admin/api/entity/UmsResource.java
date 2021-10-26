package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台资源表(UmsResource)实体类
 *
 * @author yzb
 * @since 2021-10-25 10:14:54
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_resource")
public class UmsResource extends TkBaseEntity {
    private static final long serialVersionUID = -31463713147853375L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 资源名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 资源URL
     */
    @Column(name = "url")
    private String url;
    /**
     * 描述
     */
    @Column(name = "description")
    private String description;
    /**
     * 资源分类ID
     */
    @Column(name = "category_id")
    private Long categoryId;
}
