package net.sxbang.watchapp.dao.custom;

import net.sxbang.watchapp.model.dto.OrderListInfoDTO;
import net.sxbang.watchapp.model.dto.ShopOrderDetailDTO;
import net.sxbang.watchapp.model.dto.SysOrderDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface SysOrderCustomDAO {

    List<OrderListInfoDTO> findOrderListInfoByShopId(Integer shopIds, String orderSteps, Date orderLastTimeText, Pageable pageable);

    List<OrderListInfoDTO> findOrderListInfoByUserId(Integer userId, String orderSteps, Date orderLastTimeText, Pageable pageable);

    List<OrderListInfoDTO> searchOrderListInfoByShopId(Integer shopId, String searchValue, Date orderLastTimeDate, Pageable pageable);

    List<OrderListInfoDTO> searchOrderListInfoByUserId(Integer id, String searchValue, Date orderLastTimeDate, Pageable pageable);

    List<String> loadYearMonthByUserId(Integer userId);

    BigDecimal loadTotalByYearMonth(Integer userId, int year, int month);

    List<ShopOrderDetailDTO> loadOrderDetailByYearMonth(Integer userId, int year, int month);
}
