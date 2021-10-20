package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateReq {
    private String userPhone;
    private long userId;
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
}
