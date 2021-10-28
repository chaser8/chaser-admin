package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class MenuPutReq {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer level;
    @NotNull
    private String code;
    @NotNull
    private String icon;
    @NotNull
    private Integer sort;
    @NotNull
    private String url;
    @NotNull
    private Long parentId;
}
