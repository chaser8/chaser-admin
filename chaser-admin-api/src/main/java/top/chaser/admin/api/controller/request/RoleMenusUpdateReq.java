package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class RoleMenusUpdateReq {
    @NotNull
    private Long roleId;
    @NotNull
    private List<Long> menuIds;

    private List<Long> funcIds;
}
