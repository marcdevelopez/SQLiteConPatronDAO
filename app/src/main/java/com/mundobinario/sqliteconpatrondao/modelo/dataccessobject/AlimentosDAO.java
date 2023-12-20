package com.mundobinario.sqliteconpatrondao.modelo.dataccessobject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.mundobinario.sqliteconpatrondao.ConexionDespensaDbHelper;
import com.mundobinario.sqliteconpatrondao.modelo.entidades.Alimento;
import com.mundobinario.sqliteconpatrondao.modelo.esquema.DespensaContract;
import com.mundobinario.sqliteconpatrondao.vista.Vista;

import java.util.ArrayList;
import java.util.List;

public class AlimentosDAO {

    public static void agregarRegistro(Context context, ConexionDespensaDbHelper conexionhelper,
                                       String alimento, Integer hidratos, Integer proteinas,
                                       Integer grasas) {
        List consulta = consultar(conexionhelper, alimento);
        if (!consulta.isEmpty()) {
            Toast.makeText(context, alimento + " ya existe", Toast.LENGTH_SHORT).show();
        } else {
            // Obtiene el repositorio de datos en modo de escritura
            SQLiteDatabase db = conexionhelper.getWritableDatabase();
            // Crea un nuevo mapa de valores, donde los nombres de columna son las claves
            ContentValues values = new ContentValues();
            values.put(DespensaContract.alimentos.COLUMN_NAME_ALIMENTO, alimento);
            values.put(DespensaContract.alimentos.COLUMN_NAME_HIDRATO, hidratos);
            values.put(DespensaContract.alimentos.COLUMN_NAME_PROTEINA, proteinas);
            values.put(DespensaContract.alimentos.COLUMN_NAME_GRASA, grasas);
            // Inserte la nueva fila, devolviendo el valor de clave principal de la nueva fila
            long newRowId = db.insert(DespensaContract.alimentos.TABLE_NAME, null, values);
            Vista.exitoRegistro(context, alimento, newRowId);
        }

    }

    public static List consultar(ConexionDespensaDbHelper conexionhelper, String alimento) {
        SQLiteDatabase db = conexionhelper.getReadableDatabase();
        /* Define una proyección que especifica qué columnas de la base de datos usará realmente
        después de la consulta */
        String[] proyeccion = {
                DespensaContract.alimentos.COLUMN_NAME_ALIMENTO,
                DespensaContract.alimentos.COLUMN_NAME_HIDRATO,
                DespensaContract.alimentos.COLUMN_NAME_PROTEINA,
                DespensaContract.alimentos.COLUMN_NAME_GRASA
        };

        // Filtra resultados WHERE COLUMN_NAME_ALIMENTO = alimento (del EditText)
        String selection = DespensaContract.alimentos.COLUMN_NAME_ALIMENTO + " = ?";
        String[] selectionArgs = {alimento};

        // Cómo deseo que los resultados se ordenen en el Cursor resultante
        String sortOrder = DespensaContract.alimentos.COLUMN_NAME_ALIMENTO + " DESC";

        Cursor cursor = db.query(
                DespensaContract.alimentos.TABLE_NAME,  // La tabla a consultar
                proyeccion,                             // El array de columnas a devolver (null=all)
                selection,                              // Las columnas para la cláusula WHERE
                selectionArgs,                          // Los valores para la cláusula WHERE
                null,                           // No agrupa las filas
                null,                            // No filtra por grupos de filas
                sortOrder                               // Criterio de ordenación
        );

        // Vamos a crear una List que llenaremos de objetos Alimento
        // y utilizamos el metodo getColumnIndexOrThrow para llenarlo
        List itemAlimentos = new ArrayList<Alimento>();
        while (cursor.moveToNext()) {
            int hidratos = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DespensaContract.alimentos.COLUMN_NAME_HIDRATO));
            int proteinas = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DespensaContract.alimentos.COLUMN_NAME_PROTEINA));
            int grasas = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DespensaContract.alimentos.COLUMN_NAME_GRASA));
            Alimento alimento_resultadoCursor = new Alimento();
            alimento_resultadoCursor.setAlimento(alimento);
            alimento_resultadoCursor.setHidratos(hidratos);
            alimento_resultadoCursor.setProteinas(proteinas);
            alimento_resultadoCursor.setGrasas(grasas);
            itemAlimentos.add(alimento_resultadoCursor);
        }
        cursor.close();

        return itemAlimentos;

    }

    public static void modificar(Context context, ConexionDespensaDbHelper conexionhelper,
                                 String alimento, Integer hidratos, Integer proteinas,
                                 Integer grasas) {

        // Obtiene el repositorio de datos en modo de escritura
        SQLiteDatabase db = conexionhelper.getWritableDatabase();

        // Nuevos valores para columnas hidratos, proteinas y grasas:
        ContentValues values = new ContentValues();
        values.put(DespensaContract.alimentos.COLUMN_NAME_HIDRATO, hidratos);
        values.put(DespensaContract.alimentos.COLUMN_NAME_PROTEINA, proteinas);
        values.put(DespensaContract.alimentos.COLUMN_NAME_GRASA, grasas);

        // Cual fila se actualizará, basado en el campo alimento
        String selection = DespensaContract.alimentos.COLUMN_NAME_ALIMENTO + " LIKE ?";
        String[] selectionArgs = {alimento};

        int count = db.update(
                DespensaContract.alimentos.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        Vista.exitoModificar(context, count);
    }

    public static void eliminar(Context context, ConexionDespensaDbHelper conexion, String alimento) {
        // Obtiene el repositorio de datos en modo de escritura
        SQLiteDatabase db = conexion.getWritableDatabase();
        // Definimos la parte 'WHERE' de la consulta
        String selection = DespensaContract.alimentos.COLUMN_NAME_ALIMENTO + " LIKE ?";
        // Especificamos argumentos en orden de marcador de posición (EditText en este caso alimento)
        String[] selectionArgs = {alimento};
        // Declaracion SQL del problema
        int deleteRows = db.delete(DespensaContract.alimentos.TABLE_NAME, selection, selectionArgs);
        Vista.exitoEliminar(context, deleteRows);
    }
}
