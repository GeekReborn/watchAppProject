package net.sxbang.watchapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleUserKey implements Serializable {

    private Integer roleId;

    private Integer userId;
}
