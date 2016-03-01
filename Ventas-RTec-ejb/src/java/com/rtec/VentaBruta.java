/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rtec;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author user
 */
@ApplicationScoped
public class VentaBruta {

    private String capturista;
    private double monto;
    private String fecha;

    public String getCapturista() {
        return capturista;
    }

    public void setCapturista(String capturista) {
        this.capturista = capturista;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(fecha, formatter);
            this.fecha=parsedDate.toString();
        } catch (Exception ex) {
            System.out.println("Formato de fecha invalida");
        }
    }

    public void insertar() throws SQLException, ClassNotFoundException, IOException {

        ConexionBD con = new ConexionBD("rtec", "postgres", "root");
        con.conectarBD();
        con.insertarBD("INSERT INTO ventas(fecha,capturista,ventabruta)"
                + "VALUES('" + fecha + "','" + capturista + "'," + monto + ");");
        con.desconectarBD();

        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("index.xhtml");
    }

}
