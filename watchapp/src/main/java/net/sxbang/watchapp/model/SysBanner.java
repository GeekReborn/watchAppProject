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
@Table(name = "sys_banner")
public class SysBanner implements Serializable {

    private static final long serialVersionUID = 684354820925042002L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "banner_url")
    private String bannerUrl;
    @Column(name = "seq")
    private Integer seq;
    @Column(name = "create_datetime")
    private Date createDatetime;
    @Column(name = "create_by")
    private Integer createBy;

}
