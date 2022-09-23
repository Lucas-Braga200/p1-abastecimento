package br.unigran.p1_abastecimento;

import java.util.Date;

public class Abastecimento {
    Float quilometragemAtual;
    Float quantidadeAbastecida;
    Date dia;
    Float valor;

    public Float getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public void setQuilometragemAtual(Float quilometragemAtual) {
        this.quilometragemAtual = quilometragemAtual;
    }

    public Float getQuantidadeAbastecida() {
        return quantidadeAbastecida;
    }

    public void setQuantidadeAbastecida(Float quantidadeAbastecida) {
        this.quantidadeAbastecida = quantidadeAbastecida;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
