package com.apt.diva_ai.domain.news.repository;

import com.apt.diva_ai.domain.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
