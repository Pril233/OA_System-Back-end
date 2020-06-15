package pril.oa_system.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_post")
@Getter
@Setter
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private Integer id;
    private String code;
    private String name;

    public Post(){

    }

    public Post(int id){
        this.id = id;
    }
}
