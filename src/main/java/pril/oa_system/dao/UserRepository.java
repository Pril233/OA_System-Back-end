package pril.oa_system.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    void deleteById(Integer integer);

    List<User> findAll();
}

