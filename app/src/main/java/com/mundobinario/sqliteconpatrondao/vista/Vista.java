package com.mundobinario.sqliteconpatrondao.vista;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.mundobinario.sqliteconpatrondao.controlador.Controlador;
import com.mundobinario.sqliteconpatrondao.modelo.entidades.Alimento;

import java.util.List;

public class Vista {
    private Controlador controlador;

    public Vista() {
    }

    public static void exitoRegistro(Context context, String alimento, long newRowId) {
        Toast.makeText(context, "Registro " + alimento + ", _id: " + newRowId +
                ": INSERTADO con éxito", Toast.LENGTH_LONG).show();
        Log.i("AlimentosDAO", "insertado registro " + newRowId);
    }

    public static void exitoModificar(Context context, int count) {
        Toast.makeText(context, "Se MODIFICÓ " + count + " alimento/s", Toast.LENGTH_SHORT).show();
    }

    public static void exitoEliminar(Context context, int deleteRows) {
        Toast.makeText(context, "Se ELIMINÓ " + deleteRows + " alimento/s", Toast.LENGTH_SHORT).show();
    }


    public void setControlador(Controlador controlador1) {
        controlador = controlador1;
    }

    public void consulta(List resultadoBusqueda, EditText hidratos, EditText proteinas,
                         EditText grasas) {
        Alimento alimento = new Alimento();
        for (int i = 0; i < resultadoBusqueda.size(); i++) {
            alimento = (Alimento) resultadoBusqueda.get(i);
        }
        hidratos.setText(String.valueOf(alimento.getHidratos()));
        proteinas.setText(String.valueOf(alimento.getProteinas()));
        grasas.setText(String.valueOf(alimento.getGrasas()));
    }
    // Limpia los EditText si no devuelve resultados la búsqueda:
    public static void limpiar(Context context, EditText hidratos, EditText proteinas, EditText grasas) {
        hidratos.setText("");
        proteinas.setText("");
        grasas.setText("");
        Toast.makeText(context, "El alimento no existe", Toast.LENGTH_SHORT).show();
    }
}
