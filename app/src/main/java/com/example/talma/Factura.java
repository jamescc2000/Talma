package com.example.talma;

import java.util.Date;

public class Factura {
    private String RSIR;
    private String encargado_facturacion;
    private Date fecha_emision;

    private float almacenaje;
    private float manipuleo_maritimo;
    private float descarga;
    private float monitoreo;
    private float salvaguardia;

    private float estibador;
    private float reconocimiento_fisico;
    private float muestras;
    private float montacarga;
    private float inspeccion;

    private float total;

    public Factura() {

    }

    public String getRSIR() {
        return RSIR;
    }

    public void setRSIR(String RSIR) {
        this.RSIR = RSIR;
    }

    public String getEncargado_facturacion() {
        return encargado_facturacion;
    }

    public void setEncargado_facturacion(String encargado_facturacion) {
        this.encargado_facturacion = encargado_facturacion;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public float getAlmacenaje() {
        return almacenaje;
    }

    public void setAlmacenaje(float almacenaje) {
        this.almacenaje = almacenaje;
    }

    public float getManipuleo_maritimo() {
        return manipuleo_maritimo;
    }

    public void setManipuleo_maritimo(float manipuleo_maritimo) {
        this.manipuleo_maritimo = manipuleo_maritimo;
    }

    public float getDescarga() {
        return descarga;
    }

    public void setDescarga(float descarga) {
        this.descarga = descarga;
    }

    public float getMonitoreo() {
        return monitoreo;
    }

    public void setMonitoreo(float monitoreo) {
        this.monitoreo = monitoreo;
    }

    public float getSalvaguardia() {
        return salvaguardia;
    }

    public void setSalvaguardia(float salvaguardia) {
        this.salvaguardia = salvaguardia;
    }

    public float getEstibador() {
        return estibador;
    }

    public void setEstibador(float estibador) {
        this.estibador = estibador;
    }

    public float getReconocimiento_fisico() {
        return reconocimiento_fisico;
    }

    public void setReconocimiento_fisico(float reconocimiento_fisico) {
        this.reconocimiento_fisico = reconocimiento_fisico;
    }

    public float getMuestras() {
        return muestras;
    }

    public void setMuestras(float muestras) {
        this.muestras = muestras;
    }

    public float getMontacarga() {
        return montacarga;
    }

    public void setMontacarga(float montacarga) {
        this.montacarga = montacarga;
    }

    public float getInspeccion() {
        return inspeccion;
    }

    public void setInspeccion(float inspeccion) {
        this.inspeccion = inspeccion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
