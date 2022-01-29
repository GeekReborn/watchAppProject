package net.sxbang.watchapp.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysShopVO implements Serializable {

    private static final long serialVersionUID = 5114586642513878142L;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDatetime;
    private Integer lastUpdateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDatetime;
    private Integer shopManagerId;
    private String shopManagerName;
    private Integer distance;
    private String shopContact;
    private Integer orderCount;

}
