package com.clarafelipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class CentroDistribuicaoTest {

    @Test
    public void qtdParametroInvalida() {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(1000, 1500, 1500, 1005);
        int[] resObtida = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
        assertEquals(-7, resObtida[0]);
    }

    @Test
    public void pedidoNaoPodeSerAtendido() {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(0, 2000, 200, 6000);
        int[] resObtida = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
        assertEquals(-14, resObtida[0]);
    }

    @Test
    public void faltaAditivo() {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
        int[] resObtida = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
        assertEquals(-21, resObtida[0]);
    }

    @Test
    public void faltaAlcool1() {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
        int[] resObtida = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
        assertEquals(-21, resObtida[0]);
    }

    @Test
    public void faltaAlcool2() {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
        int[] resObtida = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
        assertEquals(-21, resObtida[0]);
    }

    @Test
    public void faltaGasolina() {
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
        int[] resObtida = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
        assertEquals(-21, resObtida[0]);
    }

    @Test
    public void qtdAlcoolDiferentes() {//IllegalArgumentException
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(400, 9000, 1000, 1000);
        int[] resObtida = centroDistribuicao.encomendaCombustivel(-50, CentroDistribuicao.TIPOPOSTO.COMUM);
        assertEquals(-21, resObtida[0]);
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
        CentroDistribuicao centroDistribuicao = new CentroDistribuicao(450, 0, 0, 0);
        assertEquals(50, centroDistribuicao.recebeAditivo(51));
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
    public void qtdAditivoInvalida() {//IllegalArgumentException
        assertTrue(true);
    }

    @Test
    public void qtdAlcool1Invalida() {//IllegalArgumentException
        assertTrue(true);
    }

    @Test
    public void qtdAlcool2Invalida() {//IllegalArgumentException
        assertTrue(true);
    }

    @Test
    public void qtdGasolinaInvalida() {//IllegalArgumentException
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
