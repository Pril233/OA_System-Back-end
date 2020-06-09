package pril.oa_system.Web.Controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pril.oa_system.pojo.User;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    @Resource
    UserService userServiceImpl;

  /*  @GetMapping("/users")
    public Result getUsers() {
     //后端实现分页查询
        //userServiceImpl.getUserListByPage(pagenum,pagesize);
        List users = userServiceImpl.getUserList();
        return ResultFactory.buildSuccessResult(users,"查询所有用户");
    }*/

    @GetMapping("/users")
    public Result getUsersByPage(String query,int pagenum,int pagesize){
        Page<User> page = userServiceImpl.getPageUsers(pagenum-1,pagesize);
        List<User> users = page.getContent();
        int totalpage = page.getTotalPages();
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",page.getTotalPages());
        data.put("pagenum",pagenum);
        data.put("users",users);
        System.out.println(data);
        return ResultFactory.buildSuccessResult(data,"获取成功");

    }


}
