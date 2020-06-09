package pril.oa_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.Post;


public interface PostRepository extends JpaRepository<Post,Integer> {
}
