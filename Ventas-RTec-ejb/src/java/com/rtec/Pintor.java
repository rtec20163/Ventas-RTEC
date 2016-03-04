package com.rtec;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;

/**
 * Dibuja una gr&aacute;fica y la guarda en un PNG en el directorio <tt>img</tt>
 * del server.
 *
 * @author <a href="mailto:manuel_castillo_cc@ciencias.unam.mx" >Manuel,
 * "Nachintoch", Castillo</a>
 * @version 1.0, february 2016
 * @since Riesgo Tecnol&oacute;gico, february 2016
 */
@ApplicationScoped
public class Pintor {

    // métodos de implementación
    /**
     * Pinta una gr&aacute;fica en un PNG llamado <tt>"grafica.png"</tt>.
     *
     * @throws IOException - Si ocurre un problema al crear el archivo PNG.
     * @since Pintor 1.0, february 2016
     */
    public void paintGraph() throws IOException,SQLException, ClassNotFoundException {
        ConexionBD con = new ConexionBD("rtec", "postgres", "root");
        con.conectarBD();
        ResultSet consultaBD = con.consultarBD("SELECT * FROM ventas order by fecha;");
        con.desconectarBD();
        LinkedList<Ventas> salida = new LinkedList<>();
        while (consultaBD.next()) {
            salida.add(new Ventas(consultaBD.getDouble("ventabruta"),
                    consultaBD.getString("capturista"), consultaBD.getString("fecha")));
        }
        
        paintGraph(salida.toArray(new Ventas[0]));
        
    
    }

    
    
    
    /**
     * Pinta una grafica en un PNG llamado <tt>"grafica.png"</tt>.
     * @since Pintor 1.0, february 2016
     */
    public void paintGraph(Ventas[] ventas) {
        // creamos un buffer de imagen con dimenciones y propiedades dadas
        BufferedImage graf = new BufferedImage(1000, 700, BufferedImage.TYPE_4BYTE_ABGR);
        File png = Paths.get("/", "home", "user","Dropbox","riesgoTec","Practica1","Ventas-RTEC",
                "Ventas-RTec-war", "build", "web", "img",
                "grafica.png").toFile();
        png.mkdirs();
	try{
        png.createNewFile();
	}catch(Exception e){System.out.println("Error al crearArchivo");}
        // recuperamos los grÃ¡ficos para empezar a dibujar
        Graphics2D g = graf.createGraphics();
        // asignamos un color para el dibujado
        g.setColor(Color.black);
        // dibujamos los ejes
        g.drawLine(100, 650, 100, 50);//600pixeles de longitud vertical
        g.drawLine(100, 650, 800, 650);//700 pixeles de longitud horizontal
        g.setColor(Color.gray);
        g.drawLine(100, 150, 800, 150);
        g.setColor(Color.black);
        // asignamos una fuente para el texto
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        // dibujamos una cadena en la posiciÃ³n dada
        g.drawString("TIEMPO", 380, 680);
        // respaldamos la configuraciÃ³n original del plano donde trabajamos
        AffineTransform original = g.getTransform();
        // trasladamos el plano
        g.translate(0, 380);
        // rotamos 90 grados en sentido anti-horario.
        g.rotate(-Math.PI /2);
        g.drawString("VENTAS", 0, 25);
        // restauramos la configuraciÃ³n original del plano
        g.setTransform(original);
        // dibujamos el perÃ­metro de un rectÃ¡ngulo
        g.drawRect(800, 100, 200, 100);
        // cambiamos el color de dibujado
        g.setColor(Color.red);
        // dibujamos el Ã¡rea de un rectÃ¡ngulo
        g.fillRect(820, 110, 20, 20);
        g.setColor(Color.green);
        g.fillRect(820, 140, 20, 20);
        g.setColor(Color.blue);
        g.fillRect(820, 170, 20, 20);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Brutas", 860, 125);
        g.drawString("Impuestos", 860, 155);
        g.drawString("Netas", 860, 185);
        g.setColor(Color.red);
        //genero variables para usarlas al pintar cada grafica
        int xAnt = 100;
        int yAnt = 650;
        int xSig = 0;
        int ySig = 0;
        //divido el total de fechas entre los pixeles a usar
        int xr3 = (int)(700/ventas.length);
        double yr3 = factorEscala(ventas,1,g);
        g.setColor(Color.red);
        pintaGrafica(g,ventas,1,xAnt,yAnt,xSig,ySig,xr3,yr3);
        //reinicio variables al origen de la grafica
        xAnt = 100;
        yAnt = 650;
        xSig = 0;
        ySig = 0;
        xr3 = (int)(700/ventas.length);
        yr3 = factorEscala(ventas,2,g);
        // Se define el componente B.
        g.setColor(Color.blue);
        pintaGrafica(g,ventas,2,xAnt,yAnt,xSig,ySig,xr3,yr3);
        //reinicio variables al origen de la grafica
        xAnt = 100;
        yAnt = 650;
        xSig = 0;
        ySig = 0;
        xr3 = (int)(700/ventas.length);
        yr3 = factorEscala(ventas,3,g);
        // Se define el componente G
        g.setColor(Color.green);
	pintaGrafica(g,ventas,3,xAnt,yAnt,xSig,ySig,xr3,yr3);
        g.dispose();
        try{ImageIO.write(graf, "PNG", png);}
        catch(Exception e){System.out.println("Error al pintar.");}
    }//paintGraph

