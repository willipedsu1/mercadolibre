package com.mercadolibre.service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.model.Adnmeli;
import com.mercadolibre.model.ErrorException;
import com.mercadolibre.repository.IAdnmeliRepository;

@Service
public class AdnmeliImpl implements IAdnmeli {
	
	@Autowired
	private IAdnmeliRepository repo;

	@Override
	public boolean isMeliDNA(String[] dna) throws ErrorException {
		
		Adnmeli reg = new Adnmeli();
		reg.setBaseAdn(Arrays.toString(dna));
		
		if(!validarCaracteres(dna))
			throw new ErrorException(400,"Solo se admiten las letras M,E,L,I");
		
		if(validarDuplicados(dna))
			throw new ErrorException(400,"La cadena ingresada ya fue verificada anteriormente");
		
		char [][] matriz = llenaMatriz(dna);
		if((evaluarHorizontal(matriz) + evaluarVertical(matriz) + evaluarDiagonal(matriz)) > 1) {
			reg.setEstado(true);
			repo.save(reg);
			return true;
		}
		else {
			reg.setEstado(false);
			repo.save(reg);
			return false;
		}
	}
	 
	private boolean validarCaracteres(String[] dna) {
		
		for(int i = 0; i < dna.length; i++){
			Pattern pat = Pattern.compile("(M|E|L|I)+");
			Matcher mat = pat.matcher(dna[i]);
			if(!mat.matches()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean validarDuplicados(String[] dna) {
		String base = Arrays.toString(dna);
		Adnmeli respuesta = repo.findByBaseAdn(base);
		
		if(respuesta == null)
			return false;
		else 
			return true;
	}
	
	private char[][] llenaMatriz(String[] adnArray) throws ErrorException {
			
			char [][] matriz = new char[adnArray.length][adnArray[0].length()];
	        boolean error = false;

	        for (int i = 0; i < adnArray.length; i++){
	            char[] dato = adnArray[i].toCharArray();
	            for (int j = 0; j < dato.length; j++){
	                if(adnArray.length != dato.length){
	                    error = true;
	                }else {
	                	matriz [i][j] = dato[j];
	                } 
	            }
	        }
	        
	        if(error) {
	        	throw new ErrorException(400,"la matriz debe ser de NxN");
	        } 
	        
	        return matriz;
	}
	
    private int evaluarHorizontal(char[][] matriz){
        int cont;
        int result = 0;

        for (int i = 0; i < matriz.length; i++){
            cont = 1;
            for (int j = 0; j < matriz.length; j++){
                if (j > 0){
                    if (matriz[i][j] == matriz[i][j-1]){
                        cont++;
                    }
                    else cont = 1;
                }
                if (cont == 4){
                	result++;
                };
            }
        }
        return result;
    }	
    
    private int evaluarVertical(char[][] matriz){
        int cont;
        int result = 0;

        for (int i = 0; i < matriz.length; i++){
            cont = 1;
            for (int j = 0; j < matriz.length; j++){
                if (j > 0){
                    if (matriz[j][i] == matriz[j-1][i]){
                        cont++;
                    }
                    else cont = 1;
                }
                if (cont == 4){
                	result++;
                };
            }
        }
        return result;
    }
    
    private int evaluarDiagonal(char [][] matriz){
        int cont;
        int result = 0;
        boolean paso;
        
        for(int i = 0; i < matriz.length; i++){
            if (i == 0) {
                for (int j = matriz.length - 1; j > -1; j--) {
                    cont = 1;
                    paso = false;
                    for(int x = i, y = j; x < matriz.length && y < matriz.length; x++, y++){
                        if(paso){
                            if (matriz[x][y] == matriz[x-1][y-1]){
                                cont++;
                            }
                            else cont = 1;
                        }
                        if(cont == 4){
                        	result++;
                        }
                        paso = true;
                    }
                }
            }else{
            	cont = 1;
                paso = false;
                for(int x = i, y = 0; x < matriz.length && y < matriz.length; x++, y++){
                    if(paso){
                        if (matriz[x][y] == matriz[x-1][y-1]){
                            cont++;
                        }
                        else cont = 1;
                    }
                    if(cont == 4){
                    	result++;
                    }
                    paso = true;
                }
            }
        }
        return result;
    }    

}
