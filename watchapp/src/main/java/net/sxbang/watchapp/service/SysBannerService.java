package net.sxbang.watchapp.service;

import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.vo.SysBannerVO;

import java.util.List;

public interface SysBannerService {
    RestResult<List<SysBannerVO>> list();
}
