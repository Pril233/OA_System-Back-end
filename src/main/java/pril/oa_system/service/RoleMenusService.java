package pril.oa_system.service;

import org.springframework.stereotype.Service;
import pril.oa_system.dao.RoleMenuRepository;
import pril.oa_system.pojo.RoleMenus;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleMenusService {
    @Resource
    RoleMenuRepository roleMenuRepository;

    public List<RoleMenus> findAllByRid(Integer rid) {
        return roleMenuRepository.findAllByRidOrderByMid(rid);
    }
}
