package com.mundobinario.sqliteconpatrondao.controlador;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Toast;

import com.mundobinario.sqliteconpatrondao.ConexionDespensaDbHelper;
import com.mundobinario.sqliteconpatrondao.modelo.Logica;
import com.mundobinario.sqliteconpatrondao.modelo.dataccessobject.AlimentosDAO;
import com.mundobinario.sqliteconpatrondao.modelo.entidades.Alimento;
import com.mundobinario.sqliteconpatrondao.vista.Vista;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private Vista vista;
    private Logica logica;

    public Controlador() {
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public void setLogica(Logica logica) {
        this.logica = logica;
    }

    public void agregar(Context context, ConexionDespensaDbHelper conexionhelper, EditText alimento,
                        EditText hidratos, EditText proteinas, EditText grasas) {
        logica = new Logica();
        logica.agregar(context, conexionhelper, alimento, hidratos, proteinas, grasas);
    }

    public void consultar(Context context, ConexionDespensaDbHelper conexionhelper, String alimento,
                          EditText hidratos, EditText proteinas, EditText grasas) {
        List resultadoBusqueda = new ArrayList<Alimento>();
        logica = new Logica();
        resultadoBusqueda = logica.consultar(conexionhelper, alimento);
        if (!resultadoBusqueda.isEmpty()) {
            Vista vistaConsulta = new Vista();
            vistaConsulta.consulta(resultadoBusqueda, hidratos, proteinas, grasas);
        } else {
            Vista.limpiar(context, hidratos, proteinas, grasas);
        }
    }

    public void modificar(Context context, ConexionDespensaDbHelper conexionhelper, String alimento,
                          EditText EThidratos, EditText ETproteinas, EditText ETgrasas) {
        logica = new Logica();
        logica.modificar(context, conexionhelper,alimento, EThidratos,ETproteinas,ETgrasas);
    }

    public void eliminar(Context context, ConexionDespensaDbHelper conexion, String alimento) {
        logica = new Logica();
        logica.eliminar(context, conexion, alimento);
    }

}
