package net.sxbang.watchapp.service;

import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.vo.SysShopMasterVO;
import net.sxbang.watchapp.model.vo.SysShopVO;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.List;

public interface SysShopService {
//    RestResult<List<SysShopVO>> findList(String order, BigDecimal latitude, BigDecimal longitude);

    RestResult<List<SysShopVO>> findList(String order, BigDecimal latitude, BigDecimal longitude, Pageable pageable);

    RestResult findShopInfoAndContactById(Integer shopId);

    RestResult listShopMaster(Authentication auth);

    RestResult getShop(Authentication auth);

    RestResult addShopMaster(SysShopMasterVO vo);

    RestResult delShopMaster(SysShopMasterVO vo);

//    RestResult<List<SysShopVO>> findList();

//    RestResult<List<SysShopVO>> findList(Pageable pageable);
}
