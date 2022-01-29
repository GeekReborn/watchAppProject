package net.sxbang.watchapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.common.result.RestResult;
import net.sxbang.watchapp.dao.SysRoleUserDAO;
import net.sxbang.watchapp.dao.SysShopDAO;
import net.sxbang.watchapp.dao.SysShopUserDAO;
import net.sxbang.watchapp.dao.SysUserDAO;
import net.sxbang.watchapp.model.SysRoleUser;
import net.sxbang.watchapp.model.SysShopUser;
import net.sxbang.watchapp.model.SysUser;
import net.sxbang.watchapp.model.dto.SysShopDTO;
import net.sxbang.watchapp.model.dto.SysShopMasterDTO;
import net.sxbang.watchapp.model.vo.SysShopMasterVO;
import net.sxbang.watchapp.model.vo.SysShopVO;
import net.sxbang.watchapp.service.SysShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class SysShopServiceImpl implements SysShopService {

    @Autowired
    private SysShopDAO sysShopDAO;

    @Autowired
    private SysUserDAO sysUserDAO;

    @Autowired
    private SysShopUserDAO sysShopUserDAO;

    @Autowired
    private SysRoleUserDAO sysRoleUserDAO;

    @Override
    public RestResult<List<SysShopVO>> findList(String order, BigDecimal latitude, BigDecimal longitude, Pageable pageable) {
        List<SysShopDTO> pages = sysShopDAO.findListOrder(order, latitude, longitude, pageable);
        if (pages != null && !pages.isEmpty()) {
            List<SysShopVO> resultList = new ArrayList<SysShopVO>();
            SysShopVO vo = null;
            for (SysShopDTO entity : pages) {
                vo = new SysShopVO();
                BeanUtils.copyProperties(entity, vo);
                resultList.add(vo);
            }

            long count = sysShopDAO.countListOrder();
            RestResult result = RestResult.success(resultList);
            result.setPage(count, pageable);
            return result;
        }

        return RestResult.failure();
    }

    @Override
    public RestResult findShopInfoAndContactById(Integer shopId) {
        SysShopDTO shop = sysShopDAO.findShopInfoAndContactById(shopId);
        if (!Optional.ofNullable(shop).isPresent()) {
            return RestResult.failure();
        }
        SysShopVO shopVO = new SysShopVO();
        BeanUtils.copyProperties(shop, shopVO);
        return RestResult.success(shopVO);
    }

    @Override
    public RestResult listShopMaster(Authentication auth) {
        String username = auth.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }

        List<SysShopMasterDTO> masterList = sysShopDAO.listShopMaster(sysUser.getId());
        List<SysShopMasterVO> resultList = new ArrayList<SysShopMasterVO>();
        for (SysShopMasterDTO dto : masterList) {
            SysShopMasterVO vo = new SysShopMasterVO();
            BeanUtils.copyProperties(dto, vo);
            resultList.add(vo);
        }
        return RestResult.success(resultList);
    }

    @Override
    public RestResult getShop(Authentication auth) {
        String username = auth.getName();
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(username, username);
        if (null == sysUser) {
            return RestResult.failure();
        }

        return RestResult.success(sysShopUserDAO.findByUserId(sysUser.getId()));
    }

    @Override
    public RestResult addShopMaster(SysShopMasterVO vo) {
        // 1. 判断参数不为空
        if (vo == null || vo.getShopId() == null || vo.getPhone() == null) {
            return RestResult.failure();
        }

        // 2. 查找手机号是否有对应的用户
        SysUser sysUser = sysUserDAO.findByPhoneOrUnionId(vo.getPhone(), vo.getPhone());
        if (null == sysUser) {
            return RestResult.failure();
        }

        // 3. 添加角色
        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setRoleId(Constants.ROLE_MASTER_KEY);
        sysRoleUser.setUserId(sysUser.getId());
        sysRoleUserDAO.saveAndFlush(sysRoleUser);

        // 4. 添加店铺关联
        SysShopUser sysShopUser = new SysShopUser();
        sysShopUser.setShopId(vo.getShopId());
        sysShopUser.setUserId(sysUser.getId());
        sysShopUserDAO.saveAndFlush(sysShopUser);

        return RestResult.success();
    }

    @Override
    public RestResult delShopMaster(SysShopMasterVO vo) {
        // 1. 判断参数不为空
        if (vo == null || vo.getShopId() == null || vo.getUserId() == null) {
            return RestResult.failure();
        }

        // 2. 删除角色
        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setRoleId(Constants.ROLE_MASTER_KEY);
        sysRoleUser.setUserId(vo.getUserId());
        sysRoleUserDAO.delete(sysRoleUser);

        // 3. 删除店铺关联
        SysShopUser sysShopUser = new SysShopUser();
        sysShopUser.setShopId(vo.getShopId());
        sysShopUser.setUserId(vo.getUserId());
        sysShopUserDAO.delete(sysShopUser);

        return RestResult.success();
    }
}
