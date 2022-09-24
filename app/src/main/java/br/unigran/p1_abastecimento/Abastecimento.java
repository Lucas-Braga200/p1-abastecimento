package br.unigran.p1_abastecimento;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Abastecimento {
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return formatter.format(dia) + " " + quilometragemAtual + " - " + quantidadeAbastecida;
        } catch (Exception e) {
            System.out.println(e);
            return dia + " " + quilometragemAtual + " - " + quantidadeAbastecida;
        }
    }
}
