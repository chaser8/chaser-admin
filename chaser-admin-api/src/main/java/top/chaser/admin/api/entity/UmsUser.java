package top.chaser.admin.api.entity;


import top.chaser.framework.starter.tkmybatis.model.TkBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 后台用户表(UmsUser)实体类
 *
 * @author yzb
 * @since 2021-10-18 09:44:37
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@Table(name = "ums_user")
public class UmsUser extends TkBaseEntity {
    private static final long serialVersionUID = -83750825498470995L;

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    /**
     * 头像
     */
    @Column(name = "icon")
    private String icon;
    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;
    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;
    /**
     * 备注信息
     */
    @Column(name = "note")
    private String note;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 最后登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;
    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @Column(name = "status")
    private String status;

    @Column(name = "pwd_error_cnt")
    private Integer pwdErrorCnt;
}
