package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import top.chaser.framework.common.base.page.PageParam;

@Getter
@Setter
@Accessors(chain = true)
public class RolePageReq extends PageParam {
    private String value;
}
