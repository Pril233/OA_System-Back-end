package pril.oa_system.service;

import org.springframework.stereotype.Service;
import pril.oa_system.dao.UserRepository;
import pril.oa_system.pojo.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserRepository userRepository;

   /* @Resource
    RedisUtil redisutil;*/

   public User findByUsername (String username){
       return userRepository.findByUsername(username);
   }

   public List<User> getUserList(){
       List<User> userList = userRepository.findAll();
       return userList;
   }

   public User updateOrSave(User user){
       return userRepository.save(user);
   }

   public void deleteById(Integer id){
       userRepository.deleteById(id);
   }



}
