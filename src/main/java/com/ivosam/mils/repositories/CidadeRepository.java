package com.ivosam.mils.repositories;

import com.ivosam.mils.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
