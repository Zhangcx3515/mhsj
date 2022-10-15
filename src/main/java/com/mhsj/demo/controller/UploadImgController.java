package com.mhsj.demo.controller;

import com.mhsj.demo.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadImgController {

    @Autowired
    private OssService ossService;

    @PostMapping("uploadImg")
    public String upload(@RequestParam("file") MultipartFile file){
        String path = ossService.upload(file);
        return path;
    }

}
