package com.example.proveedores.security;

import java.io.Serializable;

public class ProveedoresAutenticationReq implements Serializable{
    private static final long serialVersionUID = 1L;

    private String usuario;
    private String clave;

    public ProveedoresAutenticationReq() {
    }

    public ProveedoresAutenticationReq(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}