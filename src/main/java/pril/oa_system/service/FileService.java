package pril.oa_system.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pril.oa_system.dao.FileRepository;
import pril.oa_system.pojo.FileInfo;
import pril.oa_system.pojo.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;

@Service
public class FileService {
    //private  final static String rootPath = "C:/Users/41473/Desktop/File";
    @Resource
    FileRepository fileRepository;

    @Resource
    UserService userService;


    //判断是否存在该文件夹，若不存在则创建文件夹
    public void uploadFile(MultipartFile[] multipartFiles,HttpServletRequest request,int id){
        String rootPath = request.getServletContext().getRealPath("file/");

        File fileDir = new File(rootPath);
        if(!fileDir.exists() && !fileDir.isDirectory()){
            fileDir.mkdirs();
        }

        for(MultipartFile multipartFile : multipartFiles){
            String storagePath = rootPath + multipartFile.getOriginalFilename();
            System.out.println("上传的文件:"+multipartFile.getOriginalFilename()+",上传的路径"+storagePath);
            addFileInfo(id,multipartFile);

            try {
                multipartFile.transferTo(new File(storagePath));
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public void addFileInfo(int id,MultipartFile multipartFile){
        User user = userService.findUserById(id);
        String fileSize = multipartFile.getSize()+"";
        FileInfo fileInfo = new FileInfo(multipartFile.getOriginalFilename(),user.getName(),fileSize+"字节");
        saveFileInfo(fileInfo);


    }

    public FileInfo saveFileInfo(FileInfo fileInfo){
        return fileRepository.save(fileInfo);
    }



    public void downloadFile(String fileName, HttpServletResponse response,HttpServletRequest request){
        String rootPath = request.getServletContext().getRealPath("file/");


        OutputStream os = null;
        InputStream is = null;

        try{
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"),"ISO8859-1"));
            File f = new File(rootPath+fileName);
            is = new FileInputStream(f);
            if(is ==null ){
                System.out.println("下载附件失败，请检查文件“" + fileName + "”是否存在");
            }
            IOUtils.copy(is, os);

        } catch (IOException e) {

        }
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public Page<FileInfo> getFileInfoPage(int pagenum,int pagesize){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pagenum,pagesize, sort);
        Page<FileInfo> pageFromJPA = fileRepository.findAll(pageable);
        return pageFromJPA;

    }
}
