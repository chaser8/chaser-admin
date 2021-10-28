package top.chaser.admin.api.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class MenuRes {
    private Long id;
    private String name;
    private Integer level;
    private String code;
    private String icon;
    private Long parentId;
    private Integer hidden;
    private Integer sort;
    private String url;
    private List<MenuRes> children;
}
