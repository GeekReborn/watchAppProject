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
public class OrderListInfoDTO implements Serializable {
    @Id
    private Integer id;
    private String orderName;
    private String orderNo;
    private BigDecimal payPrice;
    private Date createDatetime;
    private Integer orderStep;
    private String orderStepText;
    private String shopImage;
    private Integer shopId;
}