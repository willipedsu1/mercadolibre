package com.mercadolibre.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Adnmeli {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAdnmeli;
	@Column(name = "base_adn", length = 200)
	private String baseAdn;
	@Column(name = "estado")
	private boolean estado;
	
	public int getIdAdnmeli() {
		return idAdnmeli;
	}
	
	public void setIdAdnmeli(int idAdnmeli) {
		this.idAdnmeli = idAdnmeli;
	}
	
	public String getBaseAdn() {
		return baseAdn;
	}
	
	public void setBaseAdn(String baseAdn) {
		this.baseAdn = baseAdn;
	}
	
	public boolean isEstado() {
		return estado;
	}
	
	public void setEstado(boolean esatdo) {
		this.estado = esatdo;
	}
	
}
