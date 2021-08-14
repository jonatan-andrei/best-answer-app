package com.example.bestanswernovaversao.model;

public class Pergunta {

    private int _id;
    private String titulo;
    private String descricao;
    private String categoria;
    private String data;

    public Pergunta(int _id, String titulo, String descricao, String categoria, String data) {
        this._id = _id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.data = data;
    }

    public Pergunta() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
