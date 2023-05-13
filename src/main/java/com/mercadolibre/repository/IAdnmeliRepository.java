package com.mercadolibre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercadolibre.model.Adnmeli;

public interface IAdnmeliRepository extends JpaRepository<Adnmeli, Integer> {
	
	Adnmeli findByBaseAdn(String baseadn);
	
	@Query(value = "select count(*) from adnmeli where estado = :estado", nativeQuery = true)
	Integer cantidad(@Param("estado") boolean estado);
	

}
