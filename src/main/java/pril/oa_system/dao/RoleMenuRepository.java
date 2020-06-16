package pril.oa_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.RoleMenus;

import java.util.List;

public interface RoleMenuRepository extends JpaRepository<RoleMenus,Integer>{
        List<RoleMenus> findAllByRidOrderByMid(Integer rid);
}
