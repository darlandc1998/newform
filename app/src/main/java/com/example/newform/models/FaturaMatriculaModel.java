package com.example.newform.models;

import java.util.Date;
import java.util.Objects;

public class FaturaMatriculaModel {

    public static final String
            TABELA_NOME = "tb_fatura_matriculas";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_MATRICULA = "id_matricula",
            COLUNA_DATA_VENCIMENTO = "data_vencimento",
            COLUNA_VALOR = "valor",
            COLUNA_DATA_PAGAMENTO = "data_pagamento",
            COLUNA_DATA_CANCELAMENTO = "data_cancelamento",
            COLUNA_ATIVO = "ativo";

    public static final String
            CREATE_TABLE =
            " create table " + TABELA_NOME
                    + " ( "
                        + COLUNA_ID_MATRICULA + " integer not null, "
                        + COLUNA_DATA_VENCIMENTO + " date not null, "
                        + COLUNA_VALOR + " decimal(10,2) not null, "
                        + COLUNA_DATA_PAGAMENTO + " date, "
                        + COLUNA_DATA_CANCELAMENTO + " date, "
                        + COLUNA_ATIVO + " integer not null default 1, "
                        + " foreign key (" + COLUNA_ID_MATRICULA + ") REFERENCES tb_matriculas ( " + COLUNA_ID + "), "
                        + " primary key (" + COLUNA_DATA_VENCIMENTO + "," + COLUNA_ID_MATRICULA + ") "
                    + " ); ";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    public FaturaMatriculaModel() {

    }

    public FaturaMatriculaModel(Long idMatricula, Date dtVencimento, Double valor, Date dtPagamento, Date dtCancelamento) {
        this.idMatricula = idMatricula;
        this.dtVencimento = dtVencimento;
        this.valor = valor;
        this.dtPagamento = dtPagamento;
        this.dtCancelamento = dtCancelamento;
    }

    /*===============================================

    ATRIBUTOS DA CLASSE CIDADE.

    =================================================*/

    private Long idMatricula;
    private Date dtVencimento;
    private Double valor;
    private Date dtPagamento;
    private Date dtCancelamento;
    private Integer ativo;

    //Variaveis auxiliares
    private String aluno;

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public Date getDtCancelamento() {
        return dtCancelamento;
    }

    public void setDtCancelamento(Date dtCancelamento) {
        this.dtCancelamento = dtCancelamento;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FaturaMatriculaModel that = (FaturaMatriculaModel) o;
        return idMatricula.equals(that.idMatricula) &&
                dtVencimento.equals(that.dtVencimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatricula, dtVencimento);
    }

    @Override
    public String toString() {
        return "FaturaMatriculaModel{" +
                "idMatricula=" + idMatricula +
                ", dtVencimento=" + dtVencimento +
                ", valor=" + valor +
                ", dtPagamento=" + dtPagamento +
                ", dtCancelamento=" + dtCancelamento +
                '}';
    }
}
