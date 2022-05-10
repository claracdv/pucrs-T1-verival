package com.clarafelipe;

public class CentroDistribuicao {
    public enum SITUACAO {
        NORMAL, SOBRAVISO, EMERGENCIA
    }

    public enum TIPOPOSTO {
        COMUM, ESTRATEGICO
    }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private int tAditivo = 0;
    private int tGasolina = 0;
    private int tAlcool1 = 0;
    private int tAlcool2 = 0;

    private final int percAditivo = 5;
    private final int percGasolina = 70;
    private final int percAlcool = 25;

    private SITUACAO situacao = SITUACAO.NORMAL;

    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        if (tAditivo > MAX_ADITIVO)
            throw new IllegalArgumentException("Valor Inválido!");
        else if (tAditivo < 0)
            throw new IllegalArgumentException("Valor Inválido!");
        else
            this.tAditivo = tAditivo;

        if (tGasolina > MAX_GASOLINA)
            throw new IllegalArgumentException("Valor Inválido!");
        else if (tGasolina < 0)
            throw new IllegalArgumentException("Valor Inválido!");
        else
            this.tGasolina = tGasolina;

        if (tAlcool1 + tAlcool2 > MAX_ALCOOL)
            throw new IllegalArgumentException("Valor Inválido!");
        else if (tAlcool1 < 0 || tAlcool2 < 0)
            throw new IllegalArgumentException("Valor Inválido!");
        else if (tAlcool1 != tAlcool2)
            throw new IllegalArgumentException("Valor Inválido!");
        else {
            this.tAlcool1 = tAlcool1;
            this.tAlcool2 = tAlcool2;
        }

