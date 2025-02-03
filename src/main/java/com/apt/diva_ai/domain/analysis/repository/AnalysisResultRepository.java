package com.apt.diva_ai.domain.analysis.repository;

import com.apt.diva_ai.domain.analysis.entity.AnalysisResult;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {

    @Query("SELECT ar FROM AnalysisResult ar WHERE ar.stock.companyName = :stockName")
    Optional<AnalysisResult> findByStockName(String stockName);
}
