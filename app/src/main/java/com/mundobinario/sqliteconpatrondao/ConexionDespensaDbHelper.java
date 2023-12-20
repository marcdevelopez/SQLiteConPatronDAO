package com.mundobinario.sqliteconpatrondao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mundobinario.sqliteconpatrondao.modelo.esquema.DespensaContract;

// si marca error aqui en public class, intentar renombrar Clase y luego volver a poner
// el mismo nombre, se va el error... error del ide!
public class ConexionDespensaDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public ConexionDespensaDbHelper(Context context) {
        super(context, DespensaContract.alimentos.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DespensaContract.alimentos.SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DespensaContract.alimentos.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
