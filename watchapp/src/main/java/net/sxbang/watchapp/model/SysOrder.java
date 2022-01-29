package net.sxbang.watchapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_order")
public class SysOrder implements Serializable {

  private static final long serialVersionUID = 8359921325391047834L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "order_no")
  private String orderNo;

  @Column(name = "order_name")
  private String orderName;

  @Column(name = "pay_price")
  private BigDecimal payPrice;

  @Column(name = "order_step")
  private Integer orderStep;

  @Column(name = "before_repair_photo")
  private String beforeRepairPhoto;

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

  @Column(name = "problem_description")
  private String problemDescription;

  @Column(name = "customer_id")
  private Integer customerId;

  @Column(name = "master_id")
  private Integer masterId;

  @Column(name = "customer_phone")
  private String customerPhone;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "customer_signature")
  private String customerSignature;

  @Column(name = "is_agree_repair")
  private Boolean agreeRepair;

  @Column(name = "watch_body_no")
  private String watchBodyNo;

  @Column(name = "watch_serial_number")
  private String watchSerialNumber;

  @Column(name = "watch_brand_id")
  private Integer watchBrandId;

  @Column(name = "watch_material")
  private String watchMaterial;

  @Column(name = "watch_strap_number")
  private String watchStrapNumber;

  @Column(name = "reservation_datetime")
  private Date reservationDatetime;

  @Column(name = "create_datetime")
  private Date createDatetime;

  @Column(name = "del_status")
  private Boolean delStatus;

  @Column(name = "archive_status")
  private Boolean archiveStatus;

  @Column(name = "remarks")
  private String remarks;

  @Column(name = "watch_function")
  private Integer watchFunction;

  @Column(name = "watch_movement")
  private Integer watchMovement;

  @Column(name = "case_material")
  private Integer caseMaterial;

  @Column(name = "watchband_material")
  private Integer watchbandMaterial;

  @Column(name = "clasp_material")
  private Integer claspMaterial;

  @Column(name = "watch_exterior")
  private Integer watchExterior;

  @Column(name = "repair_parts")
  private Integer repairParts;

  @Column(name = "purchase_place")
  private String purchasePlace;

  @Column(name = "tablehandle_button")
  private String tablehandleButton;

  @Column(name = "is_insure")
  private Boolean insure;

  @Column(name = "entrusted_mail")
  private String entrustedMail;

  @Column(name = "watch_serie")
  private String watchSerie;

  @Column(name = "watch_model")
  private String watchModel;

  @Column(name = "purchase_date")
  private Date purchaseDate;

  @Column(name = "is_repair")
  private Boolean repair;

  @Column(name = "customer_profile")
  private String customerProfile;

  @Column(name = "create_by")
  private Integer createBy;

  @Column(name = "shop_id")
  private Integer shopId;

}
