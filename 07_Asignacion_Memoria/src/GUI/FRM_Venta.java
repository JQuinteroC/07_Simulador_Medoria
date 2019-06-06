package GUI;

import LOGIC.Controlador;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
    
    public JSpinner spnTamano = new JSpinner(new SpinnerNumberModel(0,0,48,1));
    
    public JScrollPane sp = new JScrollPane();

    Font fuente = new Font("verdana", Font.PLAIN, 13);

    public void mostrar() {
        lblTitulo.setFont(new java.awt.Font("Verdana", 1, 25));
        lblTitulo.setBounds(100, 5, 350, 45);

        spnTamano.setBounds(10, 55, 50, 25);
        spnTamano.setFont(fuente);

        btnBest.setBounds(70, 55, 100, 25);
        btnBest.setFont(fuente);

        btnWorst.setBounds(180, 55, 100, 25);
        btnWorst.setFont(fuente);

        btnFirst.setBounds(290, 55, 100, 25);
        btnFirst.setFont(fuente);

        btnNext.setBounds(400, 55, 100, 25);
        btnNext.setFont(fuente);
        
        sp.setBounds(10, 90, 490, 260);

        setSize(525, 400);
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
        c.add(spnTamano);
        c.add(btnBest);
        c.add(btnWorst);
        c.add(btnFirst);
        c.add(btnNext);
        c.add(sp);
    }

    public void asignaOyentes(Controlador c) {
        btnBest.addActionListener(c);
        btnWorst.addActionListener(c);
        btnFirst.addActionListener(c);
        btnNext.addActionListener(c);
    }
}
