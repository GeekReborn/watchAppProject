package net.sxbang.watchapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysShopDTO implements Serializable {

    private static final long serialVersionUID = 5114586642513878142L;

    @Id
    private Integer id;
    private String name;
    private String info;
    private BigDecimal favorableRate;
    private String locationText;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String shopImage;
    private boolean delStatus;
    private Integer createBy;
    private Date createDatetime;
    private Integer lastUpdateBy;
    private Date lastUpdateDatetime;
    private Integer shopManagerId;
    private String shopManagerName;
    private String shopContact;
    private Integer distance;

}
