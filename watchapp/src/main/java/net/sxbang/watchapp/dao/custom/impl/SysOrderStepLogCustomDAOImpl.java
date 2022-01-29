package net.sxbang.watchapp.dao.custom.impl;

import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.dao.custom.SysOrderCustomDAO;
import net.sxbang.watchapp.dao.custom.SysOrderStepLogCustomDAO;
import net.sxbang.watchapp.model.dto.OrderListInfoDTO;
import net.sxbang.watchapp.model.dto.SysOrderStepLogDTO;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class SysOrderStepLogCustomDAOImpl implements SysOrderStepLogCustomDAO, Constants {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SysOrderStepLogDTO> findStepAndDictByOrderId(Integer orderId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select sosl.order_id,sosl.user_id,sosl.order_step, ");
        sql.append(" sosl.create_datetime,sd.dict_value as order_step_text ");
        sql.append(" from sys_order_step_log sosl ");
        sql.append(" left  JOIN sys_dict sd ON sd.dict_parent = 'order_status' ");
        sql.append("  AND sd.dict_key = sosl.order_step ");
        sql.append(" WHERE sosl.order_id = :orderId ");
        sql.append(" ORDER BY sosl.order_step desc ");
        Query query = entityManager.createNativeQuery(sql.toString(), SysOrderStepLogDTO.class)
                .setParameter("orderId", orderId);
        return query.getResultList();
    }
}
