package net.sxbang.watchapp.service;

import net.sxbang.watchapp.common.result.RestResult;
import org.springframework.security.core.Authentication;

public interface FinanceService {
    RestResult loadYearMonth(Authentication authentication);

    RestResult loadTotalByYearMonth(Authentication authentication, String orderYearMonth);

    RestResult loadOrderDetailByYearMonth(Authentication authentication, String orderYearMonth);
}
