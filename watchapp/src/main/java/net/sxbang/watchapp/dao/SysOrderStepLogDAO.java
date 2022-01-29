package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.dao.custom.SysOrderCustomDAO;
import net.sxbang.watchapp.dao.custom.SysOrderStepLogCustomDAO;
import net.sxbang.watchapp.model.SysOrder;
import net.sxbang.watchapp.model.SysOrderStepLog;
import net.sxbang.watchapp.model.SysOrderStepLogKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: watchapp
 * @description:
 * @author: StevenWang
 * @create: 2019-10-10 16:33
 **/
@Repository
public interface SysOrderStepLogDAO extends JpaRepository<SysOrderStepLog, SysOrderStepLogKey>, SysOrderStepLogCustomDAO {
    List<SysOrderStepLog> findByOrderIdOrderByOrderStepDesc(Integer orderId);
}
