package com.mundobinario.sqliteconpatrondao.modelo;

import android.content.Context;
import android.widget.EditText;

import com.mundobinario.sqliteconpatrondao.ConexionDespensaDbHelper;
import com.mundobinario.sqliteconpatrondao.controlador.Controlador;
import com.mundobinario.sqliteconpatrondao.modelo.dataccessobject.AlimentosDAO;
import com.mundobinario.sqliteconpatrondao.modelo.entidades.Alimento;

import java.util.ArrayList;
import java.util.List;

public class Logica {
    Controlador controlador = new Controlador();

    public Logica() {
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void agregar(Context context, ConexionDespensaDbHelper conexionhelper, EditText alimento,
                        EditText hidratos, EditText proteinas, EditText grasas) {
        // para poder manejar posible entrada en blanco de EditTexts hidratos, proteinas y grasas
        // los pasamos a "0" ya que un Integer no puede ser ""... daría error
        String stringAlimento = alimento.getText().toString();
        String stringHidratos = hidratos.getText().toString();
        if (stringHidratos.isEmpty()) {
            stringHidratos = "0";
        }
        Integer intHidratos = Integer.parseInt(stringHidratos);
        String stringProteinas = proteinas.getText().toString();
        if (stringProteinas.isEmpty()) {
            stringProteinas = "0";
        }
        Integer intProteinas = Integer.parseInt(stringProteinas);
        String stringGrasas = grasas.getText().toString();
        if (stringGrasas.isEmpty()) {
            stringGrasas = "0";
        }
        Integer intGrasas = Integer.parseInt(stringGrasas);

        AlimentosDAO.agregarRegistro(context, conexionhelper, stringAlimento, intHidratos,
                intProteinas, intGrasas);
    }

    public List consultar(ConexionDespensaDbHelper conexionhelper, String alimento) {
        List resultadoAlimento = new ArrayList<Alimento>();
        resultadoAlimento = AlimentosDAO.consultar(conexionhelper, alimento);
        return resultadoAlimento;
    }

    public void modificar(Context context, ConexionDespensaDbHelper conexionhelper, String alimento,
                          EditText EThidratos, EditText ETproteinas, EditText ETgrasas) {
        Integer intHidratos = Integer.parseInt(EThidratos.getText().toString());
        Integer intProteinas = Integer.parseInt(ETproteinas.getText().toString());
        Integer intGrasas = Integer.parseInt(ETgrasas.getText().toString());
        AlimentosDAO.modificar(context, conexionhelper, alimento, intHidratos, intProteinas,
                intGrasas);
    }

    public void eliminar(Context context, ConexionDespensaDbHelper conexion, String alimento) {
        AlimentosDAO.eliminar(context, conexion, alimento);
    }
}
