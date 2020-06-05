package pril.oa_system.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_menu")
@Data
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String path;
    @Column(columnDefinition="INT default 0")
    private int queue;
    @Column(columnDefinition="INT default 0")
    private int parentId;

    @Transient
    private List<Menu> children;

}
