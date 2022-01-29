package net.sxbang.watchapp.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysEvaluationVO implements Serializable {

    private static final long serialVersionUID = 3576056872614443661L;

    private Integer id;
    private Integer orderId;
    private Integer star;
    private String comment;
    private String repairItemPhoto;
    private Date createDatetime;
    private Integer createBy;
    private String avatarUrl;
    private String username;
    private Integer shopId;

}
