package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RoleMergeReq {
    private Long id;
    private String name;
    private String description;
}
