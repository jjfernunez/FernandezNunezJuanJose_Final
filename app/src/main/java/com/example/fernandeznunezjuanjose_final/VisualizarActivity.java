package com.example.fernandeznunezjuanjose_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fernandeznunezjuanjose_final.BaseDatos.BDClientes;
import com.example.fernandeznunezjuanjose_final.BaseDatos.Clientes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VisualizarActivity extends AppCompatActivity {

    EditText txtNombre, txtDNI, txtFechaNac, txtEdad;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
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

        BDClientes bd = new BDClientes(VisualizarActivity.this);
        cliente = bd.verContacto(id);

        if(cliente != null){
            txtNombre.setText(cliente.getNombre());
            txtDNI.setText(cliente.getDni());
            txtFechaNac.setText(cliente.getFechaNac());
            txtEdad.setText(Integer.toString(cliente.getEdad()));
            btnGuarda.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtDNI.setInputType(InputType.TYPE_NULL);
            txtFechaNac.setInputType(InputType.TYPE_NULL);
            txtEdad.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualizarActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VisualizarActivity.this);
                builder.setMessage("¿Quieres eliminar este cliente?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(bd.eliminarContacto(id)){
                            lista();
                        }


                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}