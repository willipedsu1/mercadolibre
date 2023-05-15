package com.mercadolibre.service;

import com.mercadolibre.model.Adnmeli;
import com.mercadolibre.model.ErrorException;
import com.mercadolibre.model.Stats;
import com.mercadolibre.repository.IAdnmeliRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AdnmeliImplTest {

    @InjectMocks
    private AdnmeliImpl service;

    @Mock
    private IAdnmeliRepository repo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void isMeliDNAServiceTest() throws ErrorException {

        boolean resp;
        String [] array = {"MELIIL","LMIEIM","IMMEIL","EIMMIL","EEEELI","LELMII"};

        Adnmeli reg = new Adnmeli();
        reg.setBaseAdn(Arrays.toString(array));

        when(repo.findByBaseAdn(Arrays.toString(array))).thenReturn(null);
        when(repo.save(reg)).thenReturn(null);

        resp = service.isMeliDNA(array);

        assertTrue(resp);

    }

    @Test
    public void isNotMeliDNAServiceTest() throws ErrorException {

        boolean resp;
        String [] array = {"MELIIL","LMIEIM","IMMIEL","EIMEEL","EMLELE","LLLMII"};

        Adnmeli reg = new Adnmeli();
        reg.setBaseAdn(Arrays.toString(array));

        when(repo.findByBaseAdn(Arrays.toString(array))).thenReturn(null);
        when(repo.save(reg)).thenReturn(null);

        resp = service.isMeliDNA(array);

        assertFalse(resp);

    }

    @Test
    public void validaCaracteresTest() {

        boolean resp;
        String [] array = {"MELIIL","LMIEIM","IMMIEL","EIMEEL","EMLELE","LLLMIA"};

        Adnmeli reg = new Adnmeli();
        reg.setBaseAdn(Arrays.toString(array));

        try {
            when(repo.findByBaseAdn(Arrays.toString(array))).thenReturn(null);
            when(repo.save(reg)).thenReturn(null);

            service.isMeliDNA(array);
        }catch (ErrorException e){
            assertNotNull(e);
        }

    }

    @Test
    public void validaDuplicadosTest() {

        boolean resp;
        String [] array = {"MELIIL","LMIEIM","IMMIEL","EIMEEL","EMLELE","LLLMII"};

        Adnmeli reg = new Adnmeli();
        reg.setBaseAdn(Arrays.toString(array));

        try {
            when(repo.findByBaseAdn(Arrays.toString(array))).thenReturn(new Adnmeli());
            when(repo.save(reg)).thenReturn(null);

            service.isMeliDNA(array);
        }catch (ErrorException e){
            assertNotNull(e);
        }

    }

    @Test
    public void validaDimensionesTest() {

        boolean resp;
        String [] array = {"MELIIL","LMIEIM","IMMIEL","EIMEEL","EMLELE","LLLMI"};

        Adnmeli reg = new Adnmeli();
        reg.setBaseAdn(Arrays.toString(array));

        try {
            when(repo.findByBaseAdn(Arrays.toString(array))).thenReturn(null);
            when(repo.save(reg)).thenReturn(null);

            service.isMeliDNA(array);
        }catch (ErrorException e){
            assertNotNull(e);
        }

    }

}
