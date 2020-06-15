package pril.oa_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pril.oa_system.dao.PostRepository;
import pril.oa_system.dao.UserRepository;
import pril.oa_system.pojo.Post;
import pril.oa_system.pojo.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostService {
    @Resource
    PostRepository postRepository;

    @Resource
    UserRepository userRepository;

    public List<Post> getPostList(){
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    public Page<Post> getPostPage(int pagenum,int pagesize){
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = new PageRequest(pagenum,pagesize,sort);
        Page<Post> page4Post = postRepository.findAll(pageable);
        return page4Post;
    }

    public Post addPost(Post post){
        return postRepository.save(post);
    }

    public Post editPost(Post post){
        return postRepository.save(post);
    }

    public void deletePostById(int id){
        Post defaultPost = new Post(0);
        Post post = postRepository.findOne(id);
        List<User> usersFindByPost = userRepository.findByPost(post);
        for(User user:usersFindByPost){
            user.setPost(defaultPost);
            userRepository.save(user);
        }
        postRepository.delete(id);
    }
}
