package net.sxbang.watchapp.service;

import net.sxbang.watchapp.common.exception.UnauthorizedException;
import net.sxbang.watchapp.common.exception.WxUnauthorizedException;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.SysUser;
import net.sxbang.watchapp.model.vo.SysUserRoleVO;

import java.io.IOException;
import java.util.List;

public interface SysUserService {

    RestResult<List<SysUser>> listUser();

    RestResult wxLogin(String code, String clientId, String clientSecret) throws IOException, WxUnauthorizedException, UnauthorizedException;

    RestResult<List<SysUserRoleVO>> findUserRoleByUser(int userId);
}
