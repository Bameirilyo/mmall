package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Created by bameirilyo
 * @date 18-5-8 下午1:55
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
