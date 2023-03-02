package com.example.fernandeznunezjuanjose_final.BaseDatos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.Period;

public class Clientes {
    private String nombre;
    private String fechaNac;
    private String dni;
    private int edad = 0;
    private int id;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calcularEdad(){
        LocalDate fechanac = LocalDate.parse(this.fechaNac);
        LocalDate hoy = LocalDate.now();
        if ((hoy != null) && (fechanac != null))
        {
            this.setEdad(Math.abs(Period.between(hoy, fechanac).getYears()));
        }
        else
        {
            this.setEdad(0);
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
