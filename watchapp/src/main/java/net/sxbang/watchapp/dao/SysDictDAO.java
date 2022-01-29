package net.sxbang.watchapp.dao;

import net.sxbang.watchapp.dao.custom.SysOrderCustomDAO;
import net.sxbang.watchapp.model.SysDict;
import net.sxbang.watchapp.model.SysOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SysDictDAO extends JpaRepository<SysDict, Integer> {

    List<SysDict> findByDictParent(String dictParent);

    List<SysDict> findByDictParentIn(Collection<String> dictParent);

    SysDict findByDictParentAndDictKey (String dictParent, String dictKey);
}
