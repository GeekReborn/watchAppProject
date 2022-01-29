package net.sxbang.watchapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sys_shop_user")
@IdClass(SysShopUserKey.class)
public class SysShopUser implements Serializable {

    private static final long serialVersionUID = 9114545858911789746L;

    @Id
    @Column(name = "shop_id")
    private Integer shopId;

    @Id
    @Column(name = "user_id")
    private Integer userId;
}
