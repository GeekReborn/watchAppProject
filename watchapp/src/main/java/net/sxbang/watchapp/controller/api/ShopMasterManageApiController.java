package net.sxbang.watchapp.controller.api;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.vo.SysShopMasterVO;
import net.sxbang.watchapp.service.SysShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "${api-prefix}/masterManage")
public class ShopMasterManageApiController {

    @Autowired
    private SysShopService sysShopService;

    @GetMapping("/list")
    public RestResult list(Authentication authentication) {
        return sysShopService.listShopMaster(authentication);
    }

    @PostMapping("/add")
    public RestResult add(SysShopMasterVO vo) {
        return sysShopService.addShopMaster(vo);
    }

    @PostMapping("/del")
    public RestResult delete(SysShopMasterVO vo) {
        return sysShopService.delShopMaster(vo);
    }

    @GetMapping("/getShop")
    public RestResult getShop(Authentication authentication) {
        return sysShopService.getShop(authentication);
    }
}
