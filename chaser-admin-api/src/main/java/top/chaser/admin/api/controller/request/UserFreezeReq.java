package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserFreezeReq {
    @NotNull
    private Long userId;
}
