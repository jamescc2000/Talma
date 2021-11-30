package com.example.talma.Modelos;

public class ModeloRSIR {

    //Usar los mismos nombres que en la base de datos
    String uid,codigoRsir, aeropuerto, compania, emailCliente, origen, destino, aeronave, matricula, area,aCargoDe, fechaLlegada, horaLlegada, nvueloLlegada, peaLlegada, fechaSalida,horaSalida,nvueloSalida,peaSalida, estado, peso;

    public ModeloRSIR() {
    }

    public ModeloRSIR(String uid, String codigoRsir, String aeropuerto, String compania, String emailCliente, String origen, String destino, String aeronave, String matricula, String area, String aCargoDe, String fechaLlegada, String horaLlegada, String nvueloLlegada, String peaLlegada, String fechaSalida, String horaSalida, String nvueloSalida, String peaSalida, String estado, String peso) {
        this.uid = uid;
        this.codigoRsir = codigoRsir;
        this.aeropuerto = aeropuerto;
        this.compania = compania;
        this.emailCliente = emailCliente;
        this.origen = origen;
        this.destino = destino;
        this.aeronave = aeronave;
        this.matricula = matricula;
        this.area = area;
        this.aCargoDe = aCargoDe;
        this.fechaLlegada = fechaLlegada;
        this.horaLlegada = horaLlegada;
        this.nvueloLlegada = nvueloLlegada;
        this.peaLlegada = peaLlegada;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.nvueloSalida = nvueloSalida;
        this.peaSalida = peaSalida;
        this.estado = estado;
        this.peso = peso;
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

    public String getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(String aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
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

    public String getAeronave() {
        return aeronave;
    }

    public void setAeronave(String aeronave) {
        this.aeronave = aeronave;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getaCargoDe() {
        return aCargoDe;
    }

    public void setaCargoDe(String aCargoDe) {
        this.aCargoDe = aCargoDe;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getNvueloLlegada() {
        return nvueloLlegada;
    }

    public void setNvueloLlegada(String nvueloLlegada) {
        this.nvueloLlegada = nvueloLlegada;
    }

    public String getPeaLlegada() {
        return peaLlegada;
    }

    public void setPeaLlegada(String peaLlegada) {
        this.peaLlegada = peaLlegada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getNvueloSalida() {
        return nvueloSalida;
    }

    public void setNvueloSalida(String nvueloSalida) {
        this.nvueloSalida = nvueloSalida;
    }

    public String getPeaSalida() {
        return peaSalida;
    }

    public void setPeaSalida(String peaSalida) {
        this.peaSalida = peaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}
