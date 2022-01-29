package net.sxbang.watchapp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserVO implements Serializable {

    private static final long serialVersionUID = -1115278177089703886L;

    private Integer id;

    private String username;

    private Date birthday;

    private short sex;

    private String password;

    private String phone;

    private Boolean status;

    private String unionId;

    private String city;

    private String avatarUrl;

    private Date lastLoginDatetime;

    private Date registrationDatetime;

    private Boolean delStatus;

    private List<SysUserRoleVO> roles;

}
