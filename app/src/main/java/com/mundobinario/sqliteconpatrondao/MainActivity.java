package com.mundobinario.sqliteconpatrondao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mundobinario.sqliteconpatrondao.controlador.Controlador;

public class MainActivity extends AppCompatActivity {
    EditText ETalimento;
    EditText EThidratos;
    EditText ETproteinas;
    EditText ETgrasas;
    ConexionDespensaDbHelper conexion = new ConexionDespensaDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETalimento = findViewById(R.id.et_alimento);
        EThidratos = findViewById(R.id.et_hidratos);
        ETproteinas = findViewById(R.id.et_proteinas);
        ETgrasas = findViewById(R.id.et_grasas);


    }

    public void botonAgregar(View view) {
        Controlador control = new Controlador();
        control.agregar(getApplicationContext(), conexion, ETalimento, EThidratos, ETproteinas,
                ETgrasas);

    }

    public void botonConsultar(View view) {
        Controlador control = new Controlador();
        control.consultar(getApplicationContext(), conexion, ETalimento.getText().toString(),
                EThidratos, ETproteinas,
                ETgrasas);
    }

    public void botonModificar(View view) {
        Controlador control = new Controlador();
        control.modificar(getApplicationContext(),conexion, ETalimento.getText().toString(),
                EThidratos, ETproteinas, ETgrasas);
    }

    public void botonEliminar(View view) {
        Controlador control = new Controlador();
        control.eliminar(getApplicationContext(),conexion,ETalimento.getText().toString());
    }
}