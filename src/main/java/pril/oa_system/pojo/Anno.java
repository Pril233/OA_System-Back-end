package pril.oa_system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;


import javax.persistence.*;

@Entity
@Table(name="t_anno")
@Getter
@Setter
public class Anno {
    private static final String DDFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE = "GMT+8";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private Integer id;
    private String name;
    private String title;
    private String context;
    @JsonFormat(pattern=DDFormat, timezone = TIME_ZONE)
    private Timestamp data;

    public Anno(){

    }

    public Anno(String title,String context,String name){
        this.title = title;
        this.context = context;
        this.name = name;
        this.data = new Timestamp(System.currentTimeMillis());
    }
}
