package net.sxbang.watchapp.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Emoker
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictNode {

    private String dictKey;
    private String dictValue;
    private Map<String, DictNode> childNode;
}
