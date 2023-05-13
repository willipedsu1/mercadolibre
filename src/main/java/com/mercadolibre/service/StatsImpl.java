package com.mercadolibre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.model.Stats;
import com.mercadolibre.repository.IAdnmeliRepository;

@Service
public class StatsImpl implements IStats {

	@Autowired
	private IAdnmeliRepository repo;
	
	@Override
	public Stats estadisticas() {
		Stats response = new Stats();
		double ratio;
		int adn_meli = repo.cantidad(true);
		int other_adn = repo.cantidad(false);
		
		if(other_adn == 0) ratio = adn_meli;
		else ratio = (double) adn_meli/other_adn;
		
		response.setCount_dna_meli(adn_meli);
		response.setCount_other_dna(other_adn);
		response.setRatio(ratio);
		
		return response;
	}

}
