package net.sxbang.watchapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.dao.SysOrderDAO;
import net.sxbang.watchapp.dao.SysUserDAO;
import net.sxbang.watchapp.model.SysUser;
import net.sxbang.watchapp.model.dto.ShopOrderDetailDTO;
import net.sxbang.watchapp.model.vo.ShopOrderDetailVO;
import net.sxbang.watchapp.service.FinanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private SysUserDAO sysUserDAO;

    @Autowired
    private SysOrderDAO sysOrderDAO;

    @Override
    public RestResult loadYearMonth(Authentication authentication) {
        // 1. 检查当前登录用户有效性
        String username = authentication.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }

        // 2. 查询当前用户，有账的月份列表
        List<String> yearMonthList = sysOrderDAO.loadYearMonthByUserId(sysUser.getId());
        return RestResult.success(yearMonthList);
    }

    @Override
    public RestResult loadTotalByYearMonth(Authentication authentication, String orderYearMonth) {
        // 1. 检查当前登录用户有效性
        String username = authentication.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }

        // 2. 检查年月不为空
        if (StringUtils.isEmpty(orderYearMonth)) {
            return RestResult.failure();
        }

        // 3. 拆分年月份
        String[] yearMonth = orderYearMonth.split("-");
        if (yearMonth.length == 2) {
            int year = Integer.valueOf(yearMonth[0]);
            int month = Integer.valueOf(yearMonth[1]);

            // 4. 读指定月份的总金额
            BigDecimal total = sysOrderDAO.loadTotalByYearMonth(sysUser.getId(), year, month);
            return RestResult.success(total);
        } else {
            return RestResult.failure();
        }
    }

    @Override
    public RestResult loadOrderDetailByYearMonth(Authentication authentication, String orderYearMonth) {
        // 1. 检查当前登录用户有效性
        String username = authentication.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }

        // 2. 检查年月不为空
        if (StringUtils.isEmpty(orderYearMonth)) {
            return RestResult.failure();
        }

        // 3. 拆分年月份
        String[] yearMonth = orderYearMonth.split("-");
        if (yearMonth.length == 2) {
            int year = Integer.valueOf(yearMonth[0]);
            int month = Integer.valueOf(yearMonth[1]);

            // 4. 读取指定月份的账单详情
            List<ShopOrderDetailDTO> dtoList = sysOrderDAO.loadOrderDetailByYearMonth(sysUser.getId(), year, month);
            List<ShopOrderDetailVO> resultList = new ArrayList<ShopOrderDetailVO>();
            for (ShopOrderDetailDTO dto : dtoList) {
                ShopOrderDetailVO vo = new ShopOrderDetailVO();
                BeanUtils.copyProperties(dto, vo);
                resultList.add(vo);
            }
            return RestResult.success(resultList);
        } else {
            return RestResult.failure();
        }
    }
}
