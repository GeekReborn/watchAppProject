package net.sxbang.watchapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name="sys_shop")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysShop implements Serializable {

    private static final long serialVersionUID = -6002897100405934070L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "favorable_rate")
    private BigDecimal favorableRate;

    @Column(name = "location_text")
    private String locationText;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "shop_image")
    private String shopImage;

    @Column(name = "del_status")
    private Boolean delStatus;

    @Column(name = "create_by")
    private Integer createBy;

    @Column(name = "create_datetime")
    private Date createDatetime;

    @Column(name = "last_update_by")
    private Integer lastUpdateBy;

    @Column(name = "last_update_datetime")
    private Date lastUpdateDatetime;

}
