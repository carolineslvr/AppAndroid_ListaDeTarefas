package com.example.appliststarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";

    public static String NOME_TABELA = "tarefas";


    public DBhelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL); ";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "SUCESSO AO CRIAR TABELA");

        }catch (Exception e){

            Log.i("INFO DB", "ERRO AO CRIAR TABELA " + e.getMessage());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
