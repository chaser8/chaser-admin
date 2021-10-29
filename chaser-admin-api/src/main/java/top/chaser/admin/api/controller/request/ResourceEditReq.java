package top.chaser.admin.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResourceEditReq {
    private Long id;
    /**
     * 资源名称
     */
    @NotNull
    private String name;
    /**
     * 资源URL
     */
    @NotNull
    private String url;
    @NotNull
    private String method;
    /**
     * 描述
     */
    private String description;

}
