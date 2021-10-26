package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台菜单表(UmsMenu)实体类
 *
 * @author yzb
 * @since 2021-10-25 10:14:53
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_menu")
public class UmsMenu extends TkBaseEntity {
    private static final long serialVersionUID = -38249673452603058L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private Long parentId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 菜单名称
     */
    @Column(name = "title")
    private String title;
    /**
     * 菜单级数
     */
    @Column(name = "level")
    private Integer level;
    /**
     * 菜单排序
     */
    @Column(name = "sort")
    private Integer sort;
    /**
     * 前端名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 前端图标
     */
    @Column(name = "icon")
    private String icon;
    /**
     * 前端隐藏
     */
    @Column(name = "hidden")
    private Integer hidden;
}
