package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 资源分类表(UmsResourceCategory)实体类
 *
 * @author yzb
 * @since 2021-10-25 10:14:54
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_resource_category")
public class UmsResourceCategory extends TkBaseEntity {
    private static final long serialVersionUID = -74014889311171119L;

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
     * 分类名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
}
