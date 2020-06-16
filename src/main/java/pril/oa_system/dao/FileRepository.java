package pril.oa_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.FileInfo;

public interface FileRepository extends JpaRepository<FileInfo,Integer> {

}
