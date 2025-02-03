package com.apt.diva_ai.domain.macroeconomics.repository;

import com.apt.diva_ai.domain.macroeconomics.entity.Macroeconomics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MacroeconomicsRepository extends JpaRepository<Macroeconomics, Long> {

}
