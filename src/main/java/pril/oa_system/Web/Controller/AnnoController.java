package pril.oa_system.Web.Controller;

import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import pril.oa_system.pojo.Anno;
import pril.oa_system.pojo.User;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.AnnoService;
import pril.oa_system.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class AnnoController {
    @Resource
    AnnoService annoService;

    @Resource
    UserService userService;

    @GetMapping("/annos")
    public Result getAnnos(){
        List<Anno> annos = annoService.findSomeAnno();
        return ResultFactory.buildSuccessResult(annos,"获取公告信息成功");
    }

    @PostMapping("/anno/{id}")
    public Result addAnno(@PathVariable(value="id") int id, @RequestBody Map<String, String> map){
        User user = userService.findUserById(id);
        String title = map.get("title");
        String content = map.get("content");
        Anno anno = new Anno(title,content,user.getName());
        annoService.save(anno);
        System.out.println(anno.getData());

        return ResultFactory.buildSuccessResult(null,"添加公告成功");
    }

    @PutMapping("/anno/{id}")
    public Result deleteAnnoById(@PathVariable(value="id") int id){
        annoService.deleteAnnoById(id);
        return ResultFactory.buildSuccessResult(null,"删除公告成功");
    }

}
