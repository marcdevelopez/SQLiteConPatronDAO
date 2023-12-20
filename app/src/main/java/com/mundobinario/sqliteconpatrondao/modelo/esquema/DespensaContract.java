package com.mundobinario.sqliteconpatrondao.modelo.esquema;

import android.provider.BaseColumns;

public final class DespensaContract {

    /* Para prevenir alguna instanciación accidental de la clase contrato
     * se hace el constructor private */
    private DespensaContract() {
    }

    /* Clase interna que define los contenidos de la tabla */
    public static class alimentos implements BaseColumns {
        public static final String DATABASE_NAME = "despensa.db";
        public static final String TABLE_NAME = "alimentos";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_ALIMENTO = "alimento";
        public static final String COLUMN_NAME_HIDRATO = "hidratos";
        public static final String COLUMN_NAME_PROTEINA = "proteinas";
        public static final String COLUMN_NAME_GRASA = "grasas";

        // ojo el campo _id debe existir, INTEGER y autoincrementable, o dará problemas...
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_ALIMENTO + " TEXT, " +
                        COLUMN_NAME_HIDRATO + " INTEGER, " +
                        COLUMN_NAME_PROTEINA + " INTEGER, " +
                        COLUMN_NAME_GRASA + " INTEGER)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
