package net.sxbang.watchapp.service.impl;

import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.common.result.ResultCode;
import net.sxbang.watchapp.dao.*;
import net.sxbang.watchapp.model.*;
import net.sxbang.watchapp.model.bean.MasterRepairOrderInfo;
import net.sxbang.watchapp.model.dto.OrderListInfoDTO;
import net.sxbang.watchapp.model.dto.SysOrderStepLogDTO;
import net.sxbang.watchapp.model.vo.OrderListInfoVO;
import net.sxbang.watchapp.model.vo.SysOrderArchiveVO;
import net.sxbang.watchapp.model.vo.SysOrderStepLogVO;
import net.sxbang.watchapp.model.vo.SysOrderVO;
import net.sxbang.watchapp.service.SysOrderService;
import net.sxbang.watchapp.utils.OrderUtils;
import net.sxbang.watchapp.utils.RoleUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
public class SysOrderServiceImpl implements SysOrderService, Constants {

    @Autowired
    SysOrderDAO sysOrderDAO;

    @Autowired
    SysShopDAO sysShopDAO;

    @Autowired
    SysUserDAO sysUserDAO;

    @Autowired
    SysShopUserDAO sysShopUserDAO;

    @Autowired
    SysOrderStepLogDAO sysOrderStepLogDAO;

    @Autowired
    SysDictDAO sysDictDAO;
    @Autowired
    SysOrderQrCodeDAO sysOrderQrCodeDAO;

    @Autowired
    SysOrderArchiveDAO sysOrderArchiveDAO;


    @Override
    public RestResult searchOrderList(Authentication authentication, Long orderLastTime, String searchValue, Pageable pageable) {
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) authentication.getAuthorities();
        boolean master_role = false;
        boolean customer_role = false;
        boolean storemanager_role = false;
        boolean admin_role = false;
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_ADMIN")) {
                admin_role = true;
            } else if (role.getAuthority().equals("ROLE_SHOPOWNER")) {
                storemanager_role = true;
            } else if (role.getAuthority().equals("ROLE_MASTER")) {
                master_role = true;
            } else if (role.getAuthority().equals("ROLE_CUSTOMER")) {
                customer_role = true;
            }
        }
        String username = authentication.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        SysShopUser shopUser = sysShopUserDAO.findSysShopUserByUserId(sysUser.getId());

        List<OrderListInfoVO> orderListInfoBeanList = new ArrayList<>();
        List<OrderListInfoDTO> datas = null;
