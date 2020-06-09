package pril.oa_system.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="t_depart")
@Getter
@Setter
public class Depart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private Integer id;
    private String name;
    private String manager;
    private String tel;
    private String email;
}
