package net.sxbang.watchapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ShopOrderDetailDTO {

    @Id
    private Integer orderId;

    private String orderYearMonth;

    private String username;

    private String avatarUrl;

    private BigDecimal payPrice;

    private Integer shopId;

    private Date createDatetime;
}