//        Pageable pageable = PageRequest.of(0,pageSize);
        Date orderLastTimeDate = new Date(orderLastTime);
        searchValue = "%" + searchValue + "%";
        /*维修师傅或者店长只能查询自己店铺的订单*/
        if (master_role || storemanager_role) {
            datas = sysOrderDAO.searchOrderListInfoByShopId(shopUser.getShopId(), searchValue, orderLastTimeDate, pageable);
        } else if (customer_role) {
            datas = sysOrderDAO.searchOrderListInfoByUserId(sysUser.getId(), searchValue, orderLastTimeDate, pageable);
        }
        if (!StringUtils.isEmpty(datas)) {
            for (OrderListInfoDTO orderListIinfoDTO : datas) {
                OrderListInfoVO orderListInfoVO = new OrderListInfoVO();
                BeanUtils.copyProperties(orderListIinfoDTO, orderListInfoVO);
                if (orderListInfoVO.getShopImage() == null && !StringUtils.isEmpty(shopUser.getShopId())) {
                    Optional<SysShop> sysShopOptional = sysShopDAO.findById(shopUser.getShopId());
                    if (sysShopOptional.isPresent()) {
                        SysShop sysShop = sysShopOptional.get();
                        orderListInfoVO.setShopImage(sysShop.getShopImage());
                    }
                }
                orderListInfoBeanList.add(orderListInfoVO);
            }
        }
        return RestResult.success(orderListInfoBeanList);
    }

    @Override
    public RestResult<List> listOrder(Authentication authentication, Integer orderListType, Long orderLastTime, Pageable pageable) {
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) authentication.getAuthorities();
        boolean masterRole = false;
        boolean customerRole = false;
        boolean storemanagerRole = false;
        boolean adminRole = false;
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_ADMIN")) {
                adminRole = true;
            } else if (role.getAuthority().equals("ROLE_SHOPOWNER")) {
                storemanagerRole = true;
            } else if (role.getAuthority().equals("ROLE_MASTER")) {
                masterRole = true;
            } else if (role.getAuthority().equals("ROLE_CUSTOMER")) {
                customerRole = true;
            }
        }
        String username = authentication.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        SysShopUser shopUser = sysShopUserDAO.findSysShopUserByUserId(sysUser.getId());

        List<OrderListInfoVO> orderListInfoBeanList = new ArrayList<>();
        List<OrderListInfoDTO> datas = null;
        String orderSteps = "";
        switch (orderListType) {
            case 0:
                orderSteps = "1,2,3,4,5,6,7,8,9,10,11,12";
                break;
            case 1:
                orderSteps = "1,2";
                break;
            case 2:
                orderSteps = "3,4,5,6,7";
                break;
            case 3:
                orderSteps = "8,9,10,11,12";
                break;
            default:
                break;
        }
        Date orderLastTimeDate = new Date(orderLastTime);
        /*维修师傅或者店长只能查询自己店铺的订单*/
        if (masterRole || storemanagerRole) {
            datas = sysOrderDAO.findOrderListInfoByShopId(shopUser.getShopId(), orderSteps, orderLastTimeDate, pageable);
        } else if (customerRole) {
            datas = sysOrderDAO.findOrderListInfoByUserId(sysUser.getId(), orderSteps, orderLastTimeDate, pageable);
        }
        if (!StringUtils.isEmpty(datas)) {
            for (OrderListInfoDTO orderListIinfoDTO : datas) {
                OrderListInfoVO orderListInfoVO = new OrderListInfoVO();
                BeanUtils.copyProperties(orderListIinfoDTO, orderListInfoVO);
                orderListInfoBeanList.add(orderListInfoVO);
            }
        }
        return RestResult.success(orderListInfoBeanList);
    }

    @Override
    public RestResult findOrderDetail(Integer orderId, Authentication auth) {
        if (!RoleUtils.isCustomer(auth) && !RoleUtils.isMaster(auth) && !RoleUtils.isStoremanager(auth)) {
            return RestResult.failure(ResultCode.ORDER_NOT_PERMISSION_SEE);
        }
        SysOrderVO orderVO = new SysOrderVO();
        Optional<SysOrder> orderOptional = sysOrderDAO.findById(orderId);
        if (!orderOptional.isPresent()) {
            return RestResult.failure(ResultCode.ORDER_NOT_FOUND);
        }
        SysOrder order = orderOptional.get();
        String username = auth.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (RoleUtils.isCustomer(auth) && !RoleUtils.isMaster(auth) && !sysUser.getId().equals(order.getCustomerId())) {
            return RestResult.failure(ResultCode.ORDER_NOT_PERMISSION_SEE);
        }
        BeanUtils.copyProperties(order, orderVO);
        SysDict dict = sysDictDAO.findByDictParentAndDictKey("order_status", order.getOrderStep().toString());
        if (dict != null) {
            orderVO.setOrderStepText(dict.getDictValue());
        } else {
            orderVO.setOrderStepText("请联系店员");
        }
        SysOrderQrCode code = null;
        int orderStep = orderVO.getOrderStep();
        int scene = orderStep == 2 ? 1 : orderStep == 7 ? 2 : 0;
        if (0 < scene) {
            code = sysOrderQrCodeDAO.findByOrderIdAndSceneAndState(orderVO.getId(), scene, true);
            if (null != code) {
                orderVO.setOrderQrCode(code.getCode());
                orderVO.setOrderQrCodeScene(code.getScene());
            }
        }
        return RestResult.success(orderVO);
    }

    @Override
    public RestResult findOrderDetailByCode(Authentication auth, String orderCode) {
        SysOrderQrCode code = sysOrderQrCodeDAO.findByCodeAndState(orderCode,true);
        if (null == code) {
            return RestResult.failure(ResultCode.ORDER_NOT_FOUND);
        }
        return this.findOrderDetail(code.getOrderId(), auth);
    }


    /* // 自定义SQL的版本
     @Override
     public RestResult findOrderSteps(Integer orderId, Authentication authentication) {
         List<SysOrderStepLogDTO> steps = sysOrderStepLogDAO.findStepAndDictByOrderId(orderId);
         List<SysOrderStepLogVO> stepResults = new ArrayList<>();
         if (steps == null) {
             return RestResult.success(stepResults);
         }
         steps.stream().forEach(step -> {
             SysOrderStepLogVO stepVO = new SysOrderStepLogVO();
             BeanUtils.copyProperties(step,stepVO);
             stepResults.add(stepVO);
         });
         return RestResult.success(stepResults);
     }*/
    // 使用JPA的版本
    @Override
    public RestResult findOrderSteps(Integer orderId, Authentication authentication) {
        List<SysOrderStepLog> steps = sysOrderStepLogDAO.findByOrderIdOrderByOrderStepDesc(orderId);
        List<SysOrderStepLogVO> stepResults = new ArrayList<>();
        if (steps == null) {
            return RestResult.success(stepResults);
        }
        List<SysDict> orderStatusDict = sysDictDAO.findByDictParent("order_status");
        Map<String, SysDict> orderStatusDictMap = orderStatusDict.stream()
                .collect(Collectors.toMap(SysDict::getDictKey, dict -> dict, (k1, k2) -> k1));
        steps.stream().forEach(step -> {
            SysOrderStepLogVO stepVO = new SysOrderStepLogVO();
            BeanUtils.copyProperties(step, stepVO);
            stepVO.setOrderStepText(orderStatusDictMap.get(step.getOrderStep().toString()).getDictValue());
            stepResults.add(stepVO);
        });
        return RestResult.success(stepResults);
    }

    @Override
    public RestResult saveAppointment(SysOrderVO orderVO, Authentication auth) {
        String username = auth.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }
        if (null == orderVO.getShopId()) {
            return RestResult.failure();
        }
        if (RoleUtils.isMaster(auth) || RoleUtils.isStoremanager(auth)) {
//            return RestResult.failure();
        }
        SysShop shop = sysShopDAO.getOne(orderVO.getShopId());
        if (null == shop) {
            return RestResult.failure();
        }
        orderVO.setOrderStep(0);
        orderVO.setCreateDatetime(new Date());
        orderVO.setCreateBy(orderVO.getCustomerId());
        orderVO.setOrderNo(OrderUtils.getOrderNumber());
        orderVO.setOrderName(shop.getName() + "维修单");
        orderVO.setOrderStep(ORDER_STATUS_1);
        //创建订单
        SysOrder order = new SysOrder();
        BeanUtils.copyProperties(orderVO, order);
        order = sysOrderDAO.saveAndFlush(order);
        // 保存操作日志
        sysOrderStepLogDAO.saveAndFlush(new SysOrderStepLog(order.getId(), sysUser.getId(),
                ORDER_STATUS_1, new Date()));
        BeanUtils.copyProperties(order, orderVO);
        return RestResult.success(orderVO);
    }
    @Override
    public RestResult acceptRepair(Authentication auth, MasterRepairOrderInfo orderInfo) {
        SysOrder order = sysOrderDAO.getOne(orderInfo.getId());
        if(order == null){
            return RestResult.failure(ResultCode.ORDER_NOT_FOUND);
        }
        //判断该维修师傅是否有权限操作该订单
        if (!RoleUtils.isMaster(auth)) {
            return RestResult.failure(ResultCode.ORDER_NOT_PERMISSION_ACTION);
        }
        // A-sql B-输入
        // a1+ a2 a3+
        // b1 b2+ b3
        // b1 b2+ b3
        // C 【2】
        // c2
        // a1 c2 a3
        SysUser user = sysUserDAO.findByPhoneOrUnionId(auth.getName(),auth.getName());
        // 输入值处理
        BeanUtils.copyProperties(orderInfo,order);
        order.setOrderStep(4);
        order.setMasterId(user.getId());
        // 数据保存到数据库
        sysOrderDAO.saveAndFlush(order);
        // 接修后，二维码，应该废弃 state = false || 0
        SysOrderQrCode sysOrderQrCode = sysOrderQrCodeDAO.findByOrderIdAndSceneAndState(order.getId(),1,true);
        if (null != sysOrderQrCode) {
            sysOrderQrCodeDAO.updateStateByOrderIdAndScene(false, order.getId(), 1);
        }
        // 鉴定日志
        sysOrderStepLogDAO.saveAndFlush(new SysOrderStepLog(order.getId(), user.getId(),
                ORDER_STATUS_3, new Date()));
        // 接修日志 - 【step，OrderID，userId ，date】
        sysOrderStepLogDAO.saveAndFlush(new SysOrderStepLog(order.getId(), user.getId(),
                ORDER_STATUS_4, new Date()));
        SysOrderVO orderVO = new SysOrderVO();
        BeanUtils.copyProperties(order,orderVO);
        return RestResult.success(orderVO);
    }
    @Override
    public RestResult updateOrder(Authentication auth, Integer orderId, int step) {

        SysOrder order = sysOrderDAO.getOne(orderId);
        if(order == null){
            return RestResult.failure(ResultCode.ORDER_NOT_FOUND);
        }
        String username = auth.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }
        //判断该维修师傅是否有权限操作该订单
        if (!RoleUtils.isMaster(auth) && !sysUser.getId().equals(order.getCustomerId())) {
            return RestResult.failure(ResultCode.ORDER_NOT_PERMISSION_ACTION);
        }

        // 更新订单状态
        order.setOrderStep(step);
        sysOrderDAO.saveAndFlush(order);
        // 日志记录
        sysOrderStepLogDAO.save(new SysOrderStepLog(orderId,sysUser.getId(),step,new Date()));
        // if 状态==接受预约
        //      创建一个二维码
        if (ORDER_STATUS_2 == step) {
            sysOrderQrCodeDAO.save(new SysOrderQrCode(orderId, OrderUtils.generatorCode(), true, QRCODE_SCENE_APPOINTMENT));
        }
        if (ORDER_STATUS_7 == step) {
            sysOrderQrCodeDAO.save(new SysOrderQrCode(orderId, OrderUtils.generatorCode(), true, QRCODE_SCENE_TAKE));
        }
        if(ORDER_STATUS_8 == step) {
            sysOrderQrCodeDAO.updateStateByOrderIdAndScene(false, order.getId(), 2);
        }
        return RestResult.success();
    }

    @Override
    public RestResult confirmRepair(Authentication authentication, Integer orderId, String customerSignature, boolean isAgreeRepair) {
        final int ORDER_STATUS_MATSER_DOUBLE_CONFIRM = 5;
        SysOrder order = sysOrderDAO.getOne(orderId);
        if(order == null){
            return RestResult.failure(ResultCode.ORDER_NOT_FOUND);
        }
        String username = authentication.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }
        // 更新订单状态
        order.setCustomerSignature(customerSignature);
        order.setAgreeRepair(isAgreeRepair);
        order.setOrderStep(ORDER_STATUS_MATSER_DOUBLE_CONFIRM);
        sysOrderDAO.saveAndFlush(order);
        // 日志记录
        sysOrderStepLogDAO.save(new SysOrderStepLog(orderId,sysUser.getId(),ORDER_STATUS_MATSER_DOUBLE_CONFIRM,new Date()));
        return RestResult.success();
    }

    @Override
    public RestResult completeRepair(Authentication auth, Integer orderId, SysOrderArchiveVO sysOrderArchiveVO) {
        SysOrder order = sysOrderDAO.getOne(orderId);
        if(order == null){
            return RestResult.failure(ResultCode.ORDER_NOT_FOUND);
        }
        String username = auth.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }
        // 1. 保存维修完成的留存档案
        SysOrderArchive sysOrderArchive = new SysOrderArchive();
        BeanUtils.copyProperties(sysOrderArchiveVO, sysOrderArchive);
        sysOrderArchive.setCreateBy(sysUser.getId());
        // new Date  java.util.date
        //
        sysOrderArchive.setCreateDatetime(Calendar.getInstance().getTime());
        sysOrderArchiveDAO.save(sysOrderArchive);
        // 2. 日志记录
        sysOrderStepLogDAO.save(new SysOrderStepLog(orderId,sysUser.getId(),ORDER_STATUS_7,new Date()));
        // 3. 更新订单状态
        order.setOrderStep(ORDER_STATUS_7);
        order.setArchiveStatus(true);
        sysOrderDAO.saveAndFlush(order);
        // 4. 创建取表码
        if (ORDER_STATUS_7 == order.getOrderStep()) {
            sysOrderQrCodeDAO.save(new SysOrderQrCode(orderId, OrderUtils.generatorCode(), true, QRCODE_SCENE_TAKE));
        }
        SysOrderVO orderVO = new SysOrderVO();
        BeanUtils.copyProperties(order, orderVO);
        return RestResult.success(orderVO);
    }


}
