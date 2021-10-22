package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserRoleUpdateReq {
    @Size(min = 1,message = "参数错误")
    private List<Long> roleIds = new ArrayList<>();
    @NotNull
    private Long userId;
}
