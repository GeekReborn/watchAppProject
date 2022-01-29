package net.sxbang.watchapp.service;

import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.SysShopUser;
import net.sxbang.watchapp.model.SysStationNotice;
import net.sxbang.watchapp.model.SysUser;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface SocketService {

    SysShopUser findShopIdByAuthentication(Authentication authentication, Integer userId);

    SysStationNotice saveNotice(String dataJsonString, Authentication auth);

    SysUser getUserByAuthentication(Authentication authentication, Integer userId);

}
