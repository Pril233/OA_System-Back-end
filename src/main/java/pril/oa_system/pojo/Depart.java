package pril.oa_system.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_depart")
@Getter
@Setter
public class Depart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private Integer id;
    private String name;
    private String manager;
    private String tel;
    private String email;

    public Depart(){

    }

    public Depart(int id){
        this.id = id;
    }
}
