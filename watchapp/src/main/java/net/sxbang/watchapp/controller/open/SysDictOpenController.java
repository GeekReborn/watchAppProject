package net.sxbang.watchapp.controller.open;

import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.vo.SysBannerVO;
import net.sxbang.watchapp.service.SysBannerService;
import net.sxbang.watchapp.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${open-prefix}/dict") // ${open-prefix} -> /open
public class SysDictOpenController {

    @Autowired
    SysDictService sysDictService;

    @GetMapping("/selector")
    public RestResult<Map> getSelector() {
        return sysDictService.findSelector();
    }

    @GetMapping("/orderStatus")
    public RestResult<Map> getOrderStatus() {
        return sysDictService.findDictByParent("order_status");
    }

    @GetMapping("/query")
    public RestResult<Map> query(String parent) {
        return sysDictService.findDictByParent(parent);
    }
}
