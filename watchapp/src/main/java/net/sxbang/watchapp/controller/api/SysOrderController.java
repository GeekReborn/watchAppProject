package net.sxbang.watchapp.controller.api;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.common.exception.UnauthorizedException;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.common.result.ResultCode;
import net.sxbang.watchapp.model.bean.MasterRepairOrderInfo;
import net.sxbang.watchapp.model.vo.SysEvaluationVO;
import net.sxbang.watchapp.model.vo.SysOrderArchiveVO;
import net.sxbang.watchapp.model.vo.SysOrderVO;
import net.sxbang.watchapp.service.SysEvaluationService;
import net.sxbang.watchapp.service.SysOrderService;
import net.sxbang.watchapp.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "${api-prefix}/order")
public class SysOrderController implements Constants {

    @Autowired
    private SysOrderService sysOrderService;
    @Autowired
    private SysEvaluationService sysEvaluationService;

    // 搜索订单
    @GetMapping(value = "/searchOrder")
    public RestResult searchOrderList(String searchValue, Long orderLastTime, Pageable pageable, Authentication authentication) {
        return sysOrderService.searchOrderList(authentication, orderLastTime, searchValue, pageable);
    }

    // 获取订单列表
    @GetMapping(value = "/orders")
    public RestResult<List> queryOrderList(Authentication authentication, Integer orderListType, Long orderLastTime, Pageable pageable) {
        return sysOrderService.listOrder(authentication, orderListType, orderLastTime, pageable);
    }

    // 获取订单详情
    @GetMapping(value = "/{orderId}")
    public RestResult queryOrderDetail(Authentication authentication, @PathVariable Integer orderId)  {
        return sysOrderService.findOrderDetail(orderId, authentication);
    }

    // 根据code获取订单详情
    @GetMapping(value = "/code/{orderCode}")
    public RestResult queryOrderDetailByCode(Authentication authentication, @PathVariable String orderCode)  {
//        return RestResult.success("queryOrderDetailByCode: "+orderCode);
        return sysOrderService.findOrderDetailByCode(authentication,orderCode);
    }

    // 获取订单进度
    @GetMapping(value = "/{orderId}/steps")
    public RestResult queryOrderSteps(Authentication authentication, @PathVariable Integer orderId) {
        return sysOrderService.findOrderSteps(orderId, authentication);
    }

    // 创建预约
    @PostMapping(value = "/appointment/create")
    public RestResult createOrder(Authentication authentication, SysOrderVO orderVO)  {
        return sysOrderService.saveAppointment(orderVO,authentication);
    }

    // 接受预约
//    @PostMapping(value = "/{orderId}/appointment/accept")
    @PostMapping(value = "/{orderId}/appointment/accept")
    public RestResult acceptAppointment(Authentication auth, @PathVariable Integer orderId)  {
//        return RestResult.success();
        return sysOrderService.updateOrder(auth, orderId, ORDER_STATUS_2);
    }

    // 取消预约
    @PostMapping(value = "/{orderId}/appointment/cancel")
    public RestResult cancelAppointment(Authentication auth, @PathVariable Integer orderId)  {
        return sysOrderService.updateOrder(auth, orderId, ORDER_STATUS_10);
    }

    // 拒绝预约
    @PostMapping(value = "/{orderId}/appointment/refuse")
    public RestResult refuseAppointment(Authentication auth, @PathVariable Integer orderId)  {
        return sysOrderService.updateOrder(auth, orderId, ORDER_STATUS_9);
    }

    // 师傅接受维修
    @PostMapping(value = "/{orderId}/repair/accept")
    public RestResult acceptRepair(Authentication auth, @PathVariable Integer orderId, MasterRepairOrderInfo orderInfo)  {
        return sysOrderService.acceptRepair(auth,orderInfo);
    }
    // 用户确认报价
    @PostMapping(value = "/{orderId}/repair/confirm")
    public RestResult confirmRepair(Authentication authentication, @PathVariable Integer orderId,
                                    String customerSignature, boolean isAgreeRepair)  {
        return sysOrderService.confirmRepair(authentication, orderId, customerSignature, isAgreeRepair);
    }

    // 用户取消维修
    @PostMapping(value = "/{orderId}/repair/cancel")
    public RestResult cancelRepair(Authentication authentication, @PathVariable Integer orderId)  {
        return sysOrderService.updateOrder(authentication, orderId, ORDER_STATUS_11);
    }

    // 店铺双重确认
    @PostMapping(value = "/{orderId}/repair/doubleConfirm")
    public RestResult doubleConfirm(Authentication authentication, @PathVariable Integer orderId)  {
        return sysOrderService.updateOrder(authentication, orderId, ORDER_STATUS_6);
    }

    // 确认维修完成
    @PostMapping(value = "/{orderId}/repair/complete")
    public RestResult completeRepair(Authentication auth, @PathVariable Integer orderId, SysOrderArchiveVO sysOrderArchiveVO)  {
        return sysOrderService.completeRepair(auth,orderId,sysOrderArchiveVO);
    }

    // 取表
    @PostMapping(value = "/{orderId}/takeWacth")
    public RestResult takeWacth(Authentication auth, @PathVariable Integer orderId, SysOrderArchiveVO sysOrderArchiveVO)  {
        return sysOrderService.updateOrder(auth, orderId, ORDER_STATUS_8);
    }

    // 用户评价提交
    @PostMapping(value = "/{orderId}/evaluation")
    public RestResult evaluation(Authentication authentication, @PathVariable Integer orderId, SysEvaluationVO sysEvaluationVO)  {
        return sysEvaluationService.addEvaluation(authentication, orderId, sysEvaluationVO);
    }
    
}
