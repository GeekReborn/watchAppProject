package net.sxbang.watchapp.dao.custom;

import net.sxbang.watchapp.model.dto.SysEvaluationDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SysEvaluationCustomDAO {
    List<SysEvaluationDTO> findList(Integer shopId, Integer filter, Pageable pageable);

    long countList(Integer shopId, Integer filter);

}
