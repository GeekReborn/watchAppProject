package net.sxbang.watchapp.controller.api;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "${api-prefix}/finance")
public class FinanceApiController {

    @Autowired
    private FinanceService financeService;

    /**
     * 加载当前用户所在店铺的账单可先年月份
     * @param authentication
     * @return
     */
    @PostMapping("/month")
    public RestResult loadMonth(Authentication authentication) {
        return financeService.loadYearMonth(authentication);
    }

    /**
     * 加载当前用户所在店铺，指定月份的总金额
     * @param authentication
     * @param orderYearMonth
     * @return
     */
    @PostMapping("/total")
    public RestResult total(Authentication authentication, String orderYearMonth) {
        return financeService.loadTotalByYearMonth(authentication, orderYearMonth);
    }

    /**
     * 加载当前用户所在店铺，指定月份的详细账单
     * @param authentication
     * @param orderYearMonth
     * @return
     */
    @PostMapping("/detail")
    public RestResult detail(Authentication authentication, String orderYearMonth) {
        return financeService.loadOrderDetailByYearMonth(authentication, orderYearMonth);
    }
}