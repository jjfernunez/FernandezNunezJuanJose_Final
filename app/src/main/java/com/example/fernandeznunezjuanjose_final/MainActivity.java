package com.example.fernandeznunezjuanjose_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fernandeznunezjuanjose_final.BaseDatos.GestionBD;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent intent = new Intent(this, MostrarDatos.class);
        startActivity(intent);
    }
}