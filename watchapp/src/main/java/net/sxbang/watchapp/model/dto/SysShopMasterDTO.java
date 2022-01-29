package net.sxbang.watchapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysShopMasterDTO implements Serializable {

    @Id
    Integer userId;

    Integer shopId;

    String username;

    String phone;

    String avatarUrl;

}
