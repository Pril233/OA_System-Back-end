package pril.oa_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.Activity;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    public List<Activity> findAllByTypeOrderByCreationdataDesc(String type);
}
