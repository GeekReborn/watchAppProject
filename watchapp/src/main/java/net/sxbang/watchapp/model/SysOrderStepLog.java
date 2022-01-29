package net.sxbang.watchapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sxbang.watchapp.model.SysOrderStepLogKey;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sys_order_step_log")
@IdClass(SysOrderStepLogKey.class)
public class SysOrderStepLog implements Serializable {

    private static final long serialVersionUID = -3267695323828215781L;

    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "order_step")
    private Integer orderStep;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_datetime")
    private Date createDatetime;



}
