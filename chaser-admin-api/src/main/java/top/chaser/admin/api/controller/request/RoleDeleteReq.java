package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class RoleDeleteReq {
    @NotNull
    private Long id;
}
