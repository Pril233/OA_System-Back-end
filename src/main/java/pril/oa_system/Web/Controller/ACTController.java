package pril.oa_system.Web.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pril.oa_system.dao.ActivityRepository;
import pril.oa_system.dao.OptionRepository;
import pril.oa_system.dao.UserActRepository;
import pril.oa_system.pojo.*;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.FileService;
import pril.oa_system.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ACTController {
    @Resource
    FileService fileService;

    @Resource
    UserService userService;

    @Resource
    ActivityRepository activityRepository;

    @Resource
    OptionRepository optionRepository;

    @Resource
    UserActRepository userActRepository;

    @GetMapping("/fileList")
    public Result getFileList(String query,int pagenum,int pagesize){
        Page<FileInfo> page = fileService.getFileInfoPage(pagenum-1,pagesize);
        List<FileInfo> files = page.getContent();
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",page.getTotalElements());
        data.put("pagenum",pagenum);
        data.put("files",files);
        return ResultFactory.buildSuccessResult(data,"获取成功");


    }


    @PostMapping("/upFile")
    public void upFile(@RequestParam(value="fileInfo") int id,@RequestParam("file") MultipartFile[] files, HttpServletRequest request){
        //System.out.println(id);
        fileService.uploadFile(files,request,id);
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam String fileName, HttpServletResponse response,HttpServletRequest request){
        fileService.downloadFile(fileName,response,request);
    }


    @PostMapping("/act/{id}")
    public Result addACT(@PathVariable(value="id") int id, @RequestBody Activity activity){
       /* System.out.println(JSONObject.fromObject(activity));
        System.out.println(id);*/
        User user = userService.findUserById(id);
        activity.setCreationdata(new Timestamp(System.currentTimeMillis()));
        activity.setFounder(user.getName());
        Activity returnActivity = activityRepository.save(activity);

        //如果活动类型是投票，同时在Option增加选项
        if(returnActivity.getType().equals("2")){
            Option AgreeOption = new Option("赞成",returnActivity);
            Option disAgreeOption = new Option("反对",returnActivity);
            optionRepository.save(AgreeOption);optionRepository.save(disAgreeOption);


        }



        return ResultFactory.buildSuccessResult(null,"成功");
    }

    @GetMapping("/CommonList")
    public Result getCommonList(){
        List<Activity> commonList =  activityRepository.findAllByTypeOrderByCreationdataDesc("1");
        return ResultFactory.buildSuccessResult(commonList,"成功");
    }

    @GetMapping("/voteList")
    public Result getVoteList(){

     /*  name: 'dfdf',
                time: '2012-12-12',
                title: '周六去团队建设',
                content: '加班',
                Agree: '30.55',
                DisAgree: '69.55'**/
        List<Activity> VoteList =  activityRepository.findAllByTypeOrderByCreationdataDesc("2");
        List<Object> voteListWithOption = new ArrayList<>();
        for(Activity activity:VoteList){
            Map<String,Object> map = new HashMap<>();
            map.put("name",activity.getFounder());
            map.put("time",activity.getCreationdata()+"");
            System.out.println(activity.getCreationdata());
            map.put("title",activity.getTitle());
            map.put("content",activity.getContent());
            map.put("Agree",Integer.parseInt(optionRepository.findByActivityAndName(activity,"赞成").getCount()));
            map.put("DisAgree",Integer.parseInt(optionRepository.findByActivityAndName(activity,"反对").getCount()));
            voteListWithOption.add(map);
        }
        //System.out.println(JSONArray.fromObject(voteListWithOption));
        return ResultFactory.buildSuccessResult(voteListWithOption,"成功");

    }

    @GetMapping("/voteListToVote")
    public Result voteListToVote(){
        List<Activity> VoteList =  activityRepository.findAllByTypeOrderByCreationdataDesc("2");
        return ResultFactory.buildSuccessResult(VoteList,"成功");
    }

    @PostMapping("/vote/{aid}/{Vote}/{uid}")
    public Result vote(@PathVariable("aid") int aid,@PathVariable("Vote") int vote,@PathVariable("uid") int uid){
        Activity activity = activityRepository.findOne(aid);
        //System.out.println(aid+"....."+vote+"......"+uid);
        if(userActRepository.findUserActByAidAndUid(aid,uid)!=null){

            return ResultFactory.buildFailResult("已经投过票");
        }
        else{
            UserAct userAct = new UserAct();
            userAct.setAid(aid); userAct.setUid(uid);
            userAct.setType(vote+"");
            userActRepository.save(userAct);
            if(vote==1) {
                Option option = optionRepository.findByActivityAndName(activity, "赞成");
                int count = Integer.parseInt(option.getCount());
                count = count + 1;
                option.setCount(count+"");
                optionRepository.save(option);

            }
            else{
                Option option = optionRepository.findByActivityAndName(activity, "反对");
                int count = Integer.parseInt(option.getCount());
                count = count + 1;
                option.setCount(count+"");
                optionRepository.save(option);
            }
        }
        return ResultFactory.buildSuccessResult(null,"投票成功");


    }


}
