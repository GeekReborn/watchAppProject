package net.sxbang.watchapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysShopUserKey implements Serializable {

    private Integer shopId;

    private Integer userId;
}
