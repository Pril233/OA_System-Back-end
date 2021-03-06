package pril.oa_system.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;



import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String name;
    private String email;
    private String tel;

    private static final long serialVersionUID = 1L;

    @ManyToOne()
    @JoinColumn(name="rid")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Role role;

    @ManyToOne()
    @JoinColumn(name="pid")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Post post;

    @ManyToOne()
    @JoinColumn(name="did")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Depart depart;


}
