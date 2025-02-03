package com.apt.diva_ai.domain.news.service;

import com.apt.diva_ai.domain.news.entity.News;
import com.apt.diva_ai.domain.news.repository.NewsRepository;
import com.apt.diva_ai.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public News addNews(String content) {
        News news = News.builder().content(content).build();

        return newsRepository.save(news);
    }

    @Override
    public void updateContent(Long id, String content) {
        News news = newsRepository.findById(id)
            .orElseThrow(() -> new CustomException("뉴스 데이터가 존재하지 않습니다 : id = " + id));

        news.setContent(content);
    }
}
