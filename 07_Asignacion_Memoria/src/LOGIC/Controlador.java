package LOGIC;

import GUI.FRM_Venta;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Controlador implements ActionListener {

    FRM_Venta v;

    public Controlador(FRM_Venta v) {
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(v.btnBest)) {

        } else if (e.getSource().equals(v.btnFirst)) {

        } else if (e.getSource().equals(v.btnNext)) {

        } else if (e.getSource().equals(v.btnWorst)) {

        }

    }

    void dibujarBase(int i, FRM_Venta d, boolean[] pre, boolean[] pos) {
        // Dimensiones
        int ancho = 100;
        int alto = 240;

        // Imagen
        
        BufferedImage imagen = new BufferedImage(ancho + 1, alto + 1, BufferedImage.TYPE_INT_RGB);
        ///BufferedImagen imagen = ImageIO.read(new File("imagen.png"));

        Graphics k = imagen.getGraphics();

        // Nombre
        k.setColor(Color.white);
        k.fillRect(0, 0, 100, 240);///
        k.setColor(Color.black);
        k.drawRect(0, 0, 80, 240);

        try {
            ImageIO.write(imagen, "jpg", new File("imgs/estado" + i + ".jpg"));
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }
    }

}
