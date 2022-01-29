package net.sxbang.watchapp.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterRepairOrderInfo {
    private Integer id;
    private String customerName;
    private String customerPhone;
    private String orderNo;
    private String entrustedMail;
    private Boolean insure;
    private Integer watchBrandId;
    private String watchSerie;
    private String watchModel;
//    @JsonFormat(pattern = "yyyy-MM")
//    @JsonFormat(pattern = "yyyy-M-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-M-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    private Date purchaseDate;
    private Boolean repair;
    private String purchasePlace;
    private Integer watchFunction;
    private Integer watchMovement;
    private Integer caseMaterial;
    private Integer watchbandMaterial;
    private Integer claspMaterial;
    private Integer watchExterior;
    private Integer repairParts;
    private String tablehandleButton;
    //    private String customerProfile;
    private String positiveItemPhoto;
    private String positiveItemDesc;
    private String backItemPhoto;
    private String backItemDesc;
    private String sideItemPhoto;
    private String sideItemDesc;
    private String problemPhoto;
    private String problemDescription;
    private BigDecimal payPrice;


//    public Date getPurchaseDate() {
////        if (StringUtils.isEmpty(purchaseDate)) {
////            return null;
////        }
////        return (Date) purchaseDate.clone();
//        return purchaseDate;
//    }
//
//    public void setPurchaseDate(Date purchaseDate) {
////        if (!StringUtils.isEmpty(purchaseDate)) {
////            this.purchaseDate = (Date) purchaseDate.clone();
////        }
//    }


}
