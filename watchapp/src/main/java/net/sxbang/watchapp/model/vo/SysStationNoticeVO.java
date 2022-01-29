package net.sxbang.watchapp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sxbang.watchapp.model.SysStationNoticeKey;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysStationNoticeVO {
    private Integer orderId;
    private Integer orderStep;
    private Boolean isRead;
    private Integer notifyShopId;
    private Integer notifyCustomerId;
    private String message;
    private Date createdDatetime;
    private Integer createdBy;
    private Date readDatetime;
    private Integer readBy;

}
