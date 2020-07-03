package com.example.newform.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AlunoModel implements Serializable {

    public static final String
            TABELA_NOME = "tb_alunos";

    public static final String
            COLUNA_ID = "_id",
            COLUNA_ALUNO = "aluno",
            COLUNA_DATA_NASCIMENTO = "data_nascimento",
            COLUNA_SEXO = "sexo",
            COLUNA_TELEFONE = "telefone",
            COLUNA_CELULAR = "celular",
            COLUNA_EMAIL = "email",
            COLUNA_OBSERVACAO = "observacao",
            COLUNA_ENDERECO = "endereco",
            COLUNA_NUMERO = "numero",
            COLUNA_COMPLEMENTO = "complemento",
            COLUNA_BAIRRO = "bairro",
            COLUNA_CIDADE = "cidade",
            COLUNA_ESTADO = "estado",
            COLUNA_PAIS = "pais",
            COLUNA_CEP = "cep",
            COLUNA_ATIVO = "ativo";

    public static final String
            CREATE_TABLE =
            " create table " + TABELA_NOME
                    + " ( "
                            + COLUNA_ID + " integer not null primary key autoincrement, "
                            + COLUNA_ALUNO + " varchar (100) not null, "
                            + COLUNA_DATA_NASCIMENTO + " date, "
                            + COLUNA_SEXO + " varchar(1),"
                            + COLUNA_TELEFONE + " varchar(45), "
                            + COLUNA_CELULAR + " varchar(45), "
                            + COLUNA_EMAIL + " varchar(45), "
                            + COLUNA_OBSERVACAO + " varchar(100), "
                            + COLUNA_ENDERECO + " varchar(100), "
                            + COLUNA_NUMERO + " varchar(45), "
                            + COLUNA_COMPLEMENTO + " varchar(45), "
                            + COLUNA_BAIRRO + " varchar(45), "
                            + COLUNA_CIDADE + " varchar(45), "
                            + COLUNA_ESTADO + " varchar(2), "
                            + COLUNA_PAIS + " varchar(45), "
                            + COLUNA_CEP + " varchar(45), "
                            + COLUNA_ATIVO + " integer not null default 1, "
                            + " foreign key ("+ COLUNA_CIDADE + "," + COLUNA_ESTADO + ", " + COLUNA_PAIS + ") REFERENCES tb_cidades (" + COLUNA_CIDADE + ", " + COLUNA_ESTADO + ", "+ COLUNA_PAIS + ") "
                    + ");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABELA_NOME;

    /*===============================================

    ATRIBUTOS DA CLASSE ALUNO.

    =================================================*/

    @SerializedName("Id")
    private Long id;
    private String aluno;
    @SerializedName("data_nascimento")
    private Date dtNascimento;
    private String sexo;
    private String telefone;
    private String celular;
    private String email;
    private String observacao;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    private Integer ativo;
    @SerializedName("id_usuario")
    private Long idUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoModel that = (AlunoModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - " + aluno;
    }
}
