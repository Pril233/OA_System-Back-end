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
public class DepartService {
    @Resource
    DepartRepository departRepository;

    @Resource
    UserRepository userRepository;

    public List<Depart> getDepartList(){
        List<Depart> departs = departRepository.findAll();
        return departs;
    }

    public Page<Depart> getPageUsers(int pagenum, int pagesize){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pagenum,pagesize, sort);
        Page<Depart> pageFromJPA = departRepository.findAll(pageable);
        return pageFromJPA;
    }

    public void add(Depart depart){departRepository.save(depart);}

    public Depart findDepartById(int id){return departRepository.findOne(id);}

    public Depart editDepart(Depart depart){
        return departRepository.save(depart);
    }

    public void deleteDepartById(int id){
        Depart defaultDepart = new Depart(0);
        Depart depart = departRepository.findOne(id);
        List<User> usersFindByDepart = userRepository.findByDepart(depart);
        for(User user : usersFindByDepart){
            user.setDepart(defaultDepart);
            userRepository.save(user);
        }

        departRepository.delete(id);
    }

}
