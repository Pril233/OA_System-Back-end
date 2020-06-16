package pril.oa_system.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="t_file")
@Getter
@Setter
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
