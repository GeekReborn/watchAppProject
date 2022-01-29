package net.sxbang.watchapp.controller.open;

import net.sxbang.watchapp.common.exception.UnauthorizedException;
import net.sxbang.watchapp.common.exception.WxUnauthorizedException;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.SysUser;
import net.sxbang.watchapp.model.vo.LoginVO;
import net.sxbang.watchapp.service.SysUserService;
import net.sxbang.watchapp.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${open-prefix}/user") // ${open-prefix} -> /open
public class UserOpenController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/list")
    public RestResult<List<SysUser>> list() {
        return sysUserService.listUser();
    }

    @RequestMapping("/roles/{userId}")
    public RestResult findUserRoleByUser(@PathVariable Integer userId) {
        return sysUserService.findUserRoleByUser(userId);
    }

//    @PostMapping(value = "/wx/login")
//    public RestResult wxLogin(@RequestBody LoginVO loginVO) throws IOException, UnauthorizedException, WxUnauthorizedException {
////        return RestResult.success(LoginUtils.getWxSessionKeyAndOpenIdByCode(loginVO.getCode()));
//
//        return sysUserService.wxLogin(loginVO.getCode(), loginVO.getClientId(), loginVO.getClientSecret());
//    }
// form / parameter
    @PostMapping(value = "/wx/login")
//    @RequestMapping("/wx/login")
    public RestResult wxLogin(String code, String clientId, String clientSecret) throws IOException, UnauthorizedException, WxUnauthorizedException {
        return sysUserService.wxLogin(code, clientId, clientSecret);
    }

}
