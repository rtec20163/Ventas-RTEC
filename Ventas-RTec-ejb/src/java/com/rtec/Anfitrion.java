package com.rtec;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

/**
 * Ejemplo de c&oacute;mo crear un EJB mediante POJO para crear aplicaciones web
 * con Java.
 * @author <a href="mailto:manuel_castillo_cc@ciencias.unam.mx" >Manuel
 * "Nachintoch" Castillo</a>
 * @version 1.0, february 2016
 * @since Riesgo Tecnol&oacute;gico 2016-2
 */
@ApplicationScoped
public class Anfitrion {
    
    // atrubtos de clase
    
    /**
     * Cuenta cuantas veces se ha hecho una petición.
     * @since Anfitrion 1.0, february 2016.
     */
    private int contador;
    
    /**
     * Cadena a manipular al atender la petici&oacute;n.
     * @since Anfitrion 1.0, february 2016.
     */
    private String aManipular;

    // métodos de acceso
    
    /**
     * Devuelve el valor de contador para la vista de la p&aacute;gina.
     * @return int - El valor actual de contador.
     * @since Anfitrion 1.0, february 2016
     */
    public int getContador() {
        return this.contador;
    }//getContador
    
    /**
     * Devuelve el valor de la cadena a manipular.
     * @return String - El valor de la cadena.
     * @since Anfitrion 1.0, february 2016
     */
    public String getAManipular() {
        return aManipular;
    }//getaManipular
    
    /**
     * Cambia el valor de contador. Se incluye por necesidad, pero no se usa
     * en este ejemplo.
     * @param valor - El nuevo valor de contador.
     * @since Anfitrion 1.0, februay 2016.
     */
    public void setContador(int valor) {
        this.contador = valor;
    }//setContador
    
    /**
     * Cambia el valor de la cadena a manipular. Tampoco se usa en este ejemplo.
     * @param aManipular aManipular - El nuevo valor de la cadena.
     * @since Anfitrion 1.0, february 2016
     */
    public void setAManipular(String aManipular) {
        this.aManipular = aManipular;
    }//setaManipular
    
    // métodos de implementación
    
    /**
     * M&eacute;todo que ejemplifica el atender la petici&oacute;n de un
     * cliente.
     * @param cadena - La cadena a manipular.
     * @throws IOException - Si hay un problema al abrir el archivo
     * <tt>index.xhtml</tt> en el proyecto WAR.
     * @since Anfitrion 1.0, february 2016.
     */
    public void atiende(String cadena) throws IOException {
        this.contador++;
        this.aManipular = cadena.toUpperCase();
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("index.xhtml");
    }//atiende
    
}//Anfitrión Bean
