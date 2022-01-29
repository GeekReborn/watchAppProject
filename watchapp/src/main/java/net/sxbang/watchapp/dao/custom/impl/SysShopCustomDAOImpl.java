package net.sxbang.watchapp.dao.custom.impl;

import net.sxbang.watchapp.common.constant.Constants;
import net.sxbang.watchapp.dao.custom.SysShopCustomDAO;
import net.sxbang.watchapp.model.SysShopUser;
import net.sxbang.watchapp.model.dto.SysShopDTO;
import net.sxbang.watchapp.model.dto.SysShopMasterDTO;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SysShopCustomDAOImpl implements SysShopCustomDAO, Constants {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SysShopDTO> findListOrder(String order, BigDecimal latitude, BigDecimal longitude, Pageable pageable) {
        StringBuffer sql = new StringBuffer();
        sql.append("    select ss.id,ss.name, ss.location_text,ss.info, ss.favorable_rate, ss.shop_image,   ");
        sql.append("          ss.latitude,ss.longitude, ss.create_by, ss.create_datetime, ss.last_update_by, ");
        sql.append("          ss.last_update_datetime, ss.del_status,   ");
        sql.append("          round(6378.138 * 2 * asin( ");
        sql.append(" 			sqrt( ");
        sql.append(" 				pow(sin((:latitude * pi() / 180 - ss.latitude * pi()/180) / 2), 2) ");
        sql.append(" 				+ cos(:latitude * pi() / 180 ) ");
        sql.append(" 		        	* cos(ss.latitude*pi() / 180) ");
        sql.append(" 		       		* pow(sin( (:longitude * pi() / 180 - ss.longitude * pi() / 180) / 2), 2) ");
        sql.append(" 		    )     ");
        sql.append(" 		) * 1000) as distance, ");
        sql.append(" 		su.id AS shop_manager_id, su.username AS shop_manager_name,su.phone AS shop_contact    ");
        sql.append("     from sys_shop ss    ");
        sql.append("     LEFT JOIN sys_shop_user ssu ON ssu.shop_id = ss.id    ");
        sql.append("     LEFT JOIN sys_user su ON su.id = ssu.user_id    ");
        sql.append("     LEFT JOIN sys_role_user sru ON su.id = sru.user_id   ");
        sql.append("     AND sru.role_id = 4    ");
        sql.append("     where IFNULL(ss.del_status,1) = 0      ");
        sql.append("     AND (sru.role_id = 4 AND su.id is not null)  ");

        if (SHOP_ORDER_DISTANCE.equals(order)) {
            sql.append(" ORDER BY distance, ss.id ");
        } else {
            sql.append(" ORDER BY favorable_rate desc, ss.id " );
        }
        Query query = entityManager.createNativeQuery(sql.toString(), SysShopDTO.class)
                .setParameter("longitude", longitude)
                .setParameter("latitude", latitude)
                .setFirstResult((int) pageable.getOffset()) // offset = currentPage * pageSize
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }
    @Override
    public long countListOrder() {
        StringBuffer sql = new StringBuffer();
        sql.append("    select count(*)    ");
        sql.append("     from sys_shop ss    ");
        sql.append("     LEFT JOIN sys_shop_user ssu ON ssu.shop_id = ss.id    ");
        sql.append("     LEFT JOIN sys_user su ON su.id = ssu.user_id    ");
        sql.append("     LEFT JOIN sys_role_user sru ON su.id = sru.user_id   ");
        sql.append("     AND sru.role_id = 4    ");
        sql.append("     where IFNULL(ss.del_status,1) = 0      ");
        sql.append("     AND (sru.role_id = 4 AND su.id is not null)  ");

        Query query = entityManager.createNativeQuery(sql.toString());
        return ((BigInteger) query.getSingleResult()).longValue();
    }

    @Override
    public SysShopDTO findShopInfoAndContactById(Integer shopId) {
        StringBuffer sql = new StringBuffer();
        sql.append("    select ss.id,ss.name, ss.location_text,ss.info, ss.favorable_rate, ss.shop_image,   ");
        sql.append("          ss.latitude,ss.longitude, ss.create_by, ss.create_datetime, ss.last_update_by, ");
        sql.append("          ss.last_update_datetime, ss.del_status,   ");
        sql.append("     0 as distance, su.id AS shop_manager_id, su.username AS shop_manager_name, su.phone AS shop_contact    ");
        sql.append("     from sys_shop ss    ");
        sql.append("     LEFT JOIN sys_shop_user ssu ON ssu.shop_id = ss.id    ");
        sql.append("     LEFT JOIN sys_user su ON su.id = ssu.user_id    ");
        sql.append("     LEFT JOIN sys_role_user sru ON su.id = sru.user_id   ");
        sql.append("     AND sru.role_id = 4    ");
        sql.append("     where ss.id = :shopId and IFNULL(ss.del_status,1) = 0      ");
        sql.append("     AND (sru.role_id = 4 AND su.id is not null)  ");
        Query query = entityManager.createNativeQuery(sql.toString(), SysShopDTO.class).setParameter("shopId", shopId);
        return (SysShopDTO)query.getSingleResult();
    }

    @Override
    public List<SysShopMasterDTO> listShopMaster(Integer userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ssu.shop_id, ssu.user_id, su.username, su.phone, su.avatar_url ");
        sql.append(" from sys_shop_user ssu ");
        sql.append(" left join sys_user su on su.id = ssu.user_id ");
        sql.append(" left join sys_role_user sru on sru.user_id = ssu.user_id ");
        sql.append(" where sru.role_id = 2 ");
        sql.append(" and ssu.shop_id in ( ");
        sql.append(" 	select shop_id from sys_shop_user where user_id = :userId ");
        sql.append(" ) ");
        sql.append(" and ssu.user_id <> :userId ");
        return entityManager.createNativeQuery(sql.toString(), SysShopMasterDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
