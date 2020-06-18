package pril.oa_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pril.oa_system.pojo.Activity;
import pril.oa_system.pojo.Option;

public interface OptionRepository extends JpaRepository<Option,Integer> {
    public Option findByActivityAndName(Activity activity,String name);

}
