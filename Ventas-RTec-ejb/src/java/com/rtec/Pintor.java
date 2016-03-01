package com.rtec;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;

/**
 * Dibuja una gr&aacute;fica y la guarda en un PNG en el directorio <tt>img</tt>
 * del server.
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
     * @throws IOException - Si ocurre un problema al crear el archivo PNG.
     * @since Pintor 1.0, february 2016
     */
    public void paintGraph() throws IOException {
        // creamos un buffer de imagen con dimenciones y propiedades dadas
        BufferedImage graf =
                new BufferedImage(1000, 500, BufferedImage.TYPE_4BYTE_ABGR);
        // comprobamos si existe el directorio img
        // creamos el archivo donde vamos aguardar la imagen
        //la ruta es dependiendo de donde tengan alojado el proyecto, en mi caso 
        //esta en dropbox     
        File png = Paths.get("/", "home", "user", "Dropbox","riesgoTec","Practica1",
                "Ventas-RTec", "Ventas-RTec-war", "build", "web", "img",
                "grafica.png").toFile();
        png.mkdirs();
        png.createNewFile();
        // recuperamos los gráficos para empezar a dibujar
        Graphics2D g = graf.createGraphics();
        // asignamos un color para el dibujado
        g.setColor(Color.black);
        // dibujamos los ejes
        g.drawLine(50, 450, 50, 50);
        g.drawLine(50, 450, 750, 450);
        // asignamos una fuente para el texto
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        // dibujamos una cadena en la posición dada
        g.drawString("Tiempo", 380, 480);
        // respaldamos la configuración original del plano donde trabajamos
        AffineTransform original = g.getTransform();
        // trasladamos el plano
        g.translate(0, 280);
        // rotamos 90 grados en sentido anti-horario.
        g.rotate(-Math.PI /2);
        g.drawString("Ventas", 0, 25);
        // restauramos la configuración original del plano
        g.setTransform(original);
        // dibujamos el perímetro de un rectángulo
        g.drawRect(790, 100, 200, 100);
        // cambiamos el color de dibujado
        g.setColor(Color.red);
        // dibujamos el área de un rectángulo
        g.fillRect(810, 110, 20, 20);
        g.setColor(Color.green);
        g.fillRect(810, 140, 20, 20);
        g.setColor(Color.blue);
        g.fillRect(810, 170, 20, 20);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Brutas", 850, 125);
        g.drawString("Impuestos", 850, 155);
        g.drawString("Netas", 850, 185);
        g.setColor(Color.red);
        g.drawLine(50, 450, 100, 275);
        g.drawLine(100, 275, 180, 250);
        g.drawLine(180, 250, 260, 175);
        g.drawLine(260, 175, 340, 325);
        g.drawLine(340, 325, 420, 300);
        g.drawLine(420, 300, 500, 350);
        g.drawLine(500, 350, 580, 275);
        g.drawLine(580, 275, 660, 250);
        g.drawLine(660, 250, 740, 200);
        g.setColor(Color.green);
        g.drawLine(50, 450, 100, 400);
        g.drawLine(100, 400, 180, 375);
        g.drawLine(180, 375, 260, 350);
        g.drawLine(260, 350, 340, 425);
        g.drawLine(340, 425, 420, 400);
        g.drawLine(420, 400, 500, 425);
        g.drawLine(500, 425, 580, 400);
        g.drawLine(580, 400, 660, 375);
        g.drawLine(660, 375, 740, 350);
        g.setColor(Color.blue);
        g.drawLine(50, 450, 100, 325);
        g.drawLine(100, 325, 180, 300);
        g.drawLine(180, 300, 260, 250);
        g.drawLine(260, 250, 340, 375);
        g.drawLine(340, 375, 420, 350);
        g.drawLine(420, 350, 500, 375);
        g.drawLine(500, 375, 580, 325);
        g.drawLine(580, 325, 660, 300);
        g.drawLine(660, 300, 740, 250);
        g.dispose();
        ImageIO.write(graf, "PNG", png);
    }//paintGraph
    
}//clase Pintor
