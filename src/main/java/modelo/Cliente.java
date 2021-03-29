/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author velae
 */
public class Cliente implements Serializable{
    static final long SerialVersionUID=42L;
    
    private String nombre;
    private String paterno;
    private String materno;
    private int cedula;
    
    private TarjetaDebito tarjeta;
    
    public void mostrarCliente(){
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Paterno: "+this.paterno);
        System.out.println("Materno: "+this.materno);
        System.out.println("Cedula: "+this.cedula);
        if (this.tarjeta!=null) {
            this.tarjeta.mostrarTarjeta();
        }else{
            System.out.println("El cliente no tiene tarjeta de debito");
        }
        System.out.println("-------------------------------------");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public TarjetaDebito getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaDebito tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    
}
