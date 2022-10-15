package com.mhsj.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface OssService {
    /**
     * 图片上传返回url
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
