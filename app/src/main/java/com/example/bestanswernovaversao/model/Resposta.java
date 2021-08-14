package com.example.bestanswernovaversao.model;

public class Resposta {

    private int _id;
    private int idPergunta;
    private String conteudo;
    private String data;

    public Resposta(){}

    public Resposta(int _id, int idPergunta, String conteudo, String data) {
        this._id = _id;
        this.idPergunta = idPergunta;
        this.conteudo = conteudo;
        this.data = data;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
