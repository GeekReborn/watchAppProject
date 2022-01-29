package net.sxbang.watchapp.service;

import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.bean.MasterRepairOrderInfo;
import net.sxbang.watchapp.model.vo.SysOrderArchiveVO;
import net.sxbang.watchapp.model.vo.SysOrderVO;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface SysOrderService {

    RestResult findOrderDetail(Integer orderId,Authentication authentication);

    RestResult findOrderSteps(Integer orderId, Authentication authentication);

    RestResult searchOrderList(Authentication authentication, Long orderLastTime, String searchValue, Pageable pageable);

    RestResult<List> listOrder(Authentication authentication, Integer orderListType, Long orderLastTime, Pageable pageable);

    RestResult saveAppointment(SysOrderVO orderVO, Authentication authentication);

    RestResult findOrderDetailByCode(Authentication auth, String orderCode);

    RestResult acceptRepair(Authentication auth, MasterRepairOrderInfo orderInfo);

    RestResult updateOrder(Authentication auth, Integer orderId, int step);

    RestResult confirmRepair(Authentication authentication, Integer orderId, String customerSignature, boolean isAgreeRepair);

    RestResult completeRepair(Authentication auth, Integer orderId, SysOrderArchiveVO sysOrderArchiveVO);

}
