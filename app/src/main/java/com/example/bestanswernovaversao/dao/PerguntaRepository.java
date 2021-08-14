package com.example.bestanswernovaversao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.bestanswernovaversao.model.Pergunta;
import com.example.bestanswernovaversao.util.BDUtil;

import java.util.ArrayList;
import java.util.List;

public class PerguntaRepository {
    private BDUtil bdUtil;

    public PerguntaRepository(Context context) {
        bdUtil = new BDUtil(context);
    }

    public String insert(Pergunta pergunta) {
        ContentValues valores = new ContentValues();
        valores.put("TITULO", pergunta.getTitulo());
        valores.put("DESCRICAO", pergunta.getDescricao());
        valores.put("DATA", pergunta.getData());
        valores.put("CATEGORIA", pergunta.getCategoria());
        long resultado = bdUtil.getConexao().insert("PERGUNTA", null, valores);
        if (resultado == -1) {
            bdUtil.close();
            return "Erro ao inserir registro";
        }
        return "Sua pergunta foi publicada!";
    }

    public void update(Pergunta pergunta) {
        ContentValues valores = new ContentValues();
        valores.put("TITULO", pergunta.getTitulo());
        valores.put("DESCRICAO", pergunta.getDescricao());
        valores.put("DATA", pergunta.getData());
        valores.put("CATEGORIA", pergunta.getCategoria());
        bdUtil.getConexao().update("PERGUNTA", valores, "_id = ?", new
                String[]{Integer.toString(pergunta.get_id())});
    }

    public void delete(Integer _id) {
        bdUtil.getConexao().delete("PERGUNTA", "_id = ?", new
                String[]{Integer.toString(_id)});
    }

    public List<Pergunta> getAll() {
        List<Pergunta> perguntas = new ArrayList<>();
        // monta a consulta
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT _ID, TITULO, DESCRICAO, CATEGORIA, DATA ");
        stringBuilderQuery.append("FROM  PERGUNTA ");
        stringBuilderQuery.append("ORDER BY _ID DESC ");
        //consulta os registros que estão no BD
        Cursor cursor = bdUtil.getConexao().rawQuery(stringBuilderQuery.toString(), null);
        //aponta cursos para o primeiro registro
        cursor.moveToFirst();
        Pergunta pergunta = null;
        //Percorre os registros até atingir o fim da lista de registros
        while (!cursor.isAfterLast()) {
            // Cria objetos do tipo pergunta
            pergunta = new Pergunta();
            //adiciona os dados no objeto
            pergunta.set_id(cursor.getInt(cursor.getColumnIndex("_ID")));
            pergunta.setTitulo(cursor.getString(cursor.getColumnIndex("TITULO")));
            pergunta.setDescricao(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
            pergunta.setCategoria(cursor.getString(cursor.getColumnIndex("CATEGORIA")));
            pergunta.setData(cursor.getString(cursor.getColumnIndex("DATA")));
            //adiciona o objeto na lista
            perguntas.add(pergunta);
            //aponta para o próximo registro
            cursor.moveToNext();
        }
        bdUtil.close();
        //retorna a lista de objetos
        return perguntas;
    }
}
