package pril.oa_system.Web.Controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pril.oa_system.pojo.Depart;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.DepartService;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@RestController
public class DepartController {
    @Resource
    DepartService departService;

    @GetMapping("/departs")
    public Result getDepartList(){
        List<Depart> departs = departService.getDepartList();
        return ResultFactory.buildSuccessResult(departs,"获取部门列表成功");
    }

    @GetMapping("/departsByPage")
    public Result getDepartListByPage(String query,int pagenum,int pagesize){
        Page<Depart> page = departService.getPageUsers(pagenum-1,pagesize);
        List<Depart> departs = page.getContent();
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",page.getTotalElements());
        data.put("pagenum",pagenum);
        data.put("departs",departs);
        return ResultFactory.buildSuccessResult(data,"获取成功");

    }

    @PostMapping("departs")
    public Result add(@RequestBody Depart depart){
        departService.add(depart);
        return ResultFactory.buildSuccessResult(null,"删除部门信息成功");
    }

    @GetMapping("/departs/{id}")
    public Result getDepartById(@PathVariable("id") int id){
        Depart depart = departService.findDepartById(id);
        return ResultFactory.buildSuccessResult(depart,"通过ID查找部门成功");
    }

    @Transactional
    @PutMapping("/departs")
        public Result editDepart(@RequestBody Depart depart){
            departService.editDepart(depart);
            return ResultFactory.buildSuccessResult(null,"修改部门成功");
        }


    @DeleteMapping("/departs/{id}")
    public Result deleteDepartById(@PathVariable("id") int id){
        departService.deleteDepartById(id);
        return ResultFactory.buildSuccessResult(null,"删除部门成功");
    }


}
