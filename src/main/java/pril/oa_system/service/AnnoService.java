package pril.oa_system.service;

import org.springframework.stereotype.Service;
import pril.oa_system.dao.AnnoRepository;
import pril.oa_system.pojo.Anno;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnnoService {
    @Resource
    AnnoRepository annoRepository;

    public Anno findAnnoById(int id){
        return annoRepository.findOne(id);
    }

    public Anno save(Anno anno){
        return annoRepository.save(anno);
    }

    public List<Anno> findSomeAnno(){
        return annoRepository.findTop5ByOrderByIdDesc();
    }

    public void deleteAnnoById(int id){
        annoRepository.delete(id);
    }
}
