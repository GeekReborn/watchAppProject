package net.sxbang.watchapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sys_dict")
public class SysDict implements Serializable {


    private static final long serialVersionUID = -2822908104923658826L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dict_key")
    private String dictKey;
    @Column(name = "dict_value")
    private String dictValue;
    @Column(name = "dict_parent")
    private String dictParent;
    @Column(name = "seq")
    private Integer seq;
    @Column(name = "parent_id")
    private Integer parentId;


}
