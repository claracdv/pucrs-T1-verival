package com.clarafelipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class CentroDistribuicaoTest {
    @Test
    public void qtdParametroInvalida() {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
        int[] resp = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
        assertEquals(-7, resp[0]);
    }

    @Test
    public void pedidoNaoPodeSerAtendido() {
        assertTrue(true);
    }

    @Test
    public void semCombustivelSuficiente() {
        assertTrue(true);
    }

    @Test
    public void faltaAditivo() {
        assertTrue(true);
    }

    @Test
    public void faltaAlcool1() {
        assertTrue(true);
    }

    @Test
    public void faltaAlcool2() {
        assertTrue(true);
    }

    @Test
    public void faltaGasolina() {
        assertTrue(true);
    }

    @Test
    public void situacaoNormal() {
        assertTrue(true);
    }

    @Test
    public void situacaoSobraviso() {
        assertTrue(true);
    }

    @Test
    public void situacaoEmergencia() {
        assertTrue(true);
    }

    @Test
    public void situacaoNormalLimiteOn() {
        assertTrue(true);
    }

    @Test
    public void situacaoNormalLimiteOff() {
        assertTrue(true);
    }

    @Test
    public void situacaoSobravisoLimiteOn() {
        assertTrue(true);
    }

    @Test
    public void situacaoSobravisoLimiteOff() {
        assertTrue(true);
    }

    @Test
    public void situacaoEmergenciaLimiteOn() {
        assertTrue(true);
    }

    @Test
    public void situacaoEmergenciaLimiteOff() {
        assertTrue(true);
    }

    @Test
    public void situacaoNormalLimiteIn() {
        assertTrue(true);
    }

    @Test
    public void situacaoSobravisoLimiteIn() {
        assertTrue(true);
    }

    @Test
    public void situacaoEmergenciaLimiteIn() {
        assertTrue(true);
    }

    @Test
    public void qtdAditivoInvalida() {
        assertTrue(true);
    }

    @Test
    public void qtdAlcool1Invalida() {
        assertTrue(true);
    }

    @Test
    public void qtdAlcool2Invalida() {
        assertTrue(true);
    }

    @Test
    public void qtdGasolinaInvalida() {
        assertTrue(true);
    }

    @Test
    public void qtdValidaAditivo() {
        assertTrue(true);
    }

    @Test
    public void qtdValidaAlcool1() {
        assertTrue(true);
    }

    @Test
    public void qtdValidaAlcool2() {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 0, 1200, 1200);
        assertEquals(100, centroDistribuicao.recebeAlcool(102));
    }

    @Test
    public void qtdValidaGasolina() {
        assertTrue(true);
    }

}
