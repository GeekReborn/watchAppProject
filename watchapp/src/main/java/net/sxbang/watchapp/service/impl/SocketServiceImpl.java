package net.sxbang.watchapp.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.dao.SysDictDAO;
import net.sxbang.watchapp.dao.SysShopUserDAO;
import net.sxbang.watchapp.dao.SysStationNoticeDAO;
import net.sxbang.watchapp.dao.SysUserDAO;
import net.sxbang.watchapp.model.SysDict;
import net.sxbang.watchapp.model.SysShopUser;
import net.sxbang.watchapp.model.SysStationNotice;
import net.sxbang.watchapp.model.SysUser;
import net.sxbang.watchapp.model.bean.DictNode;
import net.sxbang.watchapp.service.SocketService;
import net.sxbang.watchapp.service.SysDictService;
import net.sxbang.watchapp.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class SocketServiceImpl implements SocketService {

    @Autowired
    SysStationNoticeDAO sysStationNoticeDAO;
    @Autowired
    SysShopUserDAO sysShopUserDAO;
    @Autowired
    SysUserDAO sysUserDAO;

    // 通过accessToken获取shopUser
    @Override
    public SysShopUser findShopIdByAuthentication(Authentication authentication, Integer userId) {
        if (null == authentication && null == userId) {
            return null;
        }
        SysUser sysUser = null;
        if (null != authentication) {
            String username = authentication.getName();
            sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        }
        if (null != sysUser) {
            userId = sysUser.getId();
        }
        SysShopUser shopUser = sysShopUserDAO.findSysShopUserByUserId(userId);
        if (null == shopUser) {
            return null;
        }
        return shopUser;
    }

    // 保存消息以便后续通知
    @Override
    public SysStationNotice saveNotice(String dataJsonString, Authentication auth) {
        if (null == dataJsonString) {
            return null;
        }
        SysStationNotice sysStationNotice = JSONObject.parseObject(dataJsonString, SysStationNotice.class);
        log.info("...saveNotice...SysStationNotice: " + sysStationNotice);
        sysStationNotice.setCreatedBy(0);
        // save
        sysStationNotice.setMessage("你有一个订单发生改变，请前往查看");
        sysStationNoticeDAO.save(sysStationNotice);
        return sysStationNotice;
    }

    @Override
    public SysUser getUserByAuthentication(Authentication authentication, Integer userId) {
        if (null == authentication && null == userId) {
            return null;
        }
        SysUser user = null;
        if (null != authentication) {
            String username = authentication.getName();
            user = sysUserDAO.findByPhoneOrUnionId(username, username);
        } else {
            Optional<SysUser> userOptional = sysUserDAO.findById(userId);
            user = userOptional.get();
        }
        return user;
    }
}
