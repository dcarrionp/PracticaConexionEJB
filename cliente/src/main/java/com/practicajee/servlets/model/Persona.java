package com.practicajee.servlets.model;

public class Persona {

    private String dni;
    private String nombre;
    private String apellido;
    private int edad;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return String.format("Persona [DNI: %s, Nombre: %s, Apellido: %s, Edad: %d]", dni, nombre, apellido, edad);
    }

}
