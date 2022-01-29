package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.dao.custom.SysUserCustomDAO;
import net.sxbang.watchapp.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SysUserDAO extends JpaRepository<SysUser,Integer>, SysUserCustomDAO {

    /**
     * select * from sys_user where phone = :phone or union_id = :unionId
     * @param phone
     * @param unionId
     * @return
     */
    SysUser findByPhoneOrUnionId(String phone, String unionId);

    @Modifying
    @Query(" update SysUser su set su.lastLoginDatetime = :lastLoginDatetime where su.unionId = :username or su.phone = :username")
    void updateLastLoginDatetimeByUnionIdOrPhone(@Param("lastLoginDatetime") Date lastLoginDatetime, @Param("username") String username);

    @Modifying
    @Query(value = " insert into sys_role_user  values (:userId, :roleId)", nativeQuery = true)
    void saveUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
