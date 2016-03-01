/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rtec;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Ventas {

    final double IVA = 0.16;

    private double iva;
    private double ventaNeta;

    private double ventaBruta;
    private String capturista;
    private String fecha;

    public Ventas(double ventaBruta, String capturista, String fecha) {
        this.ventaBruta = ventaBruta;
        this.capturista = capturista;
        this.fecha = fecha;
        this.iva= calcularIva(ventaBruta);
        this.ventaNeta=calcularVentaNeta(ventaBruta);
    }

    public double getVentaBruta() {
        return redondear(ventaBruta);
    }

    public void setVentaBruta(double ventaBruta) {
        this.ventaBruta = ventaBruta;
    }

    public String getCapturista() {
        return capturista;
    }

    public void setCapturista(String capturista) {
        this.capturista = capturista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getIva() {
        System.out.println(redondear(iva));
        return redondear(iva);
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getVentaNeta() {
        return redondear(ventaNeta);
    }
   

    public void setVentaNeta(double ventaNeta) {
        this.ventaNeta = ventaNeta;
    }

    public double calcularIva(double ventaBruta) {
        return ventaBruta*IVA;
    }
    
    public double calcularVentaNeta(double ventaBruta){
        return ventaBruta -(ventaBruta * IVA);
    }
    
    private double redondear(double valor){
        valor=valor*100;
        double tmp =Math.round(valor);
        return tmp/100;        
    }
    
    
}
