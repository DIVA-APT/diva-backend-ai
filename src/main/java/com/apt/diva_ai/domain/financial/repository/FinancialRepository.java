package com.apt.diva_ai.domain.financial.repository;

import com.apt.diva_ai.domain.financial.entity.Financial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialRepository extends JpaRepository<Financial, Long> {

}
