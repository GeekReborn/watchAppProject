package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.model.SysStationNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SysStationNoticeDAO extends JpaRepository<SysStationNotice,Integer> {
    List<SysStationNotice> findByIsReadAndNotifyShopId(Boolean isRead, Integer notifyShopId);

    List<SysStationNotice> findByIsReadAndNotifyCustomerId(Boolean isRead, Integer notifyShopId);

    @Modifying
    @Query("update SysStationNotice set isRead =:isread,readDatetime=:readDatetime,readBy=:readBy where orderId=:orderId and notifyShopId =:shopId ")
    int updateByOrderId(@Param("isread") Boolean isread, @Param("orderId") Integer orderId, @Param("shopId") Integer shopId, @Param("readBy") Integer readBy, @Param("readDatetime") Date readDatetime);

    @Modifying
    @Query("update SysStationNotice set isRead =:isread,readDatetime=:readDatetime,readBy=:readBy where orderId=:orderId and notifyCustomerId =:customerId ")
    int updateByCustomerId(@Param("isread") Boolean isread, @Param("orderId") Integer orderId, @Param("customerId") Integer customerId, @Param("readBy") Integer readBy, @Param("readDatetime") Date readDatetime);
}
