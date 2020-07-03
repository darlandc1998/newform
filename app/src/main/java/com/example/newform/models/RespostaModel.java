package com.example.newform.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RespostaModel implements Serializable {

    @SerializedName("Mensagem")
    private String mensagem;
    @SerializedName("Sucesso")
    private Boolean sucesso;
    private Long codigo;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "RespostaModel{" +
                "mensagem='" + mensagem + '\'' +
                ", sucesso=" + sucesso +
                ", codigo=" + codigo +
                '}';
    }
}
