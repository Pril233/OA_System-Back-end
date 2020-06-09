package pril.oa_system.service;

import org.springframework.stereotype.Service;
import pril.oa_system.dao.PostRepository;
import pril.oa_system.pojo.Post;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostService {
    @Resource
    PostRepository postRepository;

    public List<Post> getPostList(){
        List<Post> posts = postRepository.findAll();
        return posts;
    }
}
