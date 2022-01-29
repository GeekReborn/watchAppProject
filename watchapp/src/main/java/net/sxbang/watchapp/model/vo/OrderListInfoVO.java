package net.sxbang.watchapp.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListInfoVO implements Serializable {
    private Integer id;
    private String orderName;
    private String orderNo;
    private BigDecimal payPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDatetime;
    private String shopImage;
    private Integer orderStep;
    private String orderStepText;
    private Integer shopId;
}