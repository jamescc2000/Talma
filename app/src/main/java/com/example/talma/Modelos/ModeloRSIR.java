package com.example.talma.Modelos;

public class ModeloRSIR {

    //Usar los mismos nombres que en la base de datos
    String codigo, aeropuerto, compañia, origen, destino, tipo_aeronave, matricula, area,a_cargo_de, fecha_llegada, hora_llegada, nvuelo_llegada, pea_llegada, fecha_salida,hora_salida,nvuelo_salida,pea_salida;

    public ModeloRSIR() {
    }

    public ModeloRSIR(String codigo, String aeropuerto, String compañia, String origen, String destino, String tipo_aeronave, String matricula, String area, String a_cargo_de, String fecha_llegada, String hora_llegada, String nvuelo_llegada, String pea_llegada, String fecha_salida, String hora_salida, String nvuelo_salida, String pea_salida) {
        this.codigo = codigo;
        this.aeropuerto = aeropuerto;
        this.compañia = compañia;
        this.origen = origen;
        this.destino = destino;
        this.tipo_aeronave = tipo_aeronave;
        this.matricula = matricula;
        this.area = area;
        this.a_cargo_de = a_cargo_de;
        this.fecha_llegada = fecha_llegada;
        this.hora_llegada = hora_llegada;
        this.nvuelo_llegada = nvuelo_llegada;
        this.pea_llegada = pea_llegada;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.nvuelo_salida = nvuelo_salida;
        this.pea_salida = pea_salida;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(String aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipo_aeronave() {
        return tipo_aeronave;
    }

    public void setTipo_aeronave(String tipo_aeronave) {
        this.tipo_aeronave = tipo_aeronave;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getA_cargo_de() {
        return a_cargo_de;
    }

    public void setA_cargo_de(String a_cargo_de) {
        this.a_cargo_de = a_cargo_de;
    }

    public String getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(String fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public String getHora_llegada() {
        return hora_llegada;
    }

    public void setHora_llegada(String hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    public String getNvuelo_llegada() {
        return nvuelo_llegada;
    }

    public void setNvuelo_llegada(String nvuelo_llegada) {
        this.nvuelo_llegada = nvuelo_llegada;
    }

    public String getPea_llegada() {
        return pea_llegada;
    }

    public void setPea_llegada(String pea_llegada) {
        this.pea_llegada = pea_llegada;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getNvuelo_salida() {
        return nvuelo_salida;
    }

    public void setNvuelo_salida(String nvuelo_salida) {
        this.nvuelo_salida = nvuelo_salida;
    }

    public String getPea_salida() {
        return pea_salida;
    }

    public void setPea_salida(String pea_salida) {
        this.pea_salida = pea_salida;
    }
}
