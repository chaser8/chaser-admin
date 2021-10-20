package top.chaser.admin.api.controller.response;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import top.chaser.framework.common.base.page.PageParam;

import java.util.Date;

@Getter
@Setter
public class UserPageRes{
    private Long userId;
    private String userPhone;
    private String username;
    private String password;
    /**
     * 头像
     */
    private String icon;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 备注信息
     */
    private String note;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后登录时间
     */
    private Date loginTime;
    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private String status;

    private Integer pwdErrorCnt;
    private String roleName;
    private String statusName;
}
