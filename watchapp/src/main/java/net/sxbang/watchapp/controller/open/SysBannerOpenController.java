package net.sxbang.watchapp.controller.open;

import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.SysBanner;
import net.sxbang.watchapp.model.vo.SysBannerVO;
import net.sxbang.watchapp.service.SysBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${open-prefix}/banner") // ${open-prefix} -> /open
public class SysBannerOpenController {

    @Autowired
    private SysBannerService sysBannerService;

    @GetMapping("/list")
    public RestResult<List<SysBannerVO>> list() {
        return sysBannerService.list();
    }


}
