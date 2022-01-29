package net.sxbang.watchapp.dao.custom.impl;

import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.dao.custom.SysOrderCustomDAO;
import net.sxbang.watchapp.model.dto.OrderListInfoDTO;
import net.sxbang.watchapp.model.dto.ShopOrderDetailDTO;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SysOrderCustomDAOImpl implements SysOrderCustomDAO, Constants {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderListInfoDTO> findOrderListInfoByShopId(Integer shopId, String orderSteps, Date orderLastTimeText, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select so.id, so.order_name, so.order_no, so.pay_price, so.create_datetime, so.order_step, sd.dict_value as order_step_text, ss.shop_image, ss.id as shop_id ");
        sql.append(" from sys_order so ");
        sql.append(" left join sys_dict sd on sd.dict_key = so.order_step ");
        sql.append(" left join sys_shop ss on ss.id = so.shop_id ");
        sql.append(" where sd.dict_parent = 'ORDER_STATUS' ");
        sql.append(" and so.order_step in (" + orderSteps + ") ");
        sql.append(" and so.create_datetime < :orderLastTimeText ");
        sql.append(" and so.shop_id = :shopId ");
        sql.append(" order by so.create_datetime desc ");
        return entityManager.createNativeQuery(sql.toString(), OrderListInfoDTO.class)
                .setParameter("orderLastTimeText", orderLastTimeText)
                .setParameter("shopId", shopId)
                .setMaxResults(pageable.getPageSize())
                .setFirstResult((int) pageable.getOffset())
                .getResultList();
    }

    @Override
    public List<OrderListInfoDTO> findOrderListInfoByUserId(Integer userId, String orderSteps, Date orderLastTimeText, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select so.id, so.order_name, so.order_no, so.pay_price, so.create_datetime, so.order_step, sd.dict_value as order_step_text, ss.shop_image, ss.id as shop_id ");
        sql.append(" from sys_order so ");
        sql.append(" left join sys_dict sd on sd.dict_key = so.order_step ");
        sql.append(" left join sys_shop ss on ss.id = so.shop_id ");
        sql.append(" where sd.dict_parent = 'ORDER_STATUS' ");
        sql.append(" and so.order_step in (" + orderSteps + ") ");
        sql.append(" and so.create_datetime < :orderLastTimeText ");
        sql.append(" and so.customer_id = :userId ");
        sql.append(" order by so.create_datetime desc ");
        return entityManager.createNativeQuery(sql.toString(), OrderListInfoDTO.class)
                .setParameter("orderLastTimeText", orderLastTimeText)
                .setParameter("userId", userId)
                .setMaxResults(pageable.getPageSize())
                .setFirstResult((int) pageable.getOffset())
                .getResultList();
    }

    @Override
    public List<OrderListInfoDTO> searchOrderListInfoByShopId(Integer shopId, String searchValue, Date orderLastTimeText, Pageable pageable) {
        StringBuffer sql = new StringBuffer();
        sql.append("select   ");
        sql.append("so.id as id,concat(so.customer_name,' 维修单') as orderName,so.order_no as orderNo,so.pay_price as payPrice,  ");
        sql.append("so.create_datetime as createDatetime,so.order_step as orderStep,sd.dict_value as orderStepText,  ");
        sql.append("ss.shop_image as shopImage,ss.id as shopId   ");
        sql.append("from   ");
        sql.append("sys_order as so LEFT JOIN sys_shop as ss ON so.shop_id = ss.id ");
        sql.append("LEFT JOIN sys_shop_user as ssu ON ssu.shop_id = so.shop_id LEFT JOIN sys_user as su ON ssu.user_id = su.id  ");
        sql.append("LEFT JOIN sys_dict as sd ON so.order_step = sd.dict_key ");
        sql.append("where   ");
        sql.append("so.shop_id  = :shopId and sd.dict_parent = 'ORDER_STATUS' and   ");
        sql.append("so.create_datetime < :orderLastTimeText and ");
        sql.append("( so.customer_phone like :searchValue or su.phone like  :searchValue ) ");
        sql.append("order by so.create_datetime desc   ");
        sql.append("limit :pageSize");
        Query query = entityManager.createNativeQuery(sql.toString());
//        Integer currentPage = pageable.getPageNumber();
        Integer pageSize = pageable.getPageSize();
        List<OrderListInfoDTO> orderListInfoDTOList = query.unwrap(NativeQuery.class)
                .addScalar("id", IntegerType.INSTANCE)
                .addScalar("orderName", StringType.INSTANCE)
                .addScalar("orderNo", StringType.INSTANCE)
                .addScalar("payPrice", BigDecimalType.INSTANCE)
                .addScalar("createDatetime", TimestampType.INSTANCE)
                .addScalar("orderStep", IntegerType.INSTANCE)
                .addScalar("orderStepText", StringType.INSTANCE)
                .addScalar("shopImage", StringType.INSTANCE)
                .addScalar("shopId", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(OrderListInfoDTO.class))
                .setParameter("shopId",shopId)
                .setParameter("searchValue","%"+searchValue+"%")// like '%137%'
                .setParameter("orderLastTimeText",orderLastTimeText)
                .setParameter("pageSize",pageSize)
                .list();
        return orderListInfoDTOList;
    }

    @Override
    public List<OrderListInfoDTO> searchOrderListInfoByUserId(Integer userId, String searchValue, Date orderLastTimeText, Pageable pageable) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append("so.id as id,so.order_name as orderName,so.order_no as orderNo,so.pay_price as payPrice, ");
        sql.append("so.create_datetime as createDatetime,so.order_step as orderStep,sd.dict_value as orderStepText, ");
        sql.append("ss.shop_image as shopImage,ss.id as shopId  ");
        sql.append("from  ");
        sql.append("sys_order as so LEFT JOIN sys_shop as ss ON so.shop_id = ss.id ");
        sql.append("LEFT JOIN sys_user as su ON so.customer_id = su.id  ");
        sql.append("LEFT JOIN sys_dict as sd ON so.order_step = sd.dict_key ");
        sql.append("where  ");
        sql.append("so.customer_id = :userId and sd.dict_parent = 'ORDER_STATUS' and  ");
        sql.append("so.create_datetime < :orderLastTimeText and  ");
        sql.append("(so.customer_phone like :searchValue or su.phone like :searchValue) ");
        sql.append("order by so.create_datetime desc  ");
        sql.append("limit :pageSize ");

        Query query = entityManager.createNativeQuery(sql.toString());
//        Integer currentPage = pageable.getPageNumber();
        Integer pageSize = pageable.getPageSize();
        List<OrderListInfoDTO> orderListInfoDTOList = query.unwrap(NativeQuery.class)
                .addScalar("id", IntegerType.INSTANCE)
                .addScalar("orderName", StringType.INSTANCE)
                .addScalar("orderNo", StringType.INSTANCE)
                .addScalar("payPrice", BigDecimalType.INSTANCE)
                .addScalar("createDatetime", TimestampType.INSTANCE)
                .addScalar("orderStep", IntegerType.INSTANCE)
                .addScalar("orderStepText", StringType.INSTANCE)
                .addScalar("shopImage", StringType.INSTANCE)
                .addScalar("shopId", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(OrderListInfoDTO.class))
                .setParameter("userId",userId)
                .setParameter("searchValue","%"+searchValue+"%")
                .setParameter("orderLastTimeText",orderLastTimeText)
                .setParameter("pageSize",pageSize)
                .list();
        return orderListInfoDTOList;
    }

    @Override
    public List<String> loadYearMonthByUserId(Integer userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select concat(year(so.create_datetime), '-',  month(so.create_datetime)) as order_year_month ");
        sql.append(" from sys_order so ");
        sql.append(" left join sys_shop_user ssu on so.shop_id = ssu.shop_id ");
        sql.append(" where ssu.user_id = :userId ");
        sql.append(" and so.order_step in (8, 12) ");
        sql.append(" group by order_year_month ");
        sql.append(" order by order_year_month desc ");
        return entityManager.createNativeQuery(sql.toString())
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public BigDecimal loadTotalByYearMonth(Integer userId, int year, int month) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select sum(so.pay_price) as total ");
        sql.append(" from sys_order so ");
        sql.append(" left join sys_shop_user ssu on so.shop_id = ssu.shop_id ");
        sql.append(" where ssu.user_id = :userId ");
        sql.append(" and so.order_step in (8, 12) ");
        sql.append(" and year(so.create_datetime) = :year ");
        sql.append(" and month(so.create_datetime) = :month ");
        sql.append(" group by year(so.create_datetime), month(so.create_datetime) ");
        return (BigDecimal) entityManager.createNativeQuery(sql.toString())
                .setParameter("userId", userId)
                .setParameter("year", year)
                .setParameter("month", month)
                .getSingleResult();

    }

    @Override
    public List<ShopOrderDetailDTO> loadOrderDetailByYearMonth(Integer userId, int year, int month) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select concat(year(so.create_datetime), '-',  month(so.create_datetime)) as order_year_month,  ");
        sql.append(" 	so.customer_name as username, so.pay_price, su.avatar_url, so.id as order_id,  ");
        sql.append(" 	so.shop_id, so.create_datetime ");
        sql.append(" from sys_order so ");
        sql.append(" left join sys_user su on su.id = so.customer_id ");
        sql.append(" left join sys_shop_user ssu on so.shop_id = ssu.shop_id ");
        sql.append(" where ssu.user_id = :userId ");
        sql.append(" and so.order_step in (8, 12) ");
        sql.append(" and year(so.create_datetime) = :year ");
        sql.append(" and month(so.create_datetime) = :month ");
        sql.append(" order by so.create_datetime desc ");
        return entityManager.createNativeQuery(sql.toString(), ShopOrderDetailDTO.class)
                .setParameter("userId", userId)
                .setParameter("year", year)
                .setParameter("month", month)
                .getResultList();
    }
}
