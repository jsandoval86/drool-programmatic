package com.droolsprogrammatic.modelos;

public class Cliente {

    private String nombre;
    private boolean vip;
    private int puntos;

    public Cliente(String nombre, boolean vip, int puntos) {
        this.nombre = nombre;
        this.vip = vip;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isVip() {
        return vip;
    }

    public int getPuntos() {
        return puntos;
    }

    public void acumularPuntos(int puntos) {
        this.puntos = this.puntos + puntos;
    }
}



