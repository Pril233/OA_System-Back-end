package pril.oa_system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="t_activity")
@Getter
@Setter
public class Activity {
    private static final String DDFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE = "GMT+8";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    @JsonFormat(pattern=DDFormat, timezone = TIME_ZONE)
    private Timestamp creationdata;
    private String type;
    private String founder;

    private Activity(){

    }

    public Activity(String title,String content,String type,String founder){
        this.title = title;
        this.content = content;
        this.type = type;
        this.founder = founder;
        this.creationdata = new Timestamp(System.currentTimeMillis());
    }
}
