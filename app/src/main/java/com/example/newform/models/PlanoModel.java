package com.example.newform.models;

import java.io.Serializable;
import java.util.Objects;

public class PlanoModel implements Serializable  {

    public static final String
            TABELA_NOME = "tb_planos";

    public static final String
            COLUNA_MODALIDADE = "modalidade",
            COLUNA_PLANO = "plano",
            COLUNA_VALOR_MENSAL = "valor_mensal",
            COLUNA_ATIVO = "ativo";

    public static final String
            CREATE_TABLE =
            " create table " + TABELA_NOME
                    + " ( "
                            + COLUNA_MODALIDADE + " varchar (100) not null, "
                            + COLUNA_PLANO + " varchar (100) not null, "
                            + COLUNA_VALOR_MENSAL + " decimal(10,2) not null, "
                            + COLUNA_ATIVO + " integer not null default 1, "
                            + " foreign key (" + COLUNA_MODALIDADE + ") REFERENCES tb_modalidades (" + COLUNA_MODALIDADE + "), "
                            + " primary key (" + COLUNA_PLANO + ", " + COLUNA_VALOR_MENSAL + " ) "
                    + " );";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    /*===============================================

    ATRIBUTOS DA CLASSE PLANO.

    =================================================*/


    public PlanoModel() {
    }

    private String modalidade;
    private String plano;
    private Double valorMensal;
    private Integer ativo;

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public Double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(Double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanoModel that = (PlanoModel) o;
        return plano.equals(that.plano) &&
                valorMensal.equals(that.valorMensal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plano, valorMensal);
    }

    @Override
    public String toString() {
        return plano ;
    }
}
