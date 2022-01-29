package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.dao.custom.SysShopCustomDAO;
import net.sxbang.watchapp.model.SysBanner;
import net.sxbang.watchapp.model.SysShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysShopDAO extends JpaRepository<SysShop, Integer>, SysShopCustomDAO {

}
