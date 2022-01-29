package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.model.SysOrderArchive;
import net.sxbang.watchapp.model.SysOrderQrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysOrderArchiveDAO extends JpaRepository<SysOrderArchive, Integer> {

}
