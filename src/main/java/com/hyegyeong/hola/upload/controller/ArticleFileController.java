package com.hyegyeong.hola.upload.controller;

import com.hyegyeong.hola.commons.util.UploadFileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 게시글을 입력처리하기 전에 클라이언트로부터 AJAX 통신을 통해 첨부파일을 미리 서버에 저장한다
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/article/file")
public class ArticleFileController {

    //private final ArticleFileService articleFileService;

    /**
     * 파일 업로드 처리
     * @param file 클라이언트로부터 전달받은 file
     * @param httpServletRequest 클라이언트로부터 전달받은 request
     * @return uploadFileUtils클래스의 uploadFile() 메서드의 리턴 ("/년/월/일/UUID_파일명"의 문자열)을 호출한
     * 클라이언트의 화면으로 리턴한다
     * @throws Exception
     */
    @PostMapping(value = "/upload", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadFile(MultipartFile file,
                                             HttpServletRequest httpServletRequest) throws Exception {
        ResponseEntity<String> entity = null;
        try {
            String savedFilePath = UploadFileUtils.uploadFile(file, httpServletRequest);
            entity = new ResponseEntity<>(savedFilePath, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();;
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    /**
     * 첨부파일이 서버에 업로드되면, 화면에 업로드한 첨부파일을 확인할 수 있도록 한다.
     * @param fileName 클라이언트로부터 전달받은 첨부파일의 이름
     * @param httpServletRequest 클라이언트의 요청
     * @return getHttpHeaders() 메서드로 리턴받은 HttpHeaders 객체와 첨부파일 데이터를 호출한 클라이언트 화면으로 리턴
     * @throws Exception
     */
    @GetMapping("/display")
    public ResponseEntity<byte[]> displayFile(String fileName,
                                              HttpServletRequest httpServletRequest) throws Exception {
        HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName); // Http 헤더 설정
        String rootPath = UploadFileUtils.getRootPath(fileName, httpServletRequest);    //업로드 기본 경로

        ResponseEntity<byte[]> entity = null;

        // 파일 데이터, HttpHeader 전송
        try (InputStream inputStream = new FileInputStream(rootPath + fileName)) {
            entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    /**
     * 첨부파일 삭제
     * @param fileName 클라이언트로 부터 전달받은 첨부파일 명
     * @param httpServletRequest 클라이언트의 request
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(String fileName, HttpServletRequest httpServletRequest) {
        ResponseEntity<String> entity = null;

        try {
            UploadFileUtils.deleteFile(fileName, httpServletRequest);
            entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

}
