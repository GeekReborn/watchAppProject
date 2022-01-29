package net.sxbang.watchapp.controller.api;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.bean.MasterRepairOrderInfo;
import net.sxbang.watchapp.model.vo.SysOrderArchiveVO;
import net.sxbang.watchapp.model.vo.SysOrderVO;
import net.sxbang.watchapp.service.SysOrderService;
import net.sxbang.watchapp.service.SysStationNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "${api-prefix}/notice")
public class SysStationNoticeController implements Constants {

    @Autowired
    SysStationNoticeService sysStationNoticeService;
    // 搜索订单
    @GetMapping(value = "/query")
    public RestResult query(Integer shopId,Integer userId, Authentication authentication) {
        return sysStationNoticeService.findStationNotice(false,shopId,userId,authentication);
    }

    @PostMapping(value = "/update")
    public RestResult updateNotices(Integer orderId,Authentication authentication){
        if(orderId == null){
            return RestResult.failure();
        }
        return sysStationNoticeService.updateNotice(orderId,authentication);

    }
}
