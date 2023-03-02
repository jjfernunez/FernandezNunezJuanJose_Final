package com.example.fernandeznunezjuanjose_final;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fernandeznunezjuanjose_final.BaseDatos.BDClientes;
import com.example.fernandeznunezjuanjose_final.BaseDatos.Clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtDNI, txtFechaNac, txtEdad;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Clientes cliente;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        txtNombre = findViewById(R.id.txtNombre);
        txtDNI = findViewById(R.id.txtDni);
        txtFechaNac = findViewById(R.id.txtFechaNac);
        txtEdad = findViewById(R.id.txtEdad);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar.setVisibility(View.INVISIBLE);
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null)
                id = Integer.parseInt(null);
            else {
                id = extras.getInt("ID");
            }
        }
        else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        BDClientes bd = new BDClientes(EditarActivity.this);
        cliente = bd.verContacto(id);

        if(cliente != null){
            txtNombre.setText(cliente.getNombre());
            txtDNI.setText(cliente.getDni());
            txtFechaNac.setText(cliente.getFechaNac());
            txtEdad.setText(Integer.toString(cliente.getEdad()));
            txtEdad.setInputType(InputType.TYPE_NULL);
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombre.getText().toString().equals("") && !txtDNI.getText().toString().equals("") && !txtFechaNac.getText().toString().equals("")){
                    correcto = bd.editarContacto(id, txtNombre.getText().toString(), txtDNI.getText().toString(), txtFechaNac.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "Cliente Modificado", Toast.LENGTH_LONG).show();
                        verCliente();
                    }
                    else{
                        Toast.makeText(EditarActivity.this, "Error al modificar registro", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    Toast.makeText(EditarActivity.this, "No puedes dejar campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verCliente(){
        Intent intent = new Intent(this, VisualizarActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}