package pril.oa_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.Plan;
import pril.oa_system.pojo.User;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Integer> {
    public List<Plan> findByUserOrderByIdDesc(User user);
}
