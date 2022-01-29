package net.sxbang.watchapp.service;

import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.vo.SysEvaluationVO;
import net.sxbang.watchapp.model.vo.SysShopVO;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.List;

public interface SysEvaluationService {

    RestResult findList(Integer shopId, Integer filter, Pageable pageable);

    RestResult getEvaluationCount(Integer shopId);

    RestResult addEvaluation(Authentication authentication, Integer orderId, SysEvaluationVO sysEvaluationVO);
}
