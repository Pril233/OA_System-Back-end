package pril.oa_system.Web.Controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pril.oa_system.pojo.FileInfo;
import pril.oa_system.result.Result;
import pril.oa_system.result.ResultFactory;
import pril.oa_system.service.FileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FileController {
    @Resource
    FileService fileService;

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


}
