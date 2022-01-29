package net.sxbang.watchapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sys_station_notice")
@IdClass(SysStationNoticeKey.class)
public class SysStationNotice {
    @Id
    @Column(name = "order_id")
    private Integer orderId;
    @Id
    @Column(name = "order_step")
    private Integer orderStep;
    @Column(name = "is_read")
    private Boolean isRead;
    @Column(name = "notify_shop_id")
    private Integer notifyShopId;
    @Column(name = "notify_customer_id")
    private Integer notifyCustomerId;
    @Column(name = "message")
    private String message;
    @Column(name = "created_datetime")
    private Date createdDatetime;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "read_datetime")
    private Date readDatetime;
    @Column(name = "read_by")
    private Integer readBy;

}
