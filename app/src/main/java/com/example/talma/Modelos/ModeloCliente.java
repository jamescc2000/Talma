package com.example.talma.Modelos;

public class ModeloCliente {

    String aerolinea, email, fechaRegistro, id, password, uid;

    public ModeloCliente() {
    }

    public ModeloCliente(String aerolinea, String email, String fechaRegistro, String id, String password, String uid) {
        this.aerolinea = aerolinea;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.id = id;
        this.password = password;
        this.uid = uid;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
