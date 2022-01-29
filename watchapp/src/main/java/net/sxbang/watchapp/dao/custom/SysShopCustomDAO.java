package net.sxbang.watchapp.dao.custom;

import net.sxbang.watchapp.model.dto.SysShopDTO;
import net.sxbang.watchapp.model.dto.SysShopMasterDTO;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;

public interface SysShopCustomDAO {

    List<SysShopDTO> findListOrder(String order, BigDecimal latitude, BigDecimal longitude, Pageable pageable);

    long countListOrder();

    SysShopDTO findShopInfoAndContactById(Integer shopId);

    List<SysShopMasterDTO> listShopMaster(Integer userId);
}