        this.defineSituacao();
    }

    public void defineSituacao() {
        if (this.getAditivo() < 0.25 * MAX_ADITIVO ||
                this.getGasolina() < 0.25 * MAX_GASOLINA ||
                this.getAlcool1() < 0.25 * MAX_ALCOOL / 2 ||
                this.getAlcool2() < 0.25 * MAX_ALCOOL / 2) {
            this.situacao = SITUACAO.EMERGENCIA;
        } else if (this.getAditivo() < 0.5 * MAX_ADITIVO ||
                this.getGasolina() < 0.5 * MAX_GASOLINA ||
                this.getAlcool1() < 0.5 * MAX_ALCOOL / 2 ||
                this.getAlcool2() < 0.5 * MAX_ALCOOL / 2) {
            this.situacao = SITUACAO.SOBRAVISO;
        } else {
            this.situacao = SITUACAO.NORMAL;
        }
    }

    public SITUACAO getSituacao() {
        return this.situacao;
    }

    public int getGasolina() {
        return this.tGasolina;
    }

    public void setGasolina(int subGasolina) {
        this.tGasolina -= subGasolina;
    }

    public void setAditivo(int subAditivo) {
        this.tAditivo -= subAditivo;
    }

    public void setAlcool1(int subAlcool) {
        this.tAlcool1 -= subAlcool;
    }

    public void setAlcool2(int subAlcool) {
        this.tAlcool2 -= subAlcool;
    }

    public int getAditivo() {
        return this.tAditivo;
    }

    public int getAlcool1() {
        return this.tAlcool1;
    }

    public int getAlcool2() {
        return this.tAlcool2;
    }

    public int recebeAditivo(int qtdade) {
        if (qtdade < 0)
            return -1;

        int espacoTanque = MAX_ADITIVO - this.getAditivo();

        if (espacoTanque == 0)
            return 0;
        if (qtdade >= espacoTanque) {
            this.tAditivo += espacoTanque;
            return (qtdade - espacoTanque);
        }

        this.tAditivo += qtdade;
        return qtdade;
    }

    public int recebeGasolina(int qtdade) {
        if (qtdade < 0)
            return -1;

        int espacoTanque = MAX_GASOLINA - this.getGasolina();

        if (espacoTanque == 0)
            return 0;
        if (qtdade >= espacoTanque) {
            this.tGasolina += espacoTanque;
            return (qtdade - espacoTanque);
        }

        this.tGasolina += qtdade;
        return qtdade;
    }

    public int recebeAlcool(int qtdade) {
        if (qtdade < 0)
            return -1;

        int espacoTanque = MAX_ALCOOL - this.getAlcool1() - this.getAlcool2();

        if (espacoTanque == 0)
            return 0;
        if (qtdade >= espacoTanque) {
            this.tAlcool1 += espacoTanque / 2;
            this.tAlcool2 += espacoTanque / 2;
            return (qtdade - espacoTanque);
        }

        this.tAlcool1 += qtdade / 2;
        this.tAlcool2 += qtdade / 2;
        return qtdade - (qtdade % 2);
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        if (qtdade < 0)
            return new int[] { -7, 0, 0, 0 };

        int qtdRequeridaAditivo = (int) (percAditivo * qtdade) / 100;
        int qtdRequeridaGasolina = (int) (percGasolina * qtdade) / 100;
        int qtdRequeridaAlcool = (int) (percAlcool * qtdade) / 100;

        if (qtdRequeridaAditivo > getAditivo() ||
                qtdRequeridaGasolina > getGasolina() ||
                qtdRequeridaAlcool > getAlcool1() + getAlcool2())
            return new int[] { -21, 0, 0, 0 };

        if (getSituacao() == SITUACAO.NORMAL) {
            setAditivo(qtdRequeridaAditivo);
            setGasolina(qtdRequeridaGasolina);
            setAlcool1((int) qtdRequeridaAlcool / 2);
            setAlcool2((int) qtdRequeridaAlcool / 2);
            return new int[] { qtdRequeridaAditivo, qtdRequeridaGasolina,
                    (int) (qtdRequeridaAlcool / 2), (int) (qtdRequeridaAlcool / 2) };
        }

        if (getSituacao() == SITUACAO.SOBRAVISO) {
            if (tipoPosto == TIPOPOSTO.COMUM) {
                setAditivo((int) 0.5 * qtdRequeridaAditivo);
                setGasolina((int) 0.5 * qtdRequeridaGasolina);
                setAlcool1((int) 0.5 * qtdRequeridaAlcool / 2);
                setAlcool2((int) 0.5 * qtdRequeridaAlcool / 2);

                return new int[] { (int) 0.5 * qtdRequeridaAditivo, (int) 0.5 * qtdRequeridaGasolina,
                        (int) (0.5 * qtdRequeridaAlcool / 2), (int) (0.5 * qtdRequeridaAlcool / 2) };
            }

            if (tipoPosto == TIPOPOSTO.ESTRATEGICO) {
                setAditivo(qtdRequeridaAditivo);
                setGasolina(qtdRequeridaGasolina);
                setAlcool1((int) qtdRequeridaAlcool / 2);
                setAlcool2((int) qtdRequeridaAlcool / 2);
                return new int[] { qtdRequeridaAditivo, qtdRequeridaGasolina,
                        (int) (qtdRequeridaAlcool / 2), (int) (qtdRequeridaAlcool / 2) };
            }
        }

        if (getSituacao() == SITUACAO.EMERGENCIA) {
            if (tipoPosto == TIPOPOSTO.COMUM) {
                return new int[] { -14, 0, 0, 0 };
            }

            if (tipoPosto == TIPOPOSTO.ESTRATEGICO) {
                setAditivo((int) 0.5 * qtdRequeridaAditivo);
                setGasolina((int) 0.5 * qtdRequeridaGasolina);
                setAlcool1((int) 0.5 * qtdRequeridaAlcool / 2);
                setAlcool2((int) 0.5 * qtdRequeridaAlcool / 2);

                return new int[] { (int) 0.5 * qtdRequeridaAditivo, (int) 0.5 * qtdRequeridaGasolina,
                        (int) (0.5 * qtdRequeridaAlcool / 2), (int) (0.5 * qtdRequeridaAlcool / 2) };
            }
        }

        return new int[] { 0 };
    }
}
