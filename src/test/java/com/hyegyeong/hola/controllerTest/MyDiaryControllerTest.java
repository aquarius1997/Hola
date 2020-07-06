package com.hyegyeong.hola.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyegyeong.hola.dto.DiaryDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:web/WEB-INF/dispatcher-servlet.xml", "file:web/WEB-INF/applicationContext.xml"})
public class MyDiaryControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(MyDiaryControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup () {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        logger.info("End setup...");
    }

    /**
     * 사용자 ID가 1인 유저가 다이어리를 저장하는 요청을 제대로 받아 처리하는지 테스트
     * @throws Exception 테스트 실패시 Exception 발생
     */
    @Test
    public void testSaveDiary () throws Exception {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setTitle("Controller Unit Test7");
        diaryDTO.setContent("controller test content7..");
        diaryDTO.setMemberId(1);

        //jackson의 ObjectMapper를 이용해 객체를 json으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(diaryDTO);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/my-diaries/diary")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 특정 사용자가 게시한 모든 다이어리를 가져온다.
     * @throws Exception 테스트 실패시 Exception 발생
     */
    @Test
    public void testGetDiaryList () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/my-diaries/{memberId}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 다이어리의 내용을 읽어온다
     * @throws Exception 테스트 실패시 Exception 발생
     */
    @Test
    public void testGetDiary () throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/my-diaries/{memberId}/{diaryId}",1, 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 다이어리 내용을 수정한다
     * @throws Exception 테스트 실패시 Exception 발생
     */
    @Test
    public void testUpdateDiary () throws Exception {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setTitle("Controller Unit test");
        diaryDTO.setContent("update test");
        diaryDTO.setMemberId(1);
        diaryDTO.setDiaryId(1);

        //jackson의 ObjectMapper를 이용해 객체를 json으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(diaryDTO);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/my-diaries/diary")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 다이어리 아이디에 해당하는 다이어리르 삭제한다
     * @throws Exception 테스트 실패시 Exception 발생
     */
    @Test
    public void testDeleteDiary () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/my-diaries/{diaryId}", 2))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
