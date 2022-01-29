package net.sxbang.watchapp.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SysEvaluationDTO implements Serializable {

    private static final long serialVersionUID = 3576056872614443661L;
    @Id
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
