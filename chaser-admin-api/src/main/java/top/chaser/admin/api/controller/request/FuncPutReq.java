package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class FuncPutReq {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String code;
    @NotNull
    private Long parentId;
}
