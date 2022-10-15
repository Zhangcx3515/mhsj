package com.mhsj.demo.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.mhsj.demo.service.OssService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.ConstantPropertiesUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upload(MultipartFile file) {
        String endpoint = "oss-cn-zhangjiakou.aliyuncs.com";
        String accessKeyId = "LTAI5tKGotWG6nWJFQFZajwk";

        String accessKeySecret = "ggNkuQ2qkuAVUzOAzDuzQcUuaLh14J";
        String bucketName = "yuhdka";


        try {

            //创建OSS实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew01.jpg
            fileName = uuid + fileName;

            //2 把文件按照日期进行分类
            //获取当前日期
            //   2019/11/12
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            //  2019/11/12/ewtqr313401.jpg
            fileName = datePath + "/" + fileName;

            //设置一个请求头设置为
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));


            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName, fileName,inputStream ,objectMetadata);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //  https://edu-teacher-head-101.oss-cn-beijing.aliyuncs.com/01.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static String getcontentType(String FilenameExtension) {

        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return " image/bmp";
        }

        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }

        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(". jpg") ||
                FilenameExtension.equalsIgnoreCase(". png")) {
            return " image/jpg";
        }

        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }

        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }

        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }

        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }

        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }

        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }

        return "image/jpg";
    }

}
