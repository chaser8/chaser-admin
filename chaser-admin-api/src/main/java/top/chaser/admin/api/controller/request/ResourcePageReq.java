package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import top.chaser.framework.common.base.page.PageParam;

@Getter
@Setter
public class ResourcePageReq extends PageParam {
    private String value;
}
