package pril.oa_system.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import pril.oa_system.pojo.User;

import javax.annotation.Resource;

@Service
public class PersonalService {
    @Resource
    UserService userService;

    public boolean editPassword(int id,String oldPassword,String newPassword){
        System.out.println("要修改密码的员工id:"+id);
        User user = userService.findUserById(id);
        SimpleHash oldSimpleHash = new SimpleHash("MD5",oldPassword,user.getUsername(),1024);
        if(oldSimpleHash.toString().equals(user.getPassword())){
            SimpleHash newSimpleHash = new SimpleHash("MD5",newPassword,user.getUsername(),1024);
            user.setPassword(newSimpleHash.toString());
            userService.updateOrSave(user);
            return true;
        }
        else return false;


    }
}
