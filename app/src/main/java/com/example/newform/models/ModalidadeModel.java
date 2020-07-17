package com.example.newform.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class ModalidadeModel implements Serializable  {

    public static final String
            TABELA_NOME = "tb_modalidades";

    public static final String
            COLUNA_MODALIDADE = "modalidade",
            COLUNA_ATIVO = "ativo",
            COLUNA_ID_SERVER = "id_server";

    public static final String
            CREATE_TABLE =
            " create table " + TABELA_NOME
                    + " ( "
                        + COLUNA_MODALIDADE + " varchar (100) not null primary key, "
                        + COLUNA_ATIVO + " integer not null default 1, "
                        + COLUNA_ID_SERVER + " integer "
                    + " ); ";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    /*===============================================

    ATRIBUTOS DA CLASSE MODALIDADE.

    =================================================*/

    public ModalidadeModel() {

    }

    public ModalidadeModel(Long idServer, String modalidade, Integer ativo) {
        this.idServer = idServer;
        this.modalidade = modalidade;
        this.ativo = ativo;
    }

    private Long idServer;
    private String modalidade;
    @SerializedName("id_usuario")
    private Long idUsuario;
    private Integer ativo;

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdServer() {
        return idServer;
    }

    public void setIdServer(Long idServer) {
        this.idServer = idServer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModalidadeModel that = (ModalidadeModel) o;
        return modalidade.equals(that.modalidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modalidade);
    }

    @Override
    public String toString() {
        return modalidade ;
    }
}
