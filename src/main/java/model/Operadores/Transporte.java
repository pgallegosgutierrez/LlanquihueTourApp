package model.Operadores;

import model.Interface.Registrable;


public class Transporte implements Registrable {
    private String tipo;
    private int capacidad;
    private String patente;

    public Transporte(String tipo, int capacidad, String patente) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.patente = patente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    @Override
    public void mostrarResumen(){
        System.out.println("Tipo de Vehiculo: "+tipo);
        System.out.println("Capacidad de pasajeros: "+capacidad);
        System.out.println("Patente: "+patente);
    }


}
