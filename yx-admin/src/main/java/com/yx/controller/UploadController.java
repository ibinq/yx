//package com.yx.controller;
//
//import com.yx.service.QiniuService;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @author ZhouBing
// * @version 1.0
// * @date 2020/3/4 14:52
// */
//@RestController
//@RequestMapping("/upload")
//@Slf4j
//@Api(value = "上传",tags = "上传")
//public class UploadController   {
//
//    @Autowired
//    QiniuService qiniuService;
//
//    @PostMapping("/image")
//    @ResponseBody
//    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//        if(file.isEmpty()) {
//            return "error";
//        }
//        try {
//            String fileUrl=qiniuService.saveImage(file);
//            return   fileUrl;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "fail";
//    }
//}
