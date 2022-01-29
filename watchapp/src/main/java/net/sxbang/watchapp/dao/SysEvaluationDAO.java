package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.dao.custom.SysEvaluationCustomDAO;
import net.sxbang.watchapp.dao.custom.SysShopCustomDAO;
import net.sxbang.watchapp.model.SysEvaluation;
import net.sxbang.watchapp.model.SysShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SysEvaluationDAO extends JpaRepository<SysEvaluation, Integer>, SysEvaluationCustomDAO {

    @Query(value = "SELECT  sum(not ISNULL(sea.id)) as `0` ,sum(sea.star >= 3) as `1` ,sum(sea.star < 3) as `2` " +
            ",sum( isnull( sea.repair_item_photo ) = 0 and LENGTH(trim(sea.repair_item_photo)) > 0) as `3`," +
            "sum(( isnull (sea.repair_item_photo)=1 or LENGTH( trim( sea.repair_item_photo )) = 0 )) as `4` " +
            "FROM sys_evaluation sea WHERE sea.shop_id = :shopId", nativeQuery = true)
    Map getEvaluationCount(@Param("shopId") Integer shopId);
}
