package pril.oa_system.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "s_role_right")
@Getter
@Setter
public class RoleRights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Fetch(FetchMode.JOIN)
    private Integer roleId;
    private Integer rightId;
}
