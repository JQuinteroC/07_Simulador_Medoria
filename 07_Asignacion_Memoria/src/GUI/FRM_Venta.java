package GUI;

import LOGIC.Controlador;
import java.awt.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class FRM_Venta extends JFrame {

    JLabel lblTitulo = new JLabel("Asignación de memoria");

    public JButton btnBest = new JButton("Best");
    public JButton btnWorst = new JButton("Worst");
    public JButton btnFirst = new JButton("First");
    public JButton btnNext = new JButton("Next");
    public JButton btnDelete= new JButton("Borrar");

    public JTextField txtNombre = new JTextField();

    public JSpinner spnTamano = new JSpinner(new SpinnerNumberModel(0, 0, 48, 1));

    public Container cont = new Container();
    public JScrollPane sp = new JScrollPane(cont);

    Font fuente = new Font("verdana", Font.PLAIN, 13);

    public void mostrar() {
        lblTitulo.setFont(new java.awt.Font("Verdana", 1, 25));
        lblTitulo.setBounds(70, 5, 350, 45);

        txtNombre.setBounds(10, 55, 90, 25);
        txtNombre.setFont(fuente);

        spnTamano.setBounds(10, 90, 90, 25);
        spnTamano.setFont(fuente);

        btnBest.setBounds(10, 125, 90, 25);
        btnBest.setFont(fuente);

        btnWorst.setBounds(10, 160, 90, 25);
        btnWorst.setFont(fuente);

        btnFirst.setBounds(10, 195, 90, 25);
        btnFirst.setFont(fuente);

        btnNext.setBounds(10, 230, 90, 25);
        btnNext.setFont(fuente);

        btnDelete.setBounds(10, 265, 90, 25);
        btnDelete.setFont(new Font("verdana", Font.BOLD, 13));

        sp.setBounds(110, 55, 320, 430);

        setSize(455, 530);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Asignación de memoria");
    }

    public FRM_Venta() throws HeadlessException {
        java.awt.Container c;
        c = getContentPane();

        c.setLayout(null);
        c.add(lblTitulo);
        c.add(txtNombre);
        c.add(spnTamano);
        c.add(btnBest);
        c.add(btnWorst);
        c.add(btnFirst);
        c.add(btnNext);
        c.add(btnDelete);
        c.add(sp);
    }

    public void asignaOyentes(Controlador c) {
        btnBest.addActionListener(c);
        btnWorst.addActionListener(c);
        btnFirst.addActionListener(c);
        btnNext.addActionListener(c);
        btnDelete.addActionListener(c);
    }
}
