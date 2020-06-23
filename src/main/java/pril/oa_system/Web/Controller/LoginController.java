package pril.oa_system.Web.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pril.oa_system.pojo.User;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.util.JWTUtil;

import java.util.HashMap;

@RestController
public class LoginController {
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user){
        SimpleHash simpleHash = new SimpleHash("MD5",user.getPassword(),user.getUsername(),1024);
        //利用SimpleHash加密用户密码,盐值是user.getName
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),simpleHash.toString());
        //将加密后的密码和用户名封装到token
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try{
            //完成登录
            subject.login(token);
            //System.out.println(subject.hasRole("管理员"));

            //获得用户对象
            User resultUser = (User)subject.getPrincipal();
            HashMap<Object, Object> data = new HashMap<>();
            /*System.out.println(resultUser.getId());*/
            data.put("id",resultUser.getId());
            data.put("name",resultUser.getName());
            data.put("token", JWTUtil.sign(resultUser));
            return ResultFactory.buildSuccessResult(data,"登录成功");
        }catch (AuthenticationException e) {
            //登录失败
            return ResultFactory.buildFailResult("登录失败");
        }

    }

   /*public static void main(String[] args) {
        //明码
        String password = "123456qwe";
        //加密算法
        String algorithmName = "MD5";
        //要加密的密码
        Object source = password;
        //盐值，一般都是用户名或者userid，要保证唯一
        Object salt = "Pril";
        //加密次数
        int hashIterations = 1024;
       SimpleHash simpleHash = new SimpleHash(algorithmName,source,salt,hashIterations);
        //打印出经过盐值、加密次数、md5后的密码
        System.out.println(simpleHash.toString());
    }*/
}
