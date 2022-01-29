package net.sxbang.watchapp.dao.custom.impl;

import net.sxbang.watchapp.dao.custom.SysUserCustomDAO;
import net.sxbang.watchapp.model.dto.SysUserRoleDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SysUserCustomDAOImpl implements SysUserCustomDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SysUserRoleDTO> findUserRoleByUser(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select sr.id as id, sr.name as name  ");
        sql.append(" from sys_role sr  ");
        sql.append(" right join sys_role_user sru on sru.role_id = sr.id  ");
        sql.append(" where sru.user_id = :userId  ");
        return entityManager.createNativeQuery(sql.toString(), SysUserRoleDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

}
