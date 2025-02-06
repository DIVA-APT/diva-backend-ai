package com.apt.diva_ai.domain.news.service;

import com.apt.diva_ai.domain.inference.dto.ScriptResponseDTO;
import com.apt.diva_ai.domain.news.entity.News;

public interface NewsService {

    News addNews(ScriptResponseDTO response);

    void updateContent(Long id, ScriptResponseDTO response);
}
