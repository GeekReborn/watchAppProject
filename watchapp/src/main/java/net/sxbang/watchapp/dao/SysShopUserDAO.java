package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.dao.custom.SysShopCustomDAO;
import net.sxbang.watchapp.model.SysShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @program: watchapp
 * @description:
 * @author: AndyZheng
 * @create: 2019-09-26 11:47
 **/
@Repository
public interface SysShopUserDAO extends JpaRepository<SysShopUser,Integer>, SysShopCustomDAO {

    SysShopUser findSysShopUserByUserId(Integer id);

    @Query(value = " select ssu " +
            " from SysShopUser ssu " +
            " left join SysUser su on su.id = ssu.userId " +
            " left join SysOrder so on so.shopId = ssu.shopId " +
            " where su.id = :userId and so.id = :orderId")
    SysShopUser findSysShopUserByUserIdAndOrderId(@Param("userId") Integer userId, @Param("orderId") Integer orderId);

    SysShopUser findByUserId(Integer userId);
}
