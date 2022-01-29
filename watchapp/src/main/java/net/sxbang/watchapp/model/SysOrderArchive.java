package net.sxbang.watchapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_order_archive")
@Entity
public class SysOrderArchive implements Serializable {
    private static final long serialVersionUID = -7102194439574609226L;

    @Id
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "positive_item_photo")
    private String positiveItemPhoto;
    @Column(name = "positive_item_desc")
    private String positiveItemDesc;
    @Column(name = "back_item_photo")
    private String backItemPhoto;
    @Column(name = "back_item_desc")
    private String backItemDesc;
    @Column(name = "side_item_photo")
    private String sideItemPhoto;
    @Column(name = "side_item_desc")
    private String sideItemDesc;
    @Column(name = "problem_photo")
    private String problemPhoto;
    @Column(name = "problem_desc")
    private String problemDesc;
    @Column(name = "item_photo")
    private String itemPhoto;
    @Column(name = "item_desc")
    private String itemDesc;
    @Column(name = "repair_photo")
    private String repairPhoto;
    @Column(name = "repair_desc")
    private String repairDesc;
    @DateTimeFormat(pattern = "yyyy-M-d HH:mm:ss")
    @Column(name = "create_datetime")
    private Date createDatetime;
    @Column(name = "create_by")
    private Integer createBy;


}
