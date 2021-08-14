package com.example.bestanswernovaversao.util;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDUtil extends SQLiteOpenHelper {

    private static final String BASE_DE_DADOS = "BESTANSWERNOVAVERSAO";
    private static final int VERSAO = 1;

    public BDUtil(Context context) {
        super(context, BASE_DE_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder criarTabelaPergunta = new StringBuilder();
        criarTabelaPergunta.append(" CREATE TABLE PERGUNTA (");
        criarTabelaPergunta.append(" _ID   INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabelaPergunta.append(" TITULO  TEXT    NOT NULL,");
        criarTabelaPergunta.append(" DESCRICAO   TEXT    NOT NULL,");
        criarTabelaPergunta.append(" CATEGORIA   TEXT    NOT NULL,");
        criarTabelaPergunta.append(" DATA TEXT    NOT NULL)");
        db.execSQL(criarTabelaPergunta.toString());

        StringBuilder criarTabelaResposta = new StringBuilder();
        criarTabelaResposta.append(" CREATE TABLE RESPOSTA (");
        criarTabelaResposta.append(" _ID   INTEGER PRIMARY KEY AUTOINCREMENT, ");
        criarTabelaResposta.append(" ID_PERGUNTA  INTEGER    NOT NULL,");
        criarTabelaResposta.append(" CONTEUDO   TEXT    NOT NULL,");
        criarTabelaResposta.append(" DATA TEXT    NOT NULL)");
        db.execSQL(criarTabelaResposta.toString());
    }

    /*Método abaixo é executado quando troca a versão do BD*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS RESPOSTA");
        db.execSQL("DROP TABLE IF EXISTS PERGUNTA");
        onCreate(db);

    }

    /*Método usado para obter a conexão com o BD*/
    public SQLiteDatabase getConexao() {
        return this.getWritableDatabase();
    }


}