package pril.oa_system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pril.oa_system.dao.DepartRepository;
import pril.oa_system.dao.UserRepository;
import pril.oa_system.pojo.Depart;
import pril.oa_system.pojo.User;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserRepository userRepository;

    @Resource
    DepartRepository departRepository;

   /* @Resource
    RedisUtil redisutil;*/

   public User findByUsername (String username){
       return userRepository.findByUsername(username);
   }

   public List<User> getUserList(){
       List<User> userList = userRepository.findAll();
       return userList;
   }

   public Page<User> getPageUsers(int pagenum,int pagesize){
       Sort sort = new Sort(Sort.Direction.ASC, "id");
       Pageable pageable = new PageRequest(pagenum,pagesize, sort);
       Page<User> pageFromJPA = userRepository.findAll(pageable);
       return pageFromJPA;
   }




   public User updateOrSave(User user){
       return userRepository.save(user  );
   }
   
   public void add(User user){userRepository.save(user);}

   public void deleteById(Integer id){

       userRepository.deleteById(id);
   }

   public List<User> getUserListByPage(int pagenum,int pagesize){

       return null;
   }

   public User findUserById(int id){

       return userRepository.findOne(id);

   }


}
