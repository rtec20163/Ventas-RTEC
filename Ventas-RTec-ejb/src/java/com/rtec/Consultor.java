package com.rtec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author nachintoch
 */
@ApplicationScoped
public class Consultor {

    

    public Ventas[] consultar() throws SQLException, ClassNotFoundException {
        
        ConexionBD con = new ConexionBD("rtec", "postgres", "root");
        con.conectarBD();
        ResultSet consultaBD = con.consultarBD("SELECT * FROM ventas order by fecha DESC NULLS FIRST;");
        con.desconectarBD();
        LinkedList<Ventas> salida = new LinkedList<>();
        while (consultaBD.next()) {
            salida.add(new Ventas(consultaBD.getDouble("ventabruta"),
                    consultaBD.getString("capturista"), consultaBD.getString("fecha")));
        }
        return salida.toArray(new Ventas[0]);
    }

}//Consultor
