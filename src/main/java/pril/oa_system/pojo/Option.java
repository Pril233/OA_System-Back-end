package pril.oa_system.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name="t_option")
@Getter
@Setter
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String count;

    @ManyToOne()
    @JoinColumn(name="aid")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Activity activity;

    private Option(){

    }

    public Option(String name,Activity activity){
        this.name = name;
        this.count = 0+"";
        this.activity = activity;
    }
}
