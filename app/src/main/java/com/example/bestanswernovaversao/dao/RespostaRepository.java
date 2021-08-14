package com.example.bestanswernovaversao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.bestanswernovaversao.model.Resposta;
import com.example.bestanswernovaversao.util.BDUtil;

import java.util.ArrayList;
import java.util.List;

public class RespostaRepository {

    /*
        Escolhi implementar a funcionalidade de responder em vez da edição, já que para o meu aplicativo ela é mais importante.
     */

    private BDUtil bdUtil;

    public RespostaRepository(Context context) {
        bdUtil = new BDUtil(context);
    }

    public String insert(Resposta resposta) {
        ContentValues valores = new ContentValues();
        valores.put("ID_PERGUNTA", resposta.getIdPergunta());
        valores.put("CONTEUDO", resposta.getConteudo());
        valores.put("DATA", resposta.getData());
        long resultado = bdUtil.getConexao().insert("RESPOSTA", null, valores);
        if (resultado == -1) {
            bdUtil.close();
            return "Erro ao inserir registro";
        }
        return "Sua resposta foi publicada!";
    }

    public void deleteByPergunta(Integer idPergunta){
        bdUtil.getConexao().delete("RESPOSTA","ID_PERGUNTA = ?", new
                String[]{Integer.toString(idPergunta)});
    }

    public List<Resposta> findByPergunta(int idPergunta) {
        List<Resposta> respostas = new ArrayList<>();
        // monta a consulta
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append("SELECT _ID, ID_PERGUNTA, CONTEUDO, DATA ");
        stringBuilderQuery.append("FROM  RESPOSTA ");
        stringBuilderQuery.append("WHERE  ID_PERGUNTA = ? ");
        stringBuilderQuery.append("ORDER BY _ID ");
        //consulta os registros que estão no BD
        Cursor cursor = bdUtil.getConexao().rawQuery(stringBuilderQuery.toString(), new String[]{Integer.toString(idPergunta)});
        //aponta cursos para o primeiro registro
        cursor.moveToFirst();
        //Percorre os registros até atingir o fim da lista de registros
        while (!cursor.isAfterLast()) {
            // Cria objetos do tipo pergunta
            Resposta resposta = new Resposta();
            //adiciona os dados no objeto
            resposta.set_id(cursor.getInt(cursor.getColumnIndex("_ID")));
            resposta.setIdPergunta(cursor.getInt(cursor.getColumnIndex("ID_PERGUNTA")));
            resposta.setConteudo(cursor.getString(cursor.getColumnIndex("CONTEUDO")));
            resposta.setData(cursor.getString(cursor.getColumnIndex("DATA")));
            //adiciona o objeto na lista
            respostas.add(resposta);
            //aponta para o próximo registro
            cursor.moveToNext();
        }
        bdUtil.close();
        //retorna a lista de objetos
        return respostas;
    }
}
