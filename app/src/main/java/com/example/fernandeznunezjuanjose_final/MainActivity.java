package com.example.fernandeznunezjuanjose_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fernandeznunezjuanjose_final.BaseDatos.BDClientes;
import com.example.fernandeznunezjuanjose_final.BaseDatos.Clientes;
import com.example.fernandeznunezjuanjose_final.BaseDatos.GestionBD;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaContactos;
    ArrayList <Clientes> arrayClientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        BDClientes db = new BDClientes(MainActivity.this);

        arrayClientes = new ArrayList<>();

        ListaClientesAdapter adapter = new ListaClientesAdapter(db.mostrarClientes());
        listaContactos.setAdapter(adapter);



        GestionBD helper = new GestionBD(MainActivity.this);
        SQLiteDatabase bd = helper.getWritableDatabase();

        if(bd != null){
            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Error al crear la base de datos", Toast.LENGTH_LONG).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_nuevo:
                NuevoCliente();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void NuevoCliente(){
        Intent intent = new Intent(this, CrearCliente.class);
        startActivity(intent);
    }
}