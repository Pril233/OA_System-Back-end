package pril.oa_system.Web.Controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pril.oa_system.pojo.Post;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.PostService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class PostController {
    @Resource
    PostService postService;

    @GetMapping("/posts")
    public Result getPostList(){
        List<Post> posts = postService.getPostList();
        return ResultFactory.buildSuccessResult(posts,"获取职位列表成功");
    }

    @GetMapping("/Page4Post")
    public Result getPage4Post(int pagenum,int pagesize){
        Page<Post> page4Post = postService.getPostPage(pagenum-1,pagesize);
        List<Post> posts = page4Post.getContent();
        HashMap<String,Object> data = new HashMap<>();
        data.put("posts",posts);
        data.put("total",page4Post.getTotalElements());

        return ResultFactory.buildSuccessResult(data,"获取职位分页信息成功");

    }

    @PostMapping("/addPost")
    public Result addPost(@RequestBody Post post){
        postService.addPost(post);
        return ResultFactory.buildSuccessResult(null,"添加职位成功");
    }

    @PutMapping("/posts")
    public Result editPost(@RequestBody Post post){
        postService.editPost(post);
        return ResultFactory.buildSuccessResult(null,"修改职位成功");
    }

    @DeleteMapping("/posts/{id}")
    public Result deletePostById(@PathVariable("id") int id){
        postService.deletePostById(id);
        return ResultFactory.buildSuccessResult(null,"删除职位成功");
    }
}
