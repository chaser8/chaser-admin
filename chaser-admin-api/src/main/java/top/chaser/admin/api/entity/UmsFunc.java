package top.chaser.admin.api.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户权限表(UmsFunc)实体类
 *
 * @author yzb
 * @since 2021-10-27 17:22:52
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_func")
public class UmsFunc extends TkBaseEntity {
    private static final long serialVersionUID = -81218529083239972L;

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
     * 功能点编码
     */
    @Column(name = "code")
    private String code;
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
