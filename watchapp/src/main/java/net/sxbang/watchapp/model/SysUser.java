package net.sxbang.watchapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = -1764639835601863587L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "sex")
    private short sex;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "union_id",unique=true)
    private String unionId;

    @Column(name = "city")
    private String city;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "last_login_datetime")
    private Date lastLoginDatetime;

    @Column(name = "registration_datetime")
    private Date registrationDatetime;

    @Column(name = "del_status")
    private Boolean delStatus;

}
