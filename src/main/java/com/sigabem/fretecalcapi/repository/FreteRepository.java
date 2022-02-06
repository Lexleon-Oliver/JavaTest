package com.sigabem.fretecalcapi.repository;

import com.sigabem.fretecalcapi.entity.Frete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {
}
