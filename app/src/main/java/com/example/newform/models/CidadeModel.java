package com.example.newform.models;

import java.util.Objects;

public class CidadeModel {

    public static final String
    TABELA_NOME = "tb_cidades";

    public static final String
    COLUNA_CIDADE = "cidade",
    COLUNA_ESTADO = "estado",
    COLUNA_PAIS = "pais";

    public static final String
    CREATE_TABLE =
            " create table " + TABELA_NOME
            + " ( "
                   + COLUNA_CIDADE + " varchar (100) not null, "
                   + COLUNA_ESTADO + " varchar (2) not null, "
                   + COLUNA_PAIS + " text not null, "
                   + " primary key (" + COLUNA_CIDADE + "," + COLUNA_ESTADO + "," + COLUNA_PAIS + ") "
            + " );";

    public static final String
    DROP_TABLE = "drop table if exists " + TABELA_NOME;

    /*===============================================

    ATRIBUTOS DA CLASSE CIDADE.

    =================================================*/

    public CidadeModel() {

    }

    public CidadeModel(String cidade, String estado, String pais) {
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    private String cidade;
    private String estado;
    private String pais;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CidadeModel that = (CidadeModel) o;
        return cidade.equals(that.cidade) &&
                estado.equals(that.estado) &&
                pais.equals(that.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cidade, estado, pais);
    }

    @Override
    public String toString() {
        return  cidade + " - " + estado + " - " + pais;
    }
}
