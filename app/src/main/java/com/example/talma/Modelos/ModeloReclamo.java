package com.example.talma.Modelos;

public class ModeloReclamo {
    String codigo, reclamo, rsir, area, fecha,estado;


    public ModeloReclamo() {
    }
    public ModeloReclamo(String codigo, String reclamo, String rsir, String area, String fecha, String estado) {
        this.codigo = codigo;
        this.reclamo = reclamo;
        this.rsir = rsir;
        this.area = area;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReclamo() {
        return reclamo;
    }

    public void setReclamo(String reclamo) {
        this.reclamo = reclamo;
    }

    public String getRsir() {
        return rsir;
    }

    public void setRsir(String rsir) {
        this.rsir = rsir;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
