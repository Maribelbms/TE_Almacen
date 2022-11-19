
package com.emergentes.modelo;

public class Productos {
    private int id;
    private String producto;
    private float precio;
    private int Cantidad;

    public Productos() {
        this.id =0;
        this.producto="";
        this.precio=0;
        this.Cantidad=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
    
    
    
    
    
}
