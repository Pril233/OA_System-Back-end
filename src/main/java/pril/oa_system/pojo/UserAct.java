package pril.oa_system.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "t_user_act")
@Getter
@Setter
public class UserAct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Fetch(FetchMode.JOIN)
    private Integer uid;
    private Integer aid;
    private String type;
}

