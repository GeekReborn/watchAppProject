package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.model.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: watchapp
 * @description:
 * @author: AndyZheng
 * @create: 2019-09-26 11:47
 **/
@Repository
public interface SysRoleUserDAO extends JpaRepository<SysRoleUser,Integer> {

}
