package net.sxbang.watchapp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WechatAuthTokenVO implements Serializable {

    private static final long serialVersionUID = -3825896726347371570L;

    private String openid;

    private String sessionKey;

    private Integer expiresIn;
}
