package net.sxbang.watchapp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysBannerVO implements Serializable {

  private static final long serialVersionUID = 5103647349522011919L;
  private Integer id;
  private String name;
  private String bannerUrl;
  private Integer seq;
  private Date createDatetime;
  private Integer createBy;

}
