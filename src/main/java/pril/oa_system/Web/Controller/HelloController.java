package pril.oa_system.Web.Controller;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/login")
    public void hello() {
        SimpleHash simpleHash = new SimpleHash("MD5","123456qwe","Pril",1024);
        //利用SimpleHash加密用户密码,盐值是user.getName
        System.out.println(simpleHash.toString());
    }
}

