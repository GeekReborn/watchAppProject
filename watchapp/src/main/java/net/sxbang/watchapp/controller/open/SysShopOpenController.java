package net.sxbang.watchapp.controller.open;

import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.service.SysEvaluationService;
import net.sxbang.watchapp.service.SysShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("${open-prefix}/shop")
public class SysShopOpenController {

    @Autowired
    private SysShopService sysShopService;
    @Autowired
    private SysEvaluationService sysEvaluationService;

    /**
     * ?order=distance&latitude=&longitude=&page=0&size=5
     * ?
     * 获取店铺列表
     *
     * @return
     */
    @GetMapping("/list")
    public RestResult list(String order, BigDecimal latitude, BigDecimal longitude, Pageable pageable) {
        return sysShopService.findList(order, latitude, longitude, pageable);
    }

    /**
     * 获取店铺列表
     *
     * @param shopId
     * @return RestResult
     */
    @GetMapping("/{shopId}")
    public RestResult findShopInfoAndContactById(@PathVariable Integer shopId) {
        return sysShopService.findShopInfoAndContactById(shopId);
    }

    /**
     * 获取店铺评价列表
     * /shop/7/evaluations?filter=0&currentPage=0&pageSize=15
     * @param shopId
     * @return RestResult
     */
    @GetMapping("/{shopId}/evaluations")
    public RestResult getEvaluations(@PathVariable Integer shopId, Integer filter, Pageable pageable) {
        return sysEvaluationService.findList(shopId,  filter,  pageable);
    }
    /**
     * 获取店铺评价分类数量
     * /shop/7/evaluations?filter=0&currentPage=0&pageSize=15
     * @param shopId
     * @return RestResult
     */
    @GetMapping("/{shopId}/evaluations/count")
    public RestResult getEvaluationCount(@PathVariable Integer shopId) {
        return sysEvaluationService.getEvaluationCount(shopId);
    }

}
