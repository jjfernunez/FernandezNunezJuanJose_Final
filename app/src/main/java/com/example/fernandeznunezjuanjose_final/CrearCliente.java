package com.example.fernandeznunezjuanjose_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fernandeznunezjuanjose_final.BaseDatos.BDClientes;

public class CrearCliente extends AppCompatActivity {

    EditText txtNombre, txtDni, txtFechaNac, txtEdad;
    Button  btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);

        txtNombre = findViewById(R.id.txtNombre);
        txtDni = findViewById(R.id.txtDni);
        txtFechaNac = findViewById(R.id.txtFechaNac);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BDClientes db = new BDClientes(CrearCliente.this);
               long id = db.insertarCliente(txtNombre.getText().toString(), txtDni.getText().toString(), txtFechaNac.getText().toString());

               if(id > 0){
                   Toast.makeText(CrearCliente.this, "Cliente Guardado", Toast.LENGTH_LONG).show();
                   vaciar();
               }
               else{
                   Toast.makeText(CrearCliente.this, "Error al guardar el cliente", Toast.LENGTH_LONG).show();
               }
            }
        });
    }

    private void vaciar(){
        txtNombre.setText("");
        txtDni.setText("");
        txtFechaNac.setText("");
    }
}