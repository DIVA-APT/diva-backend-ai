package com.apt.diva_ai.domain.expert.repository;

import com.apt.diva_ai.domain.expert.entity.ExpertAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertAnalysisRepository extends JpaRepository<ExpertAnalysis, Long> {

}
