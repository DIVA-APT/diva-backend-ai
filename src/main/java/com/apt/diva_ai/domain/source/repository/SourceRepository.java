package com.apt.diva_ai.domain.source.repository;

import com.apt.diva_ai.domain.source.entity.Source;
import com.apt.diva_ai.global.enums.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {

    @Modifying
    @Query("DELETE FROM Source s WHERE s.sourceId IN :ids "
        + "AND s.category = :category")
    void deleteAllWithId(List<Long> ids, Category category);

    @Modifying
    @Query("DELETE FROM Source s WHERE s.analysisResult.analysisResultId = :id"
        + " AND s.sourceId in :ids")
    void deleteAllWithNews(Long id, List<Long> ids);

    @Modifying
    @Query("DELETE FROM Source s WHERE s.analysisResult.expertAnalysis.expertAnalysisId = :id")
    void deleteAllWithExpert(Long id);
}
