package top.chaser.admin.api.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class RolePageRes {
    private long id;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
}
