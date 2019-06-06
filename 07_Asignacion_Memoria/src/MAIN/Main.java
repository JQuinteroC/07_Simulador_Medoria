package MAIN;

import GUI.FRM_Venta;
import LOGIC.Controlador;

/**
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Main {

    public static void main(String[] args) {
        FRM_Venta vv = new FRM_Venta();
        Controlador u = new Controlador(vv);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vv.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        vv.mostrar();
        vv.asignaOyentes(u);
    }
}
