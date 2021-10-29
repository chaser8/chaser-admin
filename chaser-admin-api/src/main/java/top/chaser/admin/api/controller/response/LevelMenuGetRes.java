package top.chaser.admin.api.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class LevelMenuGetRes {
    private Long id;
    private String name;
    private String code;
    private String type;
    private Integer level;

    public String getKey() {
        return type + "_" + id;
    }

    private String key;
    private List<LevelMenuGetRes> children;
}
