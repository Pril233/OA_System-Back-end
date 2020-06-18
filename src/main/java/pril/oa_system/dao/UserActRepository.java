package pril.oa_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.UserAct;

public interface UserActRepository extends JpaRepository<UserAct,Integer>{
    public UserAct findUserActByAidAndUid(int aid,int uid);
}
