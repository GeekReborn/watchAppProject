package net.sxbang.watchapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.dao.SysBannerDAO;
import net.sxbang.watchapp.model.SysBanner;
import net.sxbang.watchapp.model.vo.SysBannerVO;
import net.sxbang.watchapp.service.SysBannerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class SysBannerServiceImpl implements SysBannerService {

    @Autowired
    private SysBannerDAO sysBannerDAO;

    /**
     * 返回所有banner数据
     * @return List<SysBannerVO>
     */
    @Override
    public RestResult<List<SysBannerVO>> list() {
        List<SysBanner> entities = sysBannerDAO.findByOrderBySeq();
        if (entities != null && !entities.isEmpty()) {
            List<SysBannerVO> resultList = new ArrayList<SysBannerVO>();
            SysBannerVO vo = null;
            for (SysBanner entity : entities) {
                vo = new SysBannerVO();
                BeanUtils.copyProperties(entity, vo);
                resultList.add(vo);
            }
            return RestResult.success(resultList);
        }
        return RestResult.failure();
    }
}
