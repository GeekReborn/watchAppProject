package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.dao.custom.SysOrderCustomDAO;
import net.sxbang.watchapp.model.SysOrder;
import net.sxbang.watchapp.model.dto.OrderListInfoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @program: watchapp
 * @description:
 * @author: StevenWang
 * @create: 2019-10-10 16:33
 **/
@Repository
public interface SysOrderDAO extends JpaRepository<SysOrder, Integer>, SysOrderCustomDAO {


}
