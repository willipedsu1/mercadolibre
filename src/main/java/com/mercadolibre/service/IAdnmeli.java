package com.mercadolibre.service;

import com.mercadolibre.model.ErrorException;

public interface IAdnmeli {
	
	boolean isMeliDNA(String[] dna) throws ErrorException;

}
