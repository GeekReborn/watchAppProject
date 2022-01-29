package net.sxbang.watchapp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalAuthTokenVO extends SysUserVO {

    private static final long serialVersionUID = -9102753094751281341L;

    private String accessToken;

    private String tokenType;

    private String refreshToken;

    private Integer expiresIn;

    private String scope;
}
