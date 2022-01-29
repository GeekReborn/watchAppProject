package net.sxbang.watchapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.common.exception.UnauthorizedException;
import net.sxbang.watchapp.common.exception.WxUnauthorizedException;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.common.result.ResultCode;
import net.sxbang.watchapp.dao.SysUserDAO;
import net.sxbang.watchapp.model.dto.SysUserRoleDTO;
import net.sxbang.watchapp.model.SysUser;
import net.sxbang.watchapp.model.vo.LocalAuthTokenVO;
import net.sxbang.watchapp.model.vo.SysUserRoleVO;
import net.sxbang.watchapp.model.vo.WechatAuthTokenVO;
import net.sxbang.watchapp.service.SysUserService;
import net.sxbang.watchapp.utils.LoginUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class SysUserServiceImpl implements SysUserService,Constants {

    @Autowired
    private SysUserDAO sysUserDAO;

    @Override
    public RestResult<List<SysUser>> listUser() {
        return RestResult.success(sysUserDAO.findAll());
    }

    @Override
    public RestResult wxLogin(String code, String clientId, String clientSecret) throws IOException, WxUnauthorizedException, UnauthorizedException {
        // 1.微信系统的授权 - 获取openId
        WechatAuthTokenVO wechatAuthTokenVO = LoginUtils.getWxSessionKeyAndOpenIdByCode(code);
        if (null == wechatAuthTokenVO) {
            throw new WxUnauthorizedException();
        }
        // 1.1 添加判断当前用户是否存在的步骤，如果不存在，则选择用户？
        SysUser user = sysUserDAO.findByPhoneOrUnionId(wechatAuthTokenVO.getOpenid(),wechatAuthTokenVO.getOpenid());
        if (null == user) {
            user = new SysUser();
            user.setUnionId(wechatAuthTokenVO.getOpenid());
            user.setPassword(new BCryptPasswordEncoder().encode(""));
            user.setRegistrationDatetime(new Date());
            user.setUsername(wechatAuthTokenVO.getOpenid().substring(0,10));
            user.setStatus(true);
            user.setDelStatus(false);
            sysUserDAO.saveAndFlush(user);
            if (null == user.getId()) {
                return RestResult.failure();
            }
            sysUserDAO.saveUserRole(user.getId(),ROLE_CUSTOMER_KEY);
            return RestResult.failure();
        }
        // 2.oauth2登陆授权
        LocalAuthTokenVO localAuthTokenVO = LoginUtils.loginMyselfByPassword(wechatAuthTokenVO.getOpenid(), ""
                , clientId, clientSecret);
        if (null == localAuthTokenVO) {
            throw new UnauthorizedException();
        }
        // 3.更新用户登录时间
        sysUserDAO.updateLastLoginDatetimeByUnionIdOrPhone(Calendar.getInstance().getTime(), wechatAuthTokenVO.getOpenid());

        // 4.整合用户信息
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(wechatAuthTokenVO.getOpenid(), wechatAuthTokenVO.getOpenid());
        if (sysUser == null) {
            throw new UnauthorizedException();
        }
        // 5.整合用户信息
        localAuthTokenVO = insertSysUserToLocalAuthTokenVO(sysUser, localAuthTokenVO);

        // 6.返回accessToken
        return RestResult.success(localAuthTokenVO);
    }

    private LocalAuthTokenVO insertSysUserToLocalAuthTokenVO(SysUser user, LocalAuthTokenVO localAuthTokenVO) {

        if (null == localAuthTokenVO) {
            return localAuthTokenVO;
        }
        // 1.基本用户信息
        // 注意：不要把密码set进去
        localAuthTokenVO.setAvatarUrl(user.getAvatarUrl());
        localAuthTokenVO.setId(user.getId());
        localAuthTokenVO.setLastLoginDatetime(user.getLastLoginDatetime());
        localAuthTokenVO.setPhone(user.getPhone());
        localAuthTokenVO.setRegistrationDatetime(user.getRegistrationDatetime());
        localAuthTokenVO.setSex(user.getSex());
        localAuthTokenVO.setUsername(user.getUsername());

        // 2.用户角色
        List<SysUserRoleDTO> rolesDTO = sysUserDAO.findUserRoleByUser(user.getId());
        List<SysUserRoleVO> rolesVO = new ArrayList<SysUserRoleVO>();
        SysUserRoleVO vo = null;
        for (SysUserRoleDTO role : rolesDTO) {
            vo = new SysUserRoleVO();
            BeanUtils.copyProperties(role, vo);
            rolesVO.add(vo);
        }
        localAuthTokenVO.setRoles(rolesVO);
        // 3.返回localAuthTokenVO
        return localAuthTokenVO;
    }

    @Override
    public RestResult<List<SysUserRoleVO>> findUserRoleByUser(int userId) {
        List<SysUserRoleDTO> roles = sysUserDAO.findUserRoleByUser(userId);
        List<SysUserRoleVO> resultList = new ArrayList<SysUserRoleVO>();
        for (SysUserRoleDTO dto : roles) {
            SysUserRoleVO vo = new SysUserRoleVO();
            BeanUtils.copyProperties(dto, vo);
            resultList.add(vo);
        }
        return RestResult.success(resultList);
    }


}
