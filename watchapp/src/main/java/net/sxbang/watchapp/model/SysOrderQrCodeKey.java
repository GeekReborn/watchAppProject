package net.sxbang.watchapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysOrderQrCodeKey implements Serializable {

    private static final long serialVersionUID = 3358448702384052368L;

    private Integer orderId;
    private Integer scene;

}
