package pril.oa_system.Web.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    UserService userServiceImpl;

    @GetMapping("/users")
    public Result getUsers() {
        List users = userServiceImpl.getUserList();
        return ResultFactory.buildSuccessResult(users,"查询所有用户");
    }


}
