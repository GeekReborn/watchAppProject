package net.sxbang.watchapp.dao.custom;

import net.sxbang.watchapp.model.dto.SysUserRoleDTO;

import java.util.List;

public interface SysUserCustomDAO {

    List<SysUserRoleDTO> findUserRoleByUser(int userId);

}
