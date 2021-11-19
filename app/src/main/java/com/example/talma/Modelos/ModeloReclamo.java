package com.example.talma.Modelos;

public class ModeloReclamo {
    String codigo_reclamo,uid, rsir, area, fecha,estado, motivo;


    public ModeloReclamo() {
    }
    public ModeloReclamo(String codigo_reclamo, String uid, String rsir, String area, String fecha, String estado, String motivo) {
        this.codigo_reclamo = codigo_reclamo;
        this.uid = uid;
        this.rsir = rsir;
        this.area = area;
        this.fecha = fecha;
        this.estado = estado;
        this.motivo = motivo;
    }

    public String getCodigo_reclamo() {
        return codigo_reclamo;
    }

    public void setCodigo_reclamo(String codigo_reclamo) {
        this.codigo_reclamo = codigo_reclamo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
