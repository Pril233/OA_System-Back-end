package pril.oa_system.Web.Controller;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pril.oa_system.dao.PlanRepository;
import pril.oa_system.pojo.Plan;
import pril.oa_system.pojo.User;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.UserService;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Resource
    UserService userServiceImpl;

    @Resource
    PlanRepository planRepository;

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
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",page.getTotalElements());
        data.put("pagenum",pagenum);
        data.put("users",users);
        System.out.println(data);
        return ResultFactory.buildSuccessResult(data,"获取成功");

    }

    @PostMapping("/users")
    public Result add(@RequestBody User user){
        /*System.out.println(user.getPost().getId());*/
        SimpleHash simpleHash = new SimpleHash("MD5",user.getPassword(),user.getUsername(),1024);
        user.setPassword(simpleHash.toString());
        userServiceImpl.add(user);

        return ResultFactory.buildSuccessResult(null,"添加员工成功!");
    }

    @GetMapping("/users/{id}")
    public Result getUserById(@PathVariable("id") int id){
        User user = userServiceImpl.findUserById(id);
        System.out.println(user.getUsername());
        return ResultFactory.buildSuccessResult(user,"通过ID查找用户成功");
    }

    @PutMapping("/users")
        public Result editUserById(@RequestBody User user){
            /*System.out.println(JSONObject.fromObject(user));*/
            userServiceImpl.updateOrSave(user);
            return ResultFactory.buildSuccessResult(null,"修改员工成功");
        }

    @Transactional
    @DeleteMapping("/users/{id}")
        public Result deleteUserById(@PathVariable("id") int id){
            userServiceImpl.deleteById(id);
            return ResultFactory.buildSuccessResult(null,"删除员工成功");
    }

    @PostMapping("/plan/{id}")
    public Result addPlan(@PathVariable("id") int id, @RequestBody Map<String,String> map){
        User user = userServiceImpl.findUserById(id);
        Plan plan = new Plan(user,map.get("name"));
        planRepository.save(plan);
        return ResultFactory.buildSuccessResult(null,"保存计划成功");
    }

    @GetMapping("/plan/{id}")
    public Result getPlanByUid(@PathVariable("id") int id){
        int uid = id;
        User user = userServiceImpl.findUserById(uid);
        List<Plan> plans = planRepository.findByUserOrderByIdDesc(user);
        return ResultFactory.buildSuccessResult(plans,"获取计划列表成功");
    }

    @DeleteMapping("/plan/{id}")
    public Result deletePlanById(@PathVariable("id") int id){
        planRepository.delete(id);
        return ResultFactory.buildSuccessResult(null,"已完成");
    }





}
