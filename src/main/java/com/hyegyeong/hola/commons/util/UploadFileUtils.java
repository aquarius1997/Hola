package com.hyegyeong.hola.commons.util;

import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.util.resources.cldr.mas.CalendarData_mas_KE;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * 파일 업로드와 관련된 기능을 처리한다.
 * 유틸 클래스이므로 클래스 내부 메서드는 static으로 선언해 인스턴스 생성 없이 사용가능하도록 한다.
 * 파일 업로드 처리, 삭제, 출력을 위한 httpHeader설정 메서드는 public 메서드로 선언해 파일 업로드 컨트롤러에서 바로 접근/사용할 수 있도록 한다.
 */
@Slf4j
public class UploadFileUtils {

    // 파일 업로드를 처리한다
    public static String uploadFile(MultipartFile file, HttpServletRequest httpServletRequest) throws Exception {
        String originalFileName = file.getOriginalFilename();
        byte[] fileData = file.getBytes();

        // 파일명 중복 방지
        String uuidFileName = getUuidFileName(originalFileName);

        // 파일 업로드 경로 설정
        String rootPath = getRootPath(originalFileName, httpServletRequest); // 기본 경로 추출(이미지 또는 일반파일)
        String datePath = getDatePath(rootPath);    // 날짜 경로 추출, 날짜 폴더 생성

        // 서버에 파일을 저장
        File target = new File(rootPath + datePath, uuidFileName);  // 파일 객체 생성
        FileCopyUtils.copy(fileData, target);   // 파일 객체에 파일 데이터 복사

        // 이미지 파일이면 썸네일 생성
        if (MediaUtils.getMediaType(originalFileName) != null) {
            uuidFileName = makeThumbnail(rootPath, datePath, uuidFileName);
        }

        // 파일 저장 경로 치환
        return replaceSavedFilePath(datePath, uuidFileName);
    }

    //기본 경로 추출
    public static String getRootPath(String fileName, HttpServletRequest httpServletRequest) {
        String rootPath = "/Users/songhyegyeong/IdeaProjects/hola/src/main/java/resources/upload";
        MediaType mediaType = MediaUtils.getMediaType(fileName);    // 파일 타입 확인

        if(mediaType != null)  //이미지 파일 경로
            return httpServletRequest.getSession().getServletContext().getRealPath(rootPath + "/images");

        return httpServletRequest.getSession().getServletContext().getRealPath(rootPath + "/files");    //일반 파일 경로
    }

    // 날짜 폴더명 추출
    private static String getDatePath(String uploadPath) {
        Calendar calendar = Calendar.getInstance();
        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        makeDateDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;
    }

    // 날짜별 폴더 생성
    private static void makeDateDir(String uploadPath, String... paths) {
        //날짜별 폴더가 이미 존재할 경우 생성하지 않는다
        if (new File(uploadPath + paths[paths.length - 1]).exists())
            return;

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);
            if (!dirPath.exists())
                dirPath.mkdir();
        }
    }

    // 파일 저장 경로 치환
    private static String replaceSavedFilePath(String datePath, String fileName) {
        String savedFilePath = datePath + File.separator + fileName;
        return savedFilePath.replace(File.separatorChar, '/');
    }

    // 파일명 중복 방지 처리
    private static String getUuidFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }

    //파일 삭제
    public static void deleteFile(String fileName, HttpServletRequest httpServletRequest) {
        String rootPath = getRootPath(fileName, httpServletRequest);

        //원본 이미지 파일 삭제
        MediaType mediaType = MediaUtils.getMediaType(fileName);
        if(mediaType != null) {
            String originalImg = fileName.substring(0, 12) + fileName.substring(14);
            new File(rootPath + originalImg.replace('/', File.separatorChar)).delete();
        }

        //파일 삭제(썸네일 이미지)
        new File(rootPath + fileName.replace('/', File.separatorChar)).delete();
    }

    //파일 출력을 위한 HttpHeader설정
    public static HttpHeaders getHttpHeaders(String fileName) throws Exception {
        MediaType mediaType = MediaUtils.getMediaType(fileName);    //파일 타입 확인
        HttpHeaders httpHeaders = new HttpHeaders();

        //이미지 파일이면
        if(mediaType != null) {
            httpHeaders.setContentType(mediaType);
            return httpHeaders;
        }

        //이미지 파일이 아니면
        fileName = fileName.substring(fileName.indexOf("_" + 1));   //UUID 제거
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM); //다운로드 MIME 타입 설정
        //파일명 한글 인코딩 처리
        httpHeaders.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

        return httpHeaders;
    }

    // 썸네일 이미지 생성
    private static String makeThumbnail(String uploadRootPath, String datePath, String fileName) throws Exception {
        // 원본 이미지를 메모리에 로딩
        BufferedImage originalImg = ImageIO.read(new File(uploadRootPath + datePath, fileName));
        // 원본 이미지 축소
        BufferedImage thumbnailImg = Scalr.resize(originalImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
        // 썸네일 파일 명
        String thumbnailImgName = "s_" + fileName;
        // 썸네일 업로드 경로
        String fullPath = uploadRootPath + datePath + File.separator + thumbnailImgName;
        // 썸네일 파일 객체생성
        File newFile = new File(fullPath);
        // 썸네일 파일 확장자 추출
        String formatName = MediaUtils.getFormatName(fileName);
        // 썸네일 파일 저장
        ImageIO.write(thumbnailImg, formatName, newFile);

        return thumbnailImgName;
    }
}
