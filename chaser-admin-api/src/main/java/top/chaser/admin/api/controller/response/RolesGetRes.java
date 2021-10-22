package top.chaser.admin.api.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RolesGetRes {
    private long id;
    private String name;
}
