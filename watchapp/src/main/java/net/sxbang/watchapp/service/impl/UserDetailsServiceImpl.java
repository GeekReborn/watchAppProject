package net.sxbang.watchapp.service.impl;

import net.sxbang.watchapp.dao.SysUserDAO;
import net.sxbang.watchapp.model.dto.SysUserRoleDTO;
import net.sxbang.watchapp.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserDAO sysUserDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. findByUsername
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        SysUser user = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 2. findRoleByUserId
        List<SysUserRoleDTO> roles = sysUserDAO.findUserRoleByUser(user.getId());
        if (roles.isEmpty()) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (SysUserRoleDTO dto : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(dto.getName());
            authorities.add(authority);
        }

        // 微信登录授权的话，按这个密码去校验
        if (username.equals(user.getUnionId())) {
            return new User(username, new BCryptPasswordEncoder().encode(""), authorities);
        }
        return new User(username, user.getPassword(), authorities);
        // 3. return new User
//        return new User(username, new BCryptPasswordEncoder().encode("123456"), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
