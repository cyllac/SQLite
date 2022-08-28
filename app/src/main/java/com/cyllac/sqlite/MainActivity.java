package com.cyllac.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

        //tabela
        bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade INT(3) ) ");

        //Inserir dados
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Marcos', 30)");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Ana', 20)");

        Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas ", null);

        int indiceColunaNome = cursor.getColumnIndex("nome");
        int indiceColunaIdade = cursor.getColumnIndex("idade");

        cursor.moveToFirst();
        while ( cursor.getPosition() <= cursor.getCount() ) {
            Log.i("RESULTADO - NOME: ", cursor.getString(indiceColunaNome));
            Log.i("RESULTADO - IDADE: ", cursor.getString(indiceColunaIdade));
            cursor.moveToNext();
        }
    }
}