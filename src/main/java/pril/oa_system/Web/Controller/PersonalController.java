package pril.oa_system.Web.Controller;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.*;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.PersonalService;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class PersonalController {
    @Resource
    PersonalService personalService;

    @PutMapping("/password/{id}")
    public Result editPassword(@PathVariable(value="id") int id, @RequestBody Map<String, String> map){
        if(personalService.editPassword(id,map.get("oldPassword"),map.get("newPassword"))){
            return ResultFactory.buildSuccessResult(null,"修改密码成功");
        }
        else
        return ResultFactory.buildFailResult("修改密码失败");
    }
}
