package com.example.talma.Modelos;


public class ModeloFactura {
    private long dias;
    private float peso;

    public ModeloFactura(long dias, float peso) {
        this.dias = dias;
        this.peso = peso;
    }

    public long getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
