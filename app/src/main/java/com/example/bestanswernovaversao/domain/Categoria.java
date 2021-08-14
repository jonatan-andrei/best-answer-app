package com.example.bestanswernovaversao.domain;

public enum Categoria {

    CONSELHOS("Conselhos", 0),
    CULTURA("Cultura", 1),
    CURIOSIDADES("Curiosidades", 2),
    EDUCACAO("Educação", 3),
    ENTRETENIMENTO("Entretenimento", 4),
    ESPORTES("Esportes", 5),
    RELIGIAO("Religião", 6),
    SAUDE("Saúde", 7),
    TECNOLOGIA("Tecnologia", 8);

    private String nome;

    private int indice;

    private Categoria(String nome, int indice) {
        this.nome = nome;
        this.indice = indice;
    }

    public static int getIndiceByNome(String nome) {
        for (Categoria categoria : values()) {
            if (categoria.nome.equals(nome)) {
                return categoria.indice;
            }
        }
        return 0;
    }

}
