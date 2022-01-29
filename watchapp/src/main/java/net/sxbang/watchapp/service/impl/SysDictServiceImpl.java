package net.sxbang.watchapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.dao.SysBannerDAO;
import net.sxbang.watchapp.dao.SysDictDAO;
import net.sxbang.watchapp.model.SysBanner;
import net.sxbang.watchapp.model.SysDict;
import net.sxbang.watchapp.model.bean.DictNode;
import net.sxbang.watchapp.model.vo.SysBannerVO;
import net.sxbang.watchapp.service.SysBannerService;
import net.sxbang.watchapp.service.SysDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictDAO sysDictDAO;


    // Java8 版
    @Override
    public RestResult<Map> findSelector() {
        return findDictByParent("selector");
    }

    @Override
    public RestResult<Map> findDictByParent(String parent) {
        // 1. 从数据库获取选择器的字典
        List<SysDict> selectors = sysDictDAO.findByDictParent(parent);
        // 2. 把选择器字典从List转换成Map结构
        Map<String, DictNode> selectorDictMap = selectors.stream()
                .collect(Collectors.toMap(SysDict::getDictKey, dict -> {
                    String key = dict.getDictKey();
                    String value = dict.getDictValue();
                    Map childNode = new HashMap<>();
                    return new DictNode(key,value,childNode);
                },(k1, k2)->k1));
        // 3. 把选择器字典的键单独获取出来
        Set selectorKeySet = selectorDictMap.keySet();
        // 4. 通过选择器的键获得子选择器的字典
        List<SysDict> selectorChilds = sysDictDAO.findByDictParentIn(selectorKeySet);
        // 5. 遍历子选择器
        // // tips: stream 操作不要用来产生副产物（也就是增删改操作）
        for (SysDict dict: selectorChilds) {
            // 6. 如果 dictParent 在选择器中存在，就把这个子选择器放到这个选择器的childNode里
            String key = dict.getDictKey();
            String value = dict.getDictValue();
            selectorDictMap.get(dict.getDictParent()).getChildNode()
                    .put(dict.getDictKey(), new DictNode(key,value,null));
        }
        return RestResult.success(selectorDictMap);
    }


}
