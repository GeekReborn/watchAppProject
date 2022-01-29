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
public class SysOrderVO implements Serializable {

  private static final long serialVersionUID = 8359921325391047834L;
  private Integer id;
  private String orderNo;
  private String orderName;
  private BigDecimal payPrice;
  private Integer orderStep;
  private String orderStepText;
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
  private Boolean agreeRepair;
  private String watchBodyNo;
  private String watchSerialNumber;
  private Integer watchBrandId;
  private String watchMaterial;
  private String watchStrapNumber;
  @JsonFormat(pattern="yyyy-M-d HH:mm:ss",timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-M-d HH:mm:ss")
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
  private Boolean insure;
  private String entrustedMail;
  private String watchSerie;
  private String watchModel;
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date purchaseDate;
  private Boolean repair;
  private String customerProfile;
  private Integer createBy;
  private Integer shopId;

  private String orderQrCode;
  private Integer orderQrCodeScene;


}
