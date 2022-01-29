package net.sxbang.watchapp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRoleVO implements Serializable {

    private static final long serialVersionUID = -7531921667790204685L;

    private Integer id;

    private String name;
}
