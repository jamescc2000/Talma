package com.example.talma.Modelos;

public class ModeloServicio {

    //Usar los mismos nombres que en la base de datos
    String uid,codigo_rsir,tipo, subtipo,nombre_servicio, codigo_servicio, hora_desde_llegada,hora_hasta_llegada, hora_desde_salida,hora_hasta_salida, cantidad_llegada, cantidad_salida, estado;

    public ModeloServicio() {
    }

    public ModeloServicio(String uid, String codigo_rsir, String tipo, String subtipo,String nombre_servicio, String codigo_servicio, String hora_desde_llegada, String hora_hasta_llegada, String hora_desde_salida, String hora_hasta_salida, String cantidad_llegada, String cantidad_salida, String estado) {
        this.uid = uid;
        this.codigo_rsir = codigo_rsir;
        this.tipo = tipo;
        this.subtipo = subtipo;
        this.nombre_servicio = nombre_servicio;
        this.codigo_servicio = codigo_servicio;
        this.hora_desde_llegada = hora_desde_llegada;
        this.hora_hasta_llegada = hora_hasta_llegada;
        this.hora_desde_salida = hora_desde_salida;
        this.hora_hasta_salida = hora_hasta_salida;
        this.cantidad_llegada = cantidad_llegada;
        this.cantidad_salida = cantidad_salida;
        this.estado = estado;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCodigo_rsir() {
        return codigo_rsir;
    }

    public void setCodigo_rsir(String codigo_rsir) {
        this.codigo_rsir = codigo_rsir;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public String getCodigo_servicio() {
        return codigo_servicio;
    }

    public void setCodigo_servicio(String codigo_servicio) {
        this.codigo_servicio = codigo_servicio;
    }

    public String getHora_desde_llegada() {
        return hora_desde_llegada;
    }

    public void setHora_desde_llegada(String hora_desde_llegada) {
        this.hora_desde_llegada = hora_desde_llegada;
    }

    public String getHora_hasta_llegada() {
        return hora_hasta_llegada;
    }

    public void setHora_hasta_llegada(String hora_hasta_llegada) {
        this.hora_hasta_llegada = hora_hasta_llegada;
    }

    public String getHora_desde_salida() {
        return hora_desde_salida;
    }

    public void setHora_desde_salida(String hora_desde_salida) {
        this.hora_desde_salida = hora_desde_salida;
    }

    public String getHora_hasta_salida() {
        return hora_hasta_salida;
    }

    public void setHora_hasta_salida(String hora_hasta_salida) {
        this.hora_hasta_salida = hora_hasta_salida;
    }

    public String getCantidad_llegada() {
        return cantidad_llegada;
    }

    public void setCantidad_llegada(String cantidad_llegada) {
        this.cantidad_llegada = cantidad_llegada;
    }

    public String getCantidad_salida() {
        return cantidad_salida;
    }

    public void setCantidad_salida(String cantidad_salida) {
        this.cantidad_salida = cantidad_salida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
