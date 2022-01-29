package net.sxbang.watchapp.service;

import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.model.vo.SysBannerVO;

import java.util.List;
import java.util.Map;

public interface SysDictService {
    RestResult<Map> findSelector();

    RestResult<Map> findDictByParent(String parent);
}
