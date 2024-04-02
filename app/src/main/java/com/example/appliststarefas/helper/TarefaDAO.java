package com.example.appliststarefas.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appliststarefas.activity.Tarefas;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements iTarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        DBhelper db = new DBhelper(context);
        escreve = db.getWritableDatabase();
        ler = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(Tarefas tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());


        try{
            escreve.insert(DBhelper.NOME_TABELA, null, cv);
            Log.i("INFO", "TAREFA SALVA COM SUCESSO");
        } catch (Exception e){
            Log.e("INFO", "ERRO AO SALVAR TAREFA" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefas tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());


        try{
            String[] args = {tarefa.getId().toString()};
            escreve.update(DBhelper.NOME_TABELA, cv, "id=?", args);
            Log.i("INFO", "TAREFA ATUALIZADA COM SUCESSO");
        } catch (Exception e){
            Log.e("INFO", "ERRO AO ATUALIZAR TAREFA" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Tarefas tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());


        try{
            String[] args = {tarefa.getId().toString()};
            escreve.delete(DBhelper.NOME_TABELA,"id=?", args);
            Log.i("INFO", "TAREFA DELETADA COM SUCESSO");
        } catch (Exception e){
            Log.e("INFO", "ERRO AO DELETAR TAREFA" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefas> listar() {

        List<Tarefas> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DBhelper.NOME_TABELA + " ;";
        Cursor c = ler.rawQuery(sql, null);

        while (c.moveToNext()){
            Tarefas tarefa1 = new Tarefas();

            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String nomeT = c.getString(c.getColumnIndex("nome"));


            tarefa1.setId(id);
            tarefa1.setNomeTarefa(nomeT);

            tarefas.add(tarefa1);
        }

        return tarefas;
    }
}
