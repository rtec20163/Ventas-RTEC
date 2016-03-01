package com.rtec;

/**
 *
 * @author nachintoch
 */
public class ObjetoBD {

    private int id;
    private String valor;
    
    public ObjetoBD(int id, String val) {
        this.id = id;
        this.valor = val;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
