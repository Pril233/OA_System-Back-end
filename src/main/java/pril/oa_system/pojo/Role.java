package pril.oa_system.pojo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="t_role")
@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name="d_esc")
    private String desc;

    @Transient
    private List<Rights> rights;
}
