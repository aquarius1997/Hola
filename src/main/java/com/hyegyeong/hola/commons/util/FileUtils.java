package com.hyegyeong.hola.commons.util;

import com.hyegyeong.hola.mydiary.dto.MydiaryDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.*;

@Component
public class FileUtils {
    private static final String filePath = "/Users/songhyegyeong/IdeaProjects/holaUploadTest";  // 파일을 저장시킬 위치

    public List<Map<String, Object>> parseInsertFileInfo (MydiaryDto mydiaryDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        // Map은 데이터를 순차적으로 접근할 수 없으므로 Iterator로 Map의 데이터들을 순차적으로 접근
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> listMap = null;

        int mydiaryDtoDiaryId = mydiaryDto.getDiaryId();
        int mydiaryDtoMemberId = mydiaryDto.getMemberId();

        File file = new File(filePath);
        if(file.exists() == false) {
            file.mkdirs();
        }

        while(iterator.hasNext()) {
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false) {
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = getRandomString() + originalFileExtension;

                file = new File(filePath + storedFileName);
                multipartFile.transferTo(file);
                listMap = new HashMap<String, Object>();
                listMap.put("DIARY_ID", mydiaryDtoDiaryId);
                listMap.put("MEMBER_ID", mydiaryDtoMemberId);
                listMap.put("ORIG_NAME", originalFileName);
                listMap.put("FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                list.add(listMap);
            }
        }
        return list;
    }

    /**
     * 32자의 랜덤한 문자열(숫자포함)을 만들어 변환
     * @return
     */
    public static String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
