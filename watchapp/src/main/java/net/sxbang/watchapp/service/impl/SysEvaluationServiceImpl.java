package net.sxbang.watchapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.common.result.ResultCode;
import net.sxbang.watchapp.dao.*;
import net.sxbang.watchapp.model.SysEvaluation;
import net.sxbang.watchapp.model.SysOrder;
import net.sxbang.watchapp.model.SysOrderStepLog;
import net.sxbang.watchapp.model.SysUser;
import net.sxbang.watchapp.model.dto.SysEvaluationDTO;
import net.sxbang.watchapp.model.dto.SysShopDTO;
import net.sxbang.watchapp.model.vo.SysEvaluationVO;
import net.sxbang.watchapp.model.vo.SysShopVO;
import net.sxbang.watchapp.service.SysEvaluationService;
import net.sxbang.watchapp.service.SysShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class SysEvaluationServiceImpl implements SysEvaluationService {

    @Autowired
    private SysEvaluationDAO sysEvaluationDAO;
    @Autowired
    SysOrderDAO sysOrderDAO;

    @Autowired
    SysUserDAO sysUserDAO;

    @Autowired
    SysOrderStepLogDAO sysOrderStepLogDAO;


    @Override
    public RestResult findList(Integer shopId, Integer filter, Pageable pageable) {
        List<SysEvaluationDTO> evaluations = sysEvaluationDAO.findList(shopId,  filter,  pageable);
        List<SysEvaluationVO> resultList = new ArrayList<>();
        if (evaluations == null || evaluations.isEmpty()) {
            RestResult result = RestResult.success(resultList, 0);
            result.setPage(0, pageable);
        }
        evaluations.stream().forEach(item->{
            SysEvaluationVO vo = new SysEvaluationVO();
            BeanUtils.copyProperties(item,vo);
            resultList.add(vo);
        });
        int count = (int)sysEvaluationDAO.countList(shopId,  filter);
        RestResult result = RestResult.success(resultList, count);
        result.setPage(count, pageable);
        return result;
    }

    @Override
    public RestResult getEvaluationCount(Integer shopId) {
        return RestResult.success(sysEvaluationDAO.getEvaluationCount(shopId));
    }

    @Override
    public RestResult addEvaluation(Authentication authentication, Integer orderId, SysEvaluationVO sysEvaluationVO) {
        String username = authentication.getName();
        final int ORDER_STATUS_RATED = 12;
        // 1. 判断用户是否登录有效
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }

        // 2. 判断订单是否有效
        SysOrder order = sysOrderDAO.getOne(orderId);
        if(order == null){
            return RestResult.failure(ResultCode.ORDER_NOT_FOUND);
        }

        // 3. 保留评价记录信息
        Calendar calendar = Calendar.getInstance();
        SysEvaluation sysEvaluation = new SysEvaluation();
        BeanUtils.copyProperties(sysEvaluationVO, sysEvaluation);
        sysEvaluation.setCreateBy(sysUser.getId());
        sysEvaluation.setCreateDatetime(calendar.getTime());    // 保留当前时间 new Date() java.util
        sysEvaluationDAO.saveAndFlush(sysEvaluation);

        // 4. 更新订单状态
        order.setOrderStep(ORDER_STATUS_RATED);
        sysOrderDAO.saveAndFlush(order);

        // 5. 日志记录
        sysOrderStepLogDAO.save(new SysOrderStepLog(orderId,sysUser.getId(),ORDER_STATUS_RATED,calendar.getTime()));
        return RestResult.success();
    }
}
