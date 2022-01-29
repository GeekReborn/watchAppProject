package net.sxbang.watchapp.dao.custom;

import net.sxbang.watchapp.model.SysOrderStepLog;
import net.sxbang.watchapp.model.dto.OrderListInfoDTO;
import net.sxbang.watchapp.model.dto.SysOrderStepLogDTO;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface SysOrderStepLogCustomDAO {

    List<SysOrderStepLogDTO> findStepAndDictByOrderId(Integer orderId);
}
