package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class FuncResourceRelPutReq {
    @NotNull
    private Long funcId;
    @NotNull
    private List<Long> resourceIds;
}
