package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import top.chaser.framework.common.base.page.PageParam;

@Getter
@Setter
public class UserPageReq extends PageParam {
    private Long userId;
    private String userPhone;
    private String username;
    private String nickName;
    private String status;
    private String value;
}
