package com.hyegyeong.hola.mydiary.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/upload")
public class UploadController {

    private static final String uploadPath = "/Users/songhyegyeong/IdeaProjects/holaUploadTest";

    @GetMapping("/uploadForm")
    public ModelAndView uploadForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload/uploadForm");
        return modelAndView;
    }

    @PostMapping("/uploadForm")
    public ModelAndView uploadFormPost(MultipartFile file) throws Exception {
        log.info("파일이름 : " + file.getOriginalFilename());
        log.info("파일 크기 :" + file.getSize());
        log.info("컨텐트 타입 : " + file.getContentType());

        String savedName = file.getOriginalFilename();

        savedName = uploadFile(savedName, file.getBytes());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload/uploadResult");
        modelAndView.addObject("savedName", savedName);

        return modelAndView;
    }

    /*
    파일명을 UUID를 만들어 저장한다 (중복방지)
     */
    private String uploadFile(String originalName, byte[] fileData) throws Exception {
        //uuid생성
        UUID uuid = UUID.randomUUID();
        //uuid + 파일이름
        String savedName = uuid.toString() + "_" + originalName;
        File target = new File(uploadPath, savedName);
        FileCopyUtils.copy(fileData, target);
        return savedName;
    }
}
