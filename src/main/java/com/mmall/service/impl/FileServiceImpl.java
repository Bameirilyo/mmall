package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Created by bameirilyo
 * @date 18-5-8 下午1:55
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService{

    //打印日志
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file,String path){
        String fileName = file.getOriginalFilename();
        //得到扩展名，并生成随机名避免重名文件覆盖原文件
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploafFileName = UUID.randomUUID().toString() + "." +fileExtensionName;
        logger.info("开始上传文件，上传的文件名:{},上传的路径:{},新文件名：{}",fileName,path,uploafFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploafFileName);

        try {
            file.transferTo(targetFile);//文件已经上传成功

            FTPUtil.uploadFile(Lists.newArrayList(targetFile));//已经上传到ftp服务器上

            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }

        return targetFile.getName();
    }

}
