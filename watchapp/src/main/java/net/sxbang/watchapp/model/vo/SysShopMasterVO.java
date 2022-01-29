package net.sxbang.watchapp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysShopMasterVO implements Serializable {

    Integer userId;

    Integer shopId;

    String username;

    String phone;

    String avatarUrl;

}
