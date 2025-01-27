package com.apt.diva_ai.domain.news.service;

import com.apt.diva_ai.domain.news.entity.News;

public interface NewsService {

    News addNews(String content);

    void updateContent(Long id, String content);
}
