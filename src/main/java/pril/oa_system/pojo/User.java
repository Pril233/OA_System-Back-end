package pril.oa_system.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;



import javax.persistence.Entity;

@Entity
@Table(name = "t_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String mg_state;
    private String name;
    private String email;
    private String tel;

    @ManyToOne()
    @JoinColumn(name="rid")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Role role;
}
