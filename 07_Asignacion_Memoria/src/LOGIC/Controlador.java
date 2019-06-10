package LOGIC;

import GUI.FRM_Venta;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Controlador implements ActionListener {

    FRM_Venta v;
    int index = 0;
    ArrayList<Integer> espacios = new ArrayList(); // pares tamaño, impares posición
    ArrayList<String> nombres = new ArrayList();

    public Controlador(FRM_Venta v) throws IOException {
        this.v = v;

        dibujarBase(0, v);
        llenadoBase(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (comprobarExt()) {
            int size = (int) v.spnTamano.getValue();
            int[] pos = new int[2];
            pos[0] = -1;
            pos[1] = -1;
            if (e.getSource().equals(v.btnBest)) {

            } else if (e.getSource().equals(v.btnFirst)) {
                for (int i = 0; i < espacios.size(); i = i + 2) {
                    if (espacios.get(i) >= size) {
                        pos[0] = espacios.get(i + 1);
                        pos[1] = i;
                        break;
                    }
                }
                try {
                    actualizarDibujo(pos, v.txtNombre.getText(), size, v);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al editar la memoria");
                }
            } else if (e.getSource().equals(v.btnNext)) {

            } else if (e.getSource().equals(v.btnWorst)) {
               //
            } else if (e.getSource().equals(v.btnDelete)) {
                v.cont.removeAll();
                v.sp.repaint();

                llenadoBase(true);
            }
        } else if (e.getSource().equals(v.btnDelete)) {
            v.cont.removeAll();
            v.sp.repaint();

            llenadoBase(true);
        }

    }

    void dibujarBase(int i, FRM_Venta d) throws IOException {
        // Dimensiones
        int ancho = 100;
        int alto = 348;

        // Imagen
        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics k = imagen.getGraphics();

        // Dibujo
        k.setColor(Color.white);
        k.fillRect(0, 0, 100, 348);
        k.setColor(Color.black);
        k.drawRect(15, 5, 80, 336);
        k.drawRect(15, 5, 80, 56);
        k.drawRect(15, 61, 80, 56);
        k.drawRect(15, 117, 80, 56);
        k.drawRect(15, 173, 80, 14);
        k.drawRect(15, 187, 80, 70);
        k.drawRect(15, 257, 80, 56);
        k.drawRect(15, 313, 80, 28);

        // Texto
        k.drawString("OS 8m", 20, 58);
        k.drawString("p1 8m", 20, 115);
        k.drawString("p3 2m", 20, 185);
        k.drawString("p5 8m", 20, 310);

        k.drawString("0", 6, 15);
        k.drawString("8", 6, 61);
        k.drawString("16", 1, 117);
        k.drawString("24", 1, 173);
        k.drawString("26", 1, 187);
        k.drawString("36", 1, 257);
        k.drawString("44", 1, 313);
        k.drawString("48", 1, 341);

        // Guardar imagen
        try {
            ImageIO.write(imagen, "jpg", new File("estado" + i + ".jpg"));
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }
    }

    void llenadoBase(boolean nuevo) {
        // Memoria base
        Image img = new ImageIcon("estado0.jpg").getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(100, 390, Image.SCALE_SMOOTH));

        JLabel d = new JLabel(img2);
        d.setBounds(10, 3, 100, 390);
        v.cont.add(d);
        v.sp.repaint();

        if (nuevo) {
            // Arreglos
            // Espacios
            espacios.clear();
            espacios.add(8);
            espacios.add(16);

            espacios.add(10);
            espacios.add(26);

            espacios.add(4);
            espacios.add(44);

            // Nombres
            nombres.clear();
            nombres.add("OS");
            nombres.add("p1");
            nombres.add("p3");
            nombres.add("p5");
            index = 0;
        }
    }

    boolean comprobarExt() {
        String n = v.txtNombre.getText();
        int va = (int) v.spnTamano.getValue();
        if (n.isEmpty()) {
            return false;
        } else if (va == 0) {
            return false;
        }

        if (nombres.contains(n)) {
            JOptionPane.showMessageDialog(null, "El nombre " + n + " ya esta en memoria");
            v.txtNombre.setText(null);
            return false;
        } else {
            boolean pru = true;
            for (int i = 0; i < espacios.size(); i = i + 2) {
                if (espacios.get(i) >= va) {
                    pru = true;
                    break;
                } else {
                    pru = false;
                }
            }

            if (!pru) {
                JOptionPane.showMessageDialog(null, "No hay suficiente tamaño en memoria");
                v.spnTamano.setValue(0);
            }
            return pru;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////
    void actualizarDibujo(int pos[], String name, int size, FRM_Venta d) throws IOException {
        actualizar(pos, name, size);

        // Imagen
        BufferedImage imagenref = ImageIO.read(new File("estado" + index + ".jpg"));
        Graphics l = imagenref.getGraphics();

        int tam = 0;
        if (size == 1) {
            tam = 18;
        } else {
            tam = size * 7;
        }
        BufferedImage imagen = new BufferedImage(80, tam, BufferedImage.TYPE_INT_RGB);
        Graphics k = imagen.getGraphics();

        int tem = (pos[0] * 7) + 5;
        int y = tem + (size * 7) - 3;
        // Dibujo
        k.setColor(Color.white);
        k.fillRect(0, 0, 80, tam);
        k.setColor(Color.red);
        k.drawRect(0, 0, 80, tam - 1);

        // Texto
        k.drawString(name + " " + size + "m", 5, tam - 3);

        l.drawImage(imagen.getScaledInstance(80, (size * 7) + 1, Image.SCALE_SMOOTH), 15, tem, null);
        // Guardar imagen
        try {
            ImageIO.write(imagenref, "jpg", new File("estado" + (index + 1) + ".jpg"));
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }

        v.cont.removeAll();
        v.sp.repaint();
        llenadoBase(false);
        Image img = new ImageIcon("estado" + (index + 1) + ".jpg").getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(100, 390, Image.SCALE_SMOOTH));
        JLabel lab = new JLabel(img2);
        lab.setBounds(130, 3, 100, 390);
        d.cont.add(lab);
        d.sp.repaint();
        index++;
    }

    void actualizar(int pos[], String name, int size) {
        nombres.add(name);

        if (size == espacios.get(pos[1])) {
            espacios.remove(pos[1] + 1);
            espacios.remove(pos[1]);
        } else {
            int ta = espacios.get(pos[1]) - size;
            espacios.set(pos[1], ta);
            espacios.set(pos[1] + 1, espacios.get(pos[1] + 1) + size);
        }
    }
}
