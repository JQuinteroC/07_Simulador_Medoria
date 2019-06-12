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
    int last = 26;
    ArrayList<Integer> espacios = new ArrayList(); // pares tamaño, impares posición
    ArrayList<String> nombres = new ArrayList();

    public Controlador(FRM_Venta v) throws IOException {
        this.v = v;

        dibujarBase(0, v);
        llenadoBase(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(v.btnDelete)) {
            v.cont.removeAll();
            v.sp.repaint();

            llenadoBase(true);
        } else if (comprobarExt()) {
            int size = (int) v.spnTamano.getValue();
            int[] pos = new int[2];
            pos[0] = -1;
            pos[1] = -1;
            if (e.getSource().equals(v.btnBest)) {
                int menor = 48;
                for (int i = 0; i < espacios.size(); i = i + 2) {
                    if (espacios.get(i) - size >= 0 && espacios.get(i) - size < menor) {
                        menor = espacios.get(i) - size;
                        pos[0] = espacios.get(i + 1);
                        last = pos[0] + size;
                        pos[1] = i;
                    }
                }
                try {
                    actualizarDibujo(pos, v.txtNombre.getText(), size, v);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al editar la memoria");
                }
            } else if (e.getSource().equals(v.btnFirst)) {
                for (int i = 0; i < espacios.size(); i = i + 2) {
                    if (espacios.get(i) >= size) {
                        pos[0] = espacios.get(i + 1);
                        last = pos[0] + size;
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
                boolean a = true;
                for (int i = 1; i < espacios.size(); i = i + 2) {
                    int tem = i;
                    if (espacios.get(tem) == last) {
                        pos[0] = espacios.get(tem);
                        if (espacios.get(tem) + size == 48) {
                            last = espacios.get(1) + size;
                            pos[0] = espacios.get(1);
                            pos[1] = 0;
                        } else {
                            last = pos[0] + size;
                            pos[1] = tem - 1;
                        }
                        if (espacios.get(tem - 1) >= size) {
                            try {
                                actualizarDibujo(pos, v.txtNombre.getText(), size, v);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Error al editar la memoria");
                            }
                            a = false;
                            break;
                        } else {
                            last = pos[0];
                        }
                    }
                }

                if (a) {
                    int posi = 0;
                    if (last == espacios.get((int) (espacios.size() - .1)) || last == 48) {
                        last = espacios.get(1) + size;
                        pos[0] = espacios.get(1);
                        pos[1] = 0;
                    } else {
                        posi = pos[1];
                    }
                    for (int i = posi; i < 2 * espacios.size(); i = i + 2) {
                        int tem = i % espacios.size();
                        if (espacios.get(tem) >= size) {
                            pos[0] = espacios.get(tem + 1);
                            last = pos[0] + size;
                            pos[1] = tem;
                            try {
                                actualizarDibujo(pos, v.txtNombre.getText(), size, v);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Error al editar la memoria");
                            }
                            break;
                        }
                    }
                }

            } else if (e.getSource().equals(v.btnWorst)) {
                int may = espacios.get(0);
                for (int i = 0; i < espacios.size(); i = i + 2) {
                    if (espacios.get(i) >= may) {
                        may = espacios.get(i);
                        pos[0] = espacios.get(i + 1);
                        pos[1] = i;
                        last = pos[0] + size;
                    }
                }
                try {
                    actualizarDibujo(pos, v.txtNombre.getText(), size, v);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al editar la memoria");
                }
            }
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
        k.drawString("p5 2m", 20, 185);
        k.drawString("p3 8m", 20, 310);

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
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(140, 420, Image.SCALE_SMOOTH));

        JLabel d = new JLabel(img2);
        d.setBounds(10, 3, 140, 420);
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
            nombres.add("p5");
            nombres.add("p3");
            index = 0;
            last = 26;
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
            v.txtNombre.requestFocus();
            return false;
        } else if (espacios.isEmpty()) {
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
                v.spnTamano.requestFocus();
            }
            return pru;
        }
    }

    void actualizarDibujo(int pos[], String name, int size, FRM_Venta d) throws IOException {
        actualizar(pos, name, size);

        // Imagen
        BufferedImage imagenref = ImageIO.read(new File("estado" + index + ".jpg"));
        Graphics l = imagenref.getGraphics();

        int tam;
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
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(140, 420, Image.SCALE_SMOOTH));
        JLabel lab = new JLabel(img2);
        lab.setBounds(170, 3, 140, 420);
        d.cont.add(lab);
        d.sp.repaint();
        index++;
    }

    void actualizar(int pos[], String name, int size) {
        if (!espacios.isEmpty()) {
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
}
