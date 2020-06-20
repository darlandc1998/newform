package com.example.newform.models;

import java.util.Objects;

public class GraduacaoModel {

    public static final String
            TABELA_NOME = "tb_graduacoes";

    public static final String
            COLUNA_MODALIDADE = "modalidade",
            COLUNA_GRADUACAO = "graduacao",
            COLUNA_ATIVO = "ativo";


    public static final String
            CREATE_TABLE =
            " create table " + TABELA_NOME
                    + " ( "
                        + COLUNA_MODALIDADE + " varchar (100) not null, "
                        + COLUNA_GRADUACAO + " varchar (100) not null, "
                        + COLUNA_ATIVO + " integer not null default 1, "
                        + " foreign key (" + COLUNA_MODALIDADE + ") REFERENCES tb_modalidades ( " + COLUNA_MODALIDADE + "), "
                        + " primary key (" + COLUNA_GRADUACAO + ", " + COLUNA_MODALIDADE +" ) "
                    + ");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    /*===============================================

    ATRIBUTOS DA CLASSE GRADUACAO.

    =================================================*/

    public GraduacaoModel() {
    }

    public GraduacaoModel(String modalidade, String graduacao, Integer ativo) {
        this.modalidade = modalidade;
        this.graduacao = graduacao;
        this.ativo = ativo;
    }

    private String modalidade;
    private String graduacao;
    private Integer ativo;

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(String graduacao) {
        this.graduacao = graduacao;
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
        GraduacaoModel that = (GraduacaoModel) o;
        return graduacao.equals(that.graduacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graduacao);
    }

    @Override
    public String toString() {
        return graduacao ;
    }
}
