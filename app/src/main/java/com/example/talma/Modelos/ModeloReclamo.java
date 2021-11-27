package com.example.talma.Modelos;

public class ModeloReclamo {

    String codigoReclamo, uid, codigoRsir, codigoServicio, fechaRegistro, estado, motivo;


    public ModeloReclamo() {
    }

    public ModeloReclamo(String codigoReclamo, String uid, String codigoRsir, String codigoServicio, String fechaRegistro, String estado, String motivo) {
        this.codigoReclamo = codigoReclamo;
        this.uid = uid;
        this.codigoRsir = codigoRsir;
        this.codigoServicio = codigoServicio;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.motivo = motivo;
    }

    public String getCodigoReclamo() {
        return codigoReclamo;
    }

    public void setCodigoReclamo(String codigoReclamo) {
        this.codigoReclamo = codigoReclamo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCodigoRsir() {
        return codigoRsir;
    }

    public void setCodigoRsir(String codigoRsir) {
        this.codigoRsir = codigoRsir;
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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
