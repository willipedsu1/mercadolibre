package com.mercadolibre.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mercadolibre.model.ErrorException;
import com.mercadolibre.model.Stats;
import com.mercadolibre.repository.IAdnmeliRepository;

public class StatsImplTest {
	
	@InjectMocks
	private StatsImpl service;
	
	@Mock
	private IAdnmeliRepository repo;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void statsServiceTest() {
		
		int cant_adn_meli = 4;
		int cant_adn_other = 6;
		
		when(repo.cantidad(true)).thenReturn(cant_adn_meli);
		when(repo.cantidad(false)).thenReturn(cant_adn_other);
		
		Stats result = service.estadisticas();

		assertEquals(result.getCount_dna_meli() , cant_adn_meli);

	}

}
