package pril.oa_system.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.Anno;
import pril.oa_system.pojo.Depart;

import java.util.List;

public interface AnnoRepository extends JpaRepository<Anno,Integer> {
    public List<Anno> findTop5ByOrderByIdDesc();
}
