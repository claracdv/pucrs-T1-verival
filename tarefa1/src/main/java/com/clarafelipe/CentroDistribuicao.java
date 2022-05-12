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

  public int tGasolina;
  public int tAditivo;
  public int tAlcool1;
  public int tAlcool2;
  public SITUACAO situacao;

  public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
    if (tAditivo > MAX_ADITIVO)
      throw new IllegalArgumentException("INVALIDO");
    else if (tAditivo < 0)
      throw new IllegalArgumentException("INVALIDO");
    else
      this.tAditivo = tAditivo;

    if (tGasolina > MAX_GASOLINA)
      throw new IllegalArgumentException("INVALIDO");
    else if (tGasolina < 0)
      throw new IllegalArgumentException("INVALIDO");
    else
      this.tGasolina = tGasolina;

    if (tAlcool1 + tAlcool2 > MAX_ALCOOL)
      throw new IllegalArgumentException("INVALIDO");
    else if (tAlcool1 < 0 || tAlcool2 < 0)
      throw new IllegalArgumentException("INVALIDO");
    else if (tAlcool1 != tAlcool2)
      throw new IllegalArgumentException("INVALIDO");
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

  public int getAditivo() {
    return this.tAditivo;
  }

  public int getAlcool1() {
    return this.tAlcool1;
  }

  public int getAlcool2() {
    return this.tAlcool2;
  }

  public int recebeAditivo(int qtd) {
    if (qtd <= 0) {
      return -1;
    }
    int vazio = MAX_ADITIVO - this.tAditivo;
    if (qtd <= vazio) {
      return qtd;
    } else {
      return vazio;
    }
  }

  public int recebeGasolina(int qtd) {
    if (qtd <= 0) {
      return -1;
    }
    int vazio = MAX_GASOLINA - this.tGasolina;
    if (qtd <= vazio) {
      return qtd;
    } else {
      return vazio;
    }
  }

  public int recebeAlcool(int qtd) {
    if (qtd <= 0) {
      return -1;
    }
    int vazio = MAX_ALCOOL - (this.tAlcool1 + this.tAlcool2);
    if (qtd <= vazio) {
      return qtd;
    } else {
      return vazio;
    }
  }

  public int[] encomendaCombustivel(int qtd, TIPOPOSTO tipoPosto) {
    if (qtd <= 0 || (tipoPosto != TIPOPOSTO.COMUM && tipoPosto != TIPOPOSTO.ESTRATEGICO)) {
      return new int[] { -7 };
    }
    if (this.situacao == SITUACAO.EMERGENCIA && tipoPosto == TIPOPOSTO.COMUM) {
      return new int[] { -14 };
    }

    if (this.situacao == SITUACAO.SOBRAVISO && tipoPosto == TIPOPOSTO.COMUM) {
      return calculaCombustivel((int) (qtd * 0.5));
    }
    if (this.situacao == SITUACAO.EMERGENCIA && tipoPosto == TIPOPOSTO.ESTRATEGICO) {
      return calculaCombustivelEstrategico(qtd);
    }

    return calculaCombustivel(qtd);
  }

  private int[] calculaCombustivel(int qtd) {
    double qtdGasolina = qtd * 0.7;
    double qtdAlcool = qtd * 0.25;
    double qtdAditivo = qtd * 0.05;
    if ((int) qtdAditivo > this.tAditivo || (int) qtdAlcool > (this.tAlcool1 + this.tAlcool2)
        || (int) qtdGasolina > this.tGasolina) {
      return new int[] { -21 };
    } else {
      this.tGasolina = this.tGasolina - (int) qtdGasolina;
      this.tAditivo = this.tAditivo - (int) qtdAditivo;

      // se der pra dividir igual, tira igual, senão, tira tudo do que menos tem e o
      // resto do que mais tem
      double alcoolDivided = qtdAlcool / 2.0;
      if ((int) alcoolDivided > this.tAlcool1) {
        this.tAlcool2 = this.tAlcool2 - ((int) qtdAlcool - this.tAlcool1);
      } else if ((int) alcoolDivided > this.tAlcool2) {
        this.tAlcool1 = this.tAlcool1 - ((int) qtdAlcool - this.tAlcool2);
      } else {
        this.tAlcool1 = this.tAlcool1 - (int) alcoolDivided;
        this.tAlcool2 = this.tAlcool2 - (int) alcoolDivided;
      }
      return new int[] { this.tAditivo, this.tGasolina, this.tAlcool1, this.tAlcool2 };
    }
  }

  private int[] calculaCombustivelEstrategico(int qtd) {
    double qtdGasolina = qtd * 0.7;
    double qtdAlcool = qtd * 0.25;
    double qtdAditivo = qtd * 0.05;
    boolean usaAditivo = true;

    if ((int) qtdAditivo > this.tAditivo) {
      qtdGasolina = qtd * 0.75;
      qtdAlcool = qtd * 0.25;
      usaAditivo = false;
    }

    if ((int) qtdAlcool > (this.tAlcool1 + this.tAlcool2) || (int) qtdGasolina > this.tGasolina) {
      return new int[] { -21 };
    } else {
      if (usaAditivo)
        this.tAditivo = this.tAditivo - (int) qtdAditivo;
      this.tGasolina = this.tGasolina - (int) qtdGasolina;

      // se der pra dividir igual, tira igual, senão, tira tudo do que menos tem e o
      // resto do que mais tem
      double alcoolDivided = qtdAlcool / 2.0;
      if ((int) alcoolDivided > this.tAlcool1) {
        this.tAlcool2 = this.tAlcool2 - ((int) qtdAlcool - this.tAlcool1);
      } else if ((int) alcoolDivided > this.tAlcool2) {
        this.tAlcool1 = this.tAlcool1 - ((int) qtdAlcool - this.tAlcool2);
      } else {
        this.tAlcool1 = this.tAlcool1 - (int) alcoolDivided;
        this.tAlcool2 = this.tAlcool2 - (int) alcoolDivided;
      }
      return new int[] { this.tAditivo, this.tGasolina, this.tAlcool1, this.tAlcool2 };
    }
  }
}