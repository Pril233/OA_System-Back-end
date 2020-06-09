package pril.oa_system.Web.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pril.oa_system.pojo.Post;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.PostService;

import javax.annotation.Resource;
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
}
