package pril.oa_system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="t_file")
@Getter
@Setter
public class FileInfo {
    private static final String DDFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE = "GMT+8";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern=DDFormat, timezone = TIME_ZONE)

    private Timestamp creationdata;
    String filename;
    String founder;
    String filesize;

    FileInfo(){

    }

    public FileInfo(String filename,String founder,String filesize){
        this.creationdata = new Timestamp(System.currentTimeMillis());
        this.filename = filename;
        this.founder = founder;
        this.filesize = filesize;
    }

}
