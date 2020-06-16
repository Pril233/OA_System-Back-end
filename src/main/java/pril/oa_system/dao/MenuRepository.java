package pril.oa_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
    @Override
    List<Menu> findAll();
    List<Menu> findAllByParentId(int parentId);
    List<Menu> findAllByIdOrderByQueue(int id);

}
