package pril.oa_system.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.Depart;
import pril.oa_system.pojo.Post;
import pril.oa_system.pojo.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    void deleteById(Integer integer);

    List<User> findAll();

    List<User> findByDepart(Depart depart);
    
    List<User> findByPost(Post post);

    User findById(int id);

/*
    public Page<User> findByDetailid(int id, Pageable pageable);
*/
}

