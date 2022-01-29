package net.sxbang.watchapp.dao.custom.impl;

import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.dao.custom.SysEvaluationCustomDAO;
import net.sxbang.watchapp.model.dto.SysEvaluationDTO;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class SysEvaluationCustomDAOImpl implements SysEvaluationCustomDAO, Constants {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SysEvaluationDTO> findList(Integer shopId, Integer filter, Pageable pageable) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT sea.*,su.avatar_url,su.username,su.id ");
        sql.append(" FROM sys_evaluation sea ");
        sql.append(" LEFT JOIN sys_order so ON so.id = sea.order_id ");
        sql.append(" LEFT JOIN sys_user su ON su.id = so.customer_id ");
        sql.append(" WHERE sea.shop_id = :shopId ");
        switch (filter){
            case 1:
                sql.append(" and sea.star >= 3 ");
                break;
            case 2:
                sql.append(" and sea.star < 3 ");
                break;
            case 3:
//                sql.append(" and isnull(sea.repair_item_photo) <= 0 and LENGTH(trim(sea.repair_item_photo)) = 0 ");
                sql.append("and isnull( sea.repair_item_photo ) = 0 and LENGTH(trim(sea.repair_item_photo)) > 0 ");
                break;
            case 4:
//                sql.append(" and isnull(sea.repair_item_photo) > 0 ");
                sql.append("and ( isnull (sea.repair_item_photo)=1 or LENGTH( trim( sea.repair_item_photo )) = 0 )");
                break;
        }
        sql.append(" ORDER BY sea.create_datetime desc ");
        Query query = entityManager.createNativeQuery(sql.toString(), SysEvaluationDTO.class)
                .setParameter("shopId", shopId)
                .setFirstResult((int) pageable.getOffset()) // offset = currentPage * pageSize
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }


    @Override
    public long countList(Integer shopId, Integer filter) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT  count(*) ");
        sql.append(" FROM sys_evaluation sea ");
        sql.append(" WHERE sea.shop_id = :shopId ");
        switch (filter){
            case 1:
                sql.append(" and sea.star >= 3 ");
                break;
            case 2:
                sql.append(" and sea.star < 3 ");
                break;
            case 3:
//                sql.append(" and isnull(sea.repair_item_photo) <= 0  ");
                sql.append(" and isnull( sea.repair_item_photo ) = 0 and LENGTH(trim(sea.repair_item_photo)) > 0 ");
                break;
            case 4:
//                sql.append(" and isnull(sea.repair_item_photo) > 0 ");
                sql.append(" and ( isnull (sea.repair_item_photo)=1 or LENGTH( trim( sea.repair_item_photo )) = 0 )");
                break;
        }
        Query query = entityManager.createNativeQuery(sql.toString())
                .setParameter("shopId", shopId);
        return ((BigInteger) query.getSingleResult()).longValue();
    }

//    @Override
//    public Map getCountData(Integer shopId) {
//        StringBuffer sql = new StringBuffer();
//        sql.append(" SELECT   ");
//        sql.append(" sum(not ISNULL(sea.id)) as alldata ");
//        sql.append(" ,sum(sea.star >=3) as good ");
//        sql.append(" ,sum(sea.star <3) as bad ");
//        sql.append(" ,sum(ISNULL(sea.repair_item_photo)) as hasPhoto ");
//        sql.append(" ,sum(not ISNULL(sea.repair_item_photo)) as notPhoto ");
//        sql.append(" FROM sys_evaluation sea ");
//        sql.append(" WHERE sea.shop_id = :shopId ");
//        Query query = entityManager.createNativeQuery(sql.toString(),java.util.Map.class)
//                .setParameter("shopId", shopId);
////                .unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        return (Map)query.getSingleResult();
//    }

}
