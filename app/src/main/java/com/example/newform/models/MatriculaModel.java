package com.example.newform.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

public class MatriculaModel {

    public static final String
            TABELA_NOME = "tb_matriculas";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_ID_ALUNO = "id_aluno",
            COLUNA_DATA_MATRICULA = "data_matricula",
            COLUNA_DIA_VENCIMENTO = "dia_vencimento",
            COLUNA_DATA_ENCERRAMENTO = "data_encerramento",
            COLUNA_ATIVO = "ativo",
            COLUNA_ID_SERVER = "id_server";


    public static final String
            CREATE_TABLE =
            " create table " + TABELA_NOME
                    + " ( "
                        + COLUNA_ID + " integer not null primary key autoincrement, "
                        + COLUNA_ID_ALUNO + " integer, "
                        + COLUNA_DATA_MATRICULA + " date not null, "
                        + COLUNA_DIA_VENCIMENTO + " integer not null, "
                        + COLUNA_DATA_ENCERRAMENTO + " date, "
                        + COLUNA_ATIVO + " integer not null default 1, "
                        + COLUNA_ID_SERVER + " integer, "
                        + " foreign key (" + COLUNA_ID_ALUNO + ") REFERENCES tb_alunos ( " + COLUNA_ID + ") "
                    + ");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    public MatriculaModel() {

    }

    public MatriculaModel(Long id, Long idAluno, Date dtMatricula, Integer diaVencimento, Date dtEncerramento) {
        this.id = id;
        this.idAluno = idAluno;
        this.dtMatricula = dtMatricula;
        this.diaVencimento = diaVencimento;
        this.dtEncerramento = dtEncerramento;
    }

    /*===============================================

    ATRIBUTOS DA CLASSE MATRICULAS.

    =================================================*/

    private Long id;
    private Long idServer;
    @SerializedName("id_aluno")
    private Long idAluno;
    private Date dtMatricula;
    @SerializedName("dia_vencimento")
    private Integer diaVencimento;
    private Date dtEncerramento;
    private Integer ativo;
    @SerializedName("id_usuario")
    private Long idUsuario;

    /*Variaveis auxiliares*/
    private String aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Date getDtMatricula() {
        return dtMatricula;
    }

    public void setDtMatricula(Date dtMatricula) {
        this.dtMatricula = dtMatricula;
    }

    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Date getDtEncerramento() {
        return dtEncerramento;
    }

    public void setDtEncerramento(Date dtEncerramento) {
        this.dtEncerramento = dtEncerramento;
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

    @SerializedName("data_matricula")
    public Long getDtMatriculaServer(){
        return dtMatricula != null ? dtMatricula.getTime() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatriculaModel that = (MatriculaModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return  id + " - " + aluno;
    }
}
