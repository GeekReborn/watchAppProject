package net.sxbang.watchapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysOrderDTO implements Serializable {

  private static final long serialVersionUID = 8359921325391047834L;
  @Id
  private Integer id;
  private String orderNo;
  private String orderName;
  private BigDecimal payPrice;
  private Integer orderStep;
  private String beforeRepairPhoto;
  private String positiveItemPhoto;
  private String positiveItemDesc;
  private String backItemPhoto;
  private String backItemDesc;
  private String sideItemPhoto;
  private String sideItemDesc;
  private String problemPhoto;
  private String problemDescription;
  private Integer customerId;
  private Integer masterId;
  private String customerPhone;
  private String customerName;
  private String customerSignature;
  @Column(name = "is_agree_repair")
  private Boolean agreeRepair;
  private String watchBodyNo;
  private String watchSerialNumber;
  private Integer watchBrandId;
  private String watchMaterial;
  private String watchStrapNumber;
  private Date reservationDatetime;
  private Date createDatetime;
  private Boolean delStatus;
  private Boolean archiveStatus;
  private String remarks;
  private Integer watchFunction;
  private Integer watchMovement;
  private Integer caseMaterial;
  private Integer watchbandMaterial;
  private Integer claspMaterial;
  private Integer watchExterior;
  private Integer repairParts;
  private String purchasePlace;
  private String tablehandleButton;
  @Column(name = "is_insure")
  private Boolean insure;
  private String entrustedMail;
  private String watchSerie;
  private String watchModel;
  private Date purchaseDate;
  @Column(name = "is_repair")
  private Boolean repair;
  private String customerProfile;
  private Integer createBy;
  private Integer shopId;
  @Column(name = "shop_name")
  private String shopName;
  @Column(name = "master_name")
  private String masterName;

}
