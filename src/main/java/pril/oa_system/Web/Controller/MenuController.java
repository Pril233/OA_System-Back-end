package pril.oa_system.Web.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.MenuService;

import javax.annotation.Resource;

@RestController
public class MenuController {
    @Resource
    MenuService menuService;



    /**
     * 获取侧栏菜单列表
     *
     * @return
     */
    @GetMapping("/menuList")
    public Result menuList(){
        Object list = menuService.buildMenuList(0);
        return ResultFactory.buildSuccessResult(list,"请求菜单数据成功");

    }

    @GetMapping("/menusById/{id}")
    public Result getMenuList(@PathVariable("id")int id){
        Object list = menuService.buildMenuListById(id);
        return ResultFactory.buildSuccessResult(list,"请求菜单数据成功");
    }

    @GetMapping("/menus")
    public Result menus(){
        Object list = menuService.buildMenuList(0);
        return ResultFactory.buildSuccessResult(list,"请求菜单数据成功");

    }

}