    /**
     * Metodo que dibuja la gráfica específica de un tipo de dato en un conjuntos de ventas.
     * @param g Es la grafica en la que se dibuja.
     * @param ventas Es el conjunto de ventas de donde se obtiene la información.
     * @param dato Tipo de Venta de la que se genera la gráfica.
     * @param xAnt Es la coordenada X en la gráfica de la recta anterior dibujada.
     * @param yAnt Es la coordenada Y en la gráfica de la recta anterior dibujada.
     * @param xSig Es la coordenada X en la gráfica de la recta siguiene a dibujar.
     * @param ySig Es la coordenada Y en la gráfica de la recta siguiene a dibujar.
     * @param xr3 Es la coordenada X del espacio donde comienza lla recta a graficar.
     * @param yr3 Es la coordenada Y del espacio donde comienza lla recta a graficar.
     */
    public void pintaGrafica(Graphics2D g,Ventas[] ventas,int dato, int xAnt,int yAnt, int xSig,int ySig,int xr3,double yr3){
	if(dato == 1){
	    double minimo = minimoVentas(ventas);
	    double temp;
	    for(int i=0; i<ventas.length;i++){
		xSig = xAnt + xr3;
		temp = yr3*ventas[i].getVentaBruta();
		ySig = 650 - (int)temp;
		if(minimo == ventas[i].getVentaBruta()){
		    g.setColor(Color.gray);
		    g.drawLine(100, ySig, 800, ySig);
		    g.setFont(new Font("comic Sans", Font.PLAIN, 15));
		    g.setColor(Color.black);
		    g.drawString("$" + minimo, 3, ySig);
		    g.setColor(Color.red);
		}

		g.fillOval(xSig-2,ySig-2,5,5);
		g.drawLine(xAnt,yAnt,xSig,ySig);
		xAnt = xSig;
		yAnt = ySig;
	    }
	}
	if(dato == 2){
	    for(int i=0; i<ventas.length;i++){
		xSig = xAnt + xr3;
		double temp = yr3*ventas[i].getVentaNeta();
		ySig = 650 - (int)temp;
		
		g.fillOval(xSig-2,ySig-2,5,5);
		g.drawLine(xAnt,yAnt,xSig,ySig);
		xAnt = xSig;
		yAnt = ySig;
	    }	
	}
	if(dato == 3){
	    for(int i=0; i<ventas.length;i++){
		xSig = xAnt + xr3;
		double temp = yr3*ventas[i].getIva();
		ySig = 650 - (int)temp;
		
		g.fillOval(xSig-2,ySig-2,5,5);
		g.drawLine(xAnt,yAnt,xSig,ySig);
		xAnt = xSig;
		yAnt = ySig;
	    }	
	}

    }

    /**
     * Método que calcula la distancia entre los puntos de venta.
     * @param numVentas Es el total de ventas.
     * @return Devuelve la distancia entre las ventas.
     */
    public int distanciaX(int numVentas){
	return 700/numVentas;
    }

    /**
     * Calcula el valor mínimo de las ventas.
     * @param ventas Es el conjunto de Ventas.
     * @return Devuelve el valor entero de la venta mínima.
     */
    public double minimoVentas(Ventas[] ventas){
	double minimo = ventas[0].getVentaBruta();
	for(int i = 0; i<ventas.length;i++){
	    if(minimo > ventas[i].getVentaBruta()){
		minimo = ventas[i].getVentaBruta();
	    }
	}
	return minimo;
    }

    /**
     * Metodo que cualcula el factor de escala para la altura en pixeles de las ventas en la gráfica.
     * @param ventas Es el conjunto de ventas.
     * @param x Es el tipo de dato de venta.
     * @param g Es la gráfica en la que se dibjua.
     * @return Devuelve el factor de escala para graficar las ventas.
     */
    public double factorEscala(Ventas[] ventas,int x,Graphics2D g){
	double maximo = -1;
	for(int i = 0; i<ventas.length;i++){
	    if(x == 1){
		if(ventas[i].getVentaBruta() > maximo){
		    maximo = ventas[i].getVentaBruta();
		    
		}
	    }
	    if(x == 2){
		if(ventas[i].getVentaNeta() > maximo){
		    maximo = ventas[i].getVentaBruta();
		}
	    }
	    if(x == 3){
		if(ventas[i].getIva() > maximo){
		    maximo = ventas[i].getVentaBruta();
		}
	    }

	}
	if(x == 1){
	    g.setFont(new Font("comic Sans", Font.PLAIN, 15));
	    g.setColor(Color.black);
	    g.drawString("$" + maximo, 3, 150);
	}
	maximo = 500/maximo;


	return maximo;//el numero mas grande entre 500 pixeles a repartir
    }    

}//clase Pintor
