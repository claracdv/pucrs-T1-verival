package com.clarafelipe;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.clarafelipe.CentroDistribuicao.TIPOPOSTO;

import org.junit.Test;

public class CentroDistribuicaoTest {

    @ParameterizedTest
    @CsvSource(value = {
            "250,5000,625,625,NORMAL",
            "499,9999,1249,1249,NORMAL",
            "249,10000,1250,1250,SOBRAVISO",
            "500,4999,1250,1250,SOBRAVISO",
            "124,10000,1250,1250,EMERGENCIA",
            "500,2499,1250,1250,EMERGENCIA" })
    public void shouldConstructWithStatus(String tAditivo, String tGasolina, String tAlcool1, String tAlcool2,
            String status) {
        CentroDistribuicao cd = new CentroDistribuicao(Integer.parseInt(tAditivo), Integer.parseInt(tGasolina),
                Integer.parseInt(tAlcool1), Integer.parseInt(tAlcool2));
        assertEquals(status, cd.getSituacao().toString());
    }
/*
    @ParameterizedTest
    public void shouldThrowException(String tAditivo, String tGasolina, String tAlcool1, String tAlcool2) {
        assertThrows(IllegalArgumentException.class, () -> {
            CentroDistribuicao cd = new CentroDistribuicao(500, 10000, 624, 1250);
        });
    }
*/
    @ParameterizedTest
    @CsvSource(value = { "250,5000,625,625,NORMAL",
            "249,10000,1250,1250,SOBRAVISO",
            "124,10000,1250,1250,EMERGENCIA" })
    public void shouldDefineSituation(String tAditivo, String tGasolina, String tAlcool1, String tAlcool2,
            String status) {
        CentroDistribuicao cd = new CentroDistribuicao(Integer.parseInt(tAditivo), Integer.parseInt(tGasolina),
                Integer.parseInt(tAlcool1), Integer.parseInt(tAlcool2));
        cd.defineSituacao();
        assertEquals(status, cd.getSituacao().toString());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "300,5000,625,625,100,100",
            "400,5000,625,625,200,100",
            "400,5000,625,625,-10,-1",
            "400,5000,625,625,0,-1" })
    public void shouldReceiveAdictive(String tAditivo, String tGasolina, String tAlcool1, String tAlcool2, String qtd,
            String expected) {
        CentroDistribuicao cd = new CentroDistribuicao(Integer.parseInt(tAditivo), Integer.parseInt(tGasolina),
                Integer.parseInt(tAlcool1), Integer.parseInt(tAlcool2));
        int result = cd.recebeAditivo(Integer.parseInt(qtd));
        assertEquals(Integer.parseInt(expected), result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "300,8000,625,625,1000,1000",
            "400,9000,625,625,2000,1000",
            "400,8000,625,625,-10,-1",
            "400,8000,625,625,0,-1" })
    public void shouldReceiveGasoline(String tAditivo, String tGasolina, String tAlcool1, String tAlcool2, String qtd,
            String expected) {
        CentroDistribuicao cd = new CentroDistribuicao(Integer.parseInt(tAditivo), Integer.parseInt(tGasolina),
                Integer.parseInt(tAlcool1), Integer.parseInt(tAlcool2));
        int result = cd.recebeGasolina(Integer.parseInt(qtd));
        assertEquals(Integer.parseInt(expected), result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "400,9000,900,900,400,400",
            "400,9000,1100,1100,400,300",
            "400,8000,1100,1100,-10,-1",
            "400,8000,1100,1100,0,-1" })
    public void shouldReceiveAlcool(String tAditivo, String tGasolina, String tAlcool1, String tAlcool2, String qtd,
            String expected) {
        CentroDistribuicao cd = new CentroDistribuicao(Integer.parseInt(tAditivo), Integer.parseInt(tGasolina),
                Integer.parseInt(tAlcool1), Integer.parseInt(tAlcool2));
        int result = cd.recebeAlcool(Integer.parseInt(qtd));
        assertEquals(Integer.parseInt(expected), result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "500,10000,1250,1250,0,COMUM,-7,-7,-7,-7",
            "500,10000,1250,1250,20000,COMUM,-21,-21,-21,-21",
            "500,10000,1250,1250,7650,COMUM,118,4645,294,294",
            "250,4000,625,625,2000,COMUM,200,3300,500,500",
            "250,2400,625,625,3000,COMUM,-14,-14,-14,-14",
            "500,10000,1250,1250,7650,ESTRATEGICO,118,4645,294,294",
            "250,4000,625,625,2000,ESTRATEGICO,150,2600,375,375",
            "250,2400,625,625,3000,ESTRATEGICO,100,300,250,250",
            "100,2400,625,625,3000,ESTRATEGICO,100,150,250,250",
            // extras adicionados após code coverage
            "250,2400,125,1125,3000,ESTRATEGICO,100,300,0,500",
            "250,2400,1125,125,3000,ESTRATEGICO,100,300,500,0",
            "500,7000,625,1250,6000,COMUM,200,2800,0,375",
            "500,7000,1250,625,6000,COMUM,200,2800,375,0",
            "250,2400,125,1125,10000,ESTRATEGICO,-21,-21,-21,-21",
    })
    public void shouldEncomendFuel(String tAditivo, String tGasolina, String tAlcool1, String tAlcool2, String qtd,
            String posto,
            String expected1, String expected2, String expected3, String expected4) {
        CentroDistribuicao cd = new CentroDistribuicao(Integer.parseInt(tAditivo), Integer.parseInt(tGasolina),
                Integer.parseInt(tAlcool1), Integer.parseInt(tAlcool2));
        TIPOPOSTO tipoPosto;

        // MAP THE TIPOPOSTO VARIABLE
        if (posto.equals("ESTRATEGICO")) {
            tipoPosto = CentroDistribuicao.TIPOPOSTO.ESTRATEGICO;
        } else {
            tipoPosto = CentroDistribuicao.TIPOPOSTO.COMUM;
        }

        // CHECK ARRAY SIZE
        int tamanho = 0;
        if (Integer.parseInt(expected1) < 0) {
            tamanho = 1;
        } else {
            tamanho = 4;
        }

        // CREATE ARRAY WITH THE RIGHT SIZE
        int[] expected = new int[tamanho];
        if (Integer.parseInt(expected1) < 0) {
            expected = new int[] { Integer.parseInt(expected1) };
        } else {
            expected = new int[] { Integer.parseInt(expected1), Integer.parseInt(expected2),
                    Integer.parseInt(expected3), Integer.parseInt(expected4) };
        }

        // CALLS FUNCTION AND ASSERTS IT
        int[] result = cd.encomendaCombustivel(Integer.parseInt(qtd), tipoPosto);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result[i]);
        }
    }

    // extras adicionados após code coverage
    @Test
    public void shouldGetGasolina() {
        CentroDistribuicao cd = new CentroDistribuicao(0, 2000, 0, 0);
        assertEquals(2000, cd.getGasolina());
    }

    @Test
    public void shouldGetAlcool1() {
        CentroDistribuicao cd = new CentroDistribuicao(0, 0, 1000, 0);
        assertEquals(1000, cd.getAlcool1());
    }

    @Test
    public void shouldGetAlcool2() {
        CentroDistribuicao cd = new CentroDistribuicao(0, 0, 0, 1000);
        assertEquals(1000, cd.getAlcool2());
    }

    @Test
    public void shouldGetAditivo() {
        CentroDistribuicao cd = new CentroDistribuicao(150, 0, 0, 0);
        assertEquals(150, cd.getAditivo());
    }

}
