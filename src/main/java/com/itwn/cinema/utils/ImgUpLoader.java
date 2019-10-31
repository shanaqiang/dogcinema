package com.itwn.cinema.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/26
 * @since 1.0.0
 */
public class ImgUpLoader {

    public static String uploadImg(String path, MultipartFile uploadImg){
        File f=new File(path);
        if (!f.exists()){
            f.mkdir();
        }
        String fileName=uploadImg.getOriginalFilename();
        UUID uuid=UUID.randomUUID();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        String newFileName=uuid.toString()+suffix;
        //文件夹名和文件名拼成
        File file=new File(f,newFileName);
        //开始上传
        try {
            uploadImg.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFileName;
    }
}

