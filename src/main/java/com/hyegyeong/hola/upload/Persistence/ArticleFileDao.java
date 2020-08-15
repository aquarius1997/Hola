package com.hyegyeong.hola.upload.Persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ArticleFileDao {
    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.hyegyeong.hola.mappers.upload.articleFileMapper";

    /**
     * 게시들 등록시 첨부 파일을 추가한다
     * @param fileName 첨부파일 명
     * @throws Exception
     */
    public void addFile(String fileName) throws Exception {
        sqlSession.insert(NAMESPACE + ".addFile", fileName);
    }
}
