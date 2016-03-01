package com.rtec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author user
 */

public class ConexionBD {

    private String url;
    private String controlador;
    private String usuario;
    private String contrasena;
    private Connection conexion;
    private Statement stmt;

    /**
     * Crea una instancia de la clase para iniciar la conexion a la BD.
     *
     * @param base El nombre de la base de datos.
     * @param usuario El usuario con el que se ingresa a la base de datos.
     * @param contrasena La contrasena del usuario que accede a la base.
     * @throws ClassNotFoundException Si no encuentra el Driver adecuado.
     */
    public ConexionBD(String base,
            String usuario,
            String contrasena) throws ClassNotFoundException {
        this.controlador = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/" + base;
        this.usuario = usuario;
        this.contrasena = contrasena;
        cargarDriver();
    }

    // Metodo que se encarga de cargar el driver para hacer 
    // la conexion entre el manejador y la aplicacion.
    private void cargarDriver() throws ClassNotFoundException {
        Class.forName(controlador);
        System.out.println("Se Cargo el driver exitosamente");
    }

    /**
     * Se encarga de crear una conexion a la BD para poder obtener informacion
     * de esta.
     *
     * @throws SQLException Si la conexion no se crea correctamente o esta
     * cerrada.
     */
    public void conectarBD() throws SQLException {
        System.out.println("Estableciendo Conexion...");
        conexion = DriverManager.getConnection(url, usuario, contrasena);
        System.out.println("Conexion establecida!");
        stmt = conexion.createStatement();
    }

    /**
     * Cierra la conexion a la BD.
     *
     * @throws SQLException Si al acceder a la base de datos ocurre un error.
     */
    public void desconectarBD() throws SQLException {
        if (this.getConexion() != null) {
            conexion.close();
            System.out.println("Conexion Cerrada!");
        }
    }

    /**
     * Dada una sentencia de sql se encarga de insertarla a la BD, esta
     * sentencia debe ser de la forma "INSERT INTO tabla VALUES (valores...)"
     *
     * @param sentencia La sentencia de sql para agregar a la BD.
     * @throws SQLException Si la conexion no esta activa.
     */
    public void insertarBD(String sentencia) throws SQLException {
        stmt.execute(sentencia);
        System.out.println("Se ejecuto la insercion correctamente.");
    }

    /**
     * Dada una sentencia de sql se encarga de insertarla a la BD, esta
     * sentencia debe ser de la forma "DELETE FROM tabla WHERE valores =
     * valores"
     *
     * @param sentencia La sentencia de sql para eliminar de la BD.
     * @throws SQLException Si la conexion no esta activa.
     */
    public void borrarBD(String sentencia) throws SQLException {
        stmt.execute(sentencia);
        System.out.println("Se elimino correctamente.");
    }

    /**
     * Dada una sentencia de sql se encarga de actualizar la BD, esta sentencia
     * debe ser de la forma "UPDATE tabla SET columna = valores"
     *
     * @param sentencia la sentencia de sql para actualizar la base.
     * @throws SQLException Si la conexion no esta activa.
     */
    public void actualizarBD(String sentencia) throws SQLException {
        stmt.executeUpdate(sentencia);
        System.out.println("Base Actualizada!");
    }

    /**
     * Dada una sentencia de sql se encarga de buscar en la BD, esta sentencia
     * debe ser de la forma "SELECT valores FROM tabla MODIFICADORES".
     *
     * @param busqueda La sentencia de sql para realizar la busqueda en la BD.
     * @return El conjunto de elementos que cumplen las caracteristicas de la
     * busqueda realizada o null si la busqueda no tiene exito.
     * @throws SQLException Si la conexion no esta activa.
     */
        public ResultSet consultarBD(String busqueda) throws SQLException {
        ResultSet resultado = stmt.executeQuery(busqueda);
        System.out.println("Consulta realizada!");
        return resultado;
    }

    /**
     * Nos permite obtener la conexion de la base de datos.
     *
     * @return la conexion creada a la base de datos.
     */
    public Connection getConexion() {
        return conexion;
    }
}
