package com.example.fernandeznunezjuanjose_final.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BDClientes extends GestionBD{

    Context context;

    public BDClientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCliente(String nombre, String dni, String fechaNac) {

        long id = 0;

        try {
            GestionBD dbHelper = new GestionBD(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("dni", dni);
            values.put("fechaNac", fechaNac);

            id = db.insert(TABLE_CLIENTES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Clientes> mostrarClientes() {

        GestionBD dbHelper = new GestionBD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Clientes cliente;
        Cursor cursorClientes;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " ORDER BY nombre ASC", null);

        if (cursorClientes.moveToFirst()) {
            do {
                cliente = new Clientes();
                cliente.setNombre(cursorClientes.getString(1));
                cliente.setDni(cursorClientes.getString(2));
                cliente.setFechaNac(cursorClientes.getString(3));
                listaClientes.add(cliente);
            } while (cursorClientes.moveToNext());
        }

        cursorClientes.close();

        return listaClientes;
    }

    public Clientes verContacto(int id) {

        GestionBD dbHelper = new GestionBD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Clientes cliente = null;
        Cursor cursorClientes;

        cursorClientes = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorClientes.moveToFirst()) {
            cliente = new Clientes();
            cliente.setNombre(cursorClientes.getString(1));
            cliente.setDni(cursorClientes.getString(2));
            cliente.setFechaNac(cursorClientes.getString(3));
        }

        cursorClientes.close();

        return cliente;
    }

    public boolean editarContacto(int id, String nombre, String telefono, String correo_electronico) {

        boolean correcto = false;

        GestionBD dbHelper = new GestionBD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CLIENTES + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', correo_electronico = '" + correo_electronico + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarContacto(int id) {

        boolean correcto = false;

        GestionBD dbHelper = new GestionBD(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CLIENTES + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}

