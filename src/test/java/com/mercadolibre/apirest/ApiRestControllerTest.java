package com.mercadolibre.apirest;

import com.mercadolibre.model.Dna;
import com.mercadolibre.model.ErrorException;
import com.mercadolibre.model.Stats;
import com.mercadolibre.service.IAdnmeli;
import com.mercadolibre.service.IStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ApiRestControllerTest {

    @InjectMocks
    private ApiRestController apiRestController;

    @Mock
    private IAdnmeli adnmeliService;

    @Mock
    private IStats statsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void isMeliDNATrueTest() {

        String [] array = {"MELIIL","LMIEIM","IMMEIL","EIMMIL","EEEELI","LELMI"};
        Dna entrada = new Dna();
        entrada.setDna(array);

        try {
            when(adnmeliService.isMeliDNA(array)).thenReturn(true);
        }catch (ErrorException e){
            assertNotNull(e);
        }

        ResponseEntity<String> resp = apiRestController.isMeliDNA(entrada);
        assertNotNull(resp);
    }

    @Test
    public void isMeliDNAFalseTest() {

        String [] array = {"MELIIL","LMIEIM","IMMIEL","EIMEEL","EMLELE","LLLMII"};
        Dna entrada = new Dna();
        entrada.setDna(array);

        try {
            when(adnmeliService.isMeliDNA(array)).thenReturn(false);
        }catch (ErrorException e){
            assertNotNull(e);
        }

        ResponseEntity<String> resp = apiRestController.isMeliDNA(entrada);
        assertNotNull(resp);
    }

    @Test
    public void isMeliDNAErrorTest() {

        String [] array = {"MELIIL","LMIEIM","IMMEIL","EIMMIL","EEEELI","LELMI"};
        Dna entrada = new Dna();
        entrada.setDna(array);

        try {
            when(adnmeliService.isMeliDNA(array)).thenThrow(new ErrorException(400,""));
        }catch (ErrorException e){
            assertNotNull(e);
        }

    }

    @Test
    public void statsTest() {

        Stats resp = new Stats();

        Stats stats = new Stats();
        stats.setCount_dna_meli(4);
        stats.setCount_other_dna(6);
        stats.setRatio(0.66);

        when(statsService.estadisticas()).thenReturn(stats);
        resp = apiRestController.stats();

        assertEquals(resp,stats);

    }

}
