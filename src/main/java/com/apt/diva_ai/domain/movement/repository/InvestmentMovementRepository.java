package com.apt.diva_ai.domain.movement.repository;

import com.apt.diva_ai.domain.movement.entity.InvestmentMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentMovementRepository extends JpaRepository<InvestmentMovement, Long> {

}
