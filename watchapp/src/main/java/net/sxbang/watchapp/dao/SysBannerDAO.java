package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.model.SysBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysBannerDAO extends JpaRepository<SysBanner,Integer> {

    // select * from sys_banner order by seq
    List<SysBanner> findByOrderBySeq();
}
