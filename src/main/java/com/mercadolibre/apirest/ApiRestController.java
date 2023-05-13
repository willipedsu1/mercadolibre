package com.mercadolibre.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.model.Dna;
import com.mercadolibre.model.ErrorException;
import com.mercadolibre.model.Stats;
import com.mercadolibre.service.IAdnmeli;
import com.mercadolibre.service.IStats;

@RestController
@RequestMapping(produces = "application/json")
public class ApiRestController {

	@Autowired
	private IAdnmeli service;
	
	@Autowired
	private IStats services2;
	
	@PostMapping(value = "/meli_dna")
	public ResponseEntity<String> isMeliDNA(@RequestBody Dna body) {
		
		try {
			if(service.isMeliDNA(body.getDna())) {
				return new ResponseEntity<>("Es Adn Meli",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No Es Adn Meli",HttpStatus.FORBIDDEN);
			}
		} catch (ErrorException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/stats")
	public Stats stats() {
		return services2.estadisticas();
	}

}
