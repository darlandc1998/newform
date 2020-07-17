package com.example.newform.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

public class MatriculaModalidadeModel {

    public static final String
            TABELA_NOME = "tb_matricula_modalidades";

    public static final String
            COLUNA_ID_MATRICULA = "id_matricula",
            COLUNA_MODALIDADE = "modalidade",
            COLUNA_GRADUACAO = "graduacao",
            COLUNA_DATA_INICIO = "data_inicio",
            COLUNA_DATA_FIM = "data_fim",
            COLUNA_PLANO = "plano",
            COLUNA_ATIVO = "ativo",
            COLUNA_ID_SERVER = "id_server";

    public static final String
            CREATE_TABLE =
            " create table " + TABELA_NOME
                    + " ( "
                            + COLUNA_ID_MATRICULA + " integer not null, "
                            + COLUNA_MODALIDADE + " varchar(100) not null, "
                            + COLUNA_GRADUACAO + " varchar(100) not null, "
                            + COLUNA_DATA_INICIO + " date, "
                            + COLUNA_DATA_FIM + " date, "
                            + COLUNA_PLANO + " varchar(100) not null, "
                            + COLUNA_ATIVO + " integer not null default 1, "
                            + COLUNA_ID_SERVER + " integer, "
                            + " foreign key (" + COLUNA_ID_MATRICULA + ") REFERENCES tb_matriculas ( " + MatriculaModel.COLUNA_ID + "), "
                            + " foreign key (" + COLUNA_MODALIDADE + ") REFERENCES tb_modalidades ( " + COLUNA_MODALIDADE + "), "
                            + " foreign key (" + COLUNA_MODALIDADE + "," + COLUNA_GRADUACAO + ") REFERENCES tb_graduacoes (" + COLUNA_MODALIDADE + ", " + COLUNA_GRADUACAO + "), "
                            + " foreign key (" + COLUNA_MODALIDADE +", " + COLUNA_PLANO + ") REFERENCES tb_planos (" + COLUNA_MODALIDADE + ", " + COLUNA_PLANO + "), "
                            + " primary key (" + COLUNA_ID_MATRICULA + ","+ COLUNA_MODALIDADE + "," + COLUNA_GRADUACAO + "," + COLUNA_PLANO + ") "
                    + " );";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    public MatriculaModalidadeModel() {

    }

    /*===============================================

    ATRIBUTOS DA CLASSE CIDADE.

    =================================================*/

    private Long idServer;
    @SerializedName("id_matricula")
    private Long idMatricula;
    @SerializedName("id_modalidade")
    private String modalidade;
    @SerializedName("id_graduacao")
    private String graduacao;
    @SerializedName("data_inicio")
    private Date dtInicio;
    @SerializedName("data_fim")
    private Date dtFim;
    @SerializedName("id_plano")
    private String plano;
    private Integer ativo;
    @SerializedName("id_usuario")
    private Long idUsuario;

    //Variaveis auxiliares
    private Double valorMensalPlano;

    public Long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Long idMatricula) {
        this.idMatricula = idMatricula;
    }

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

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    public Double getValorMensalPlano() {
        return valorMensalPlano;
    }

    public void setValorMensalPlano(Double valorMensalPlano) {
        this.valorMensalPlano = valorMensalPlano;
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
        MatriculaModalidadeModel that = (MatriculaModalidadeModel) o;
        return modalidade.equals(that.modalidade) &&
                graduacao.equals(that.graduacao) &&
                plano.equals(that.plano);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modalidade, graduacao, plano);
    }

    @Override
    public String toString() {
        return "MatriculaModalidadeModel{" +
                "idMatricula=" + idMatricula +
                ", modalidade='" + modalidade + '\'' +
                ", graduacao='" + graduacao + '\'' +
                ", dtInicio=" + dtInicio +
                ", dtFim=" + dtFim +
                ", plano='" + plano + '\'' +
                ", ativo=" + ativo +
                ", valorMensalPlano=" + valorMensalPlano +
                '}';
    }
}
