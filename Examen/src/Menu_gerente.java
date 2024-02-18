import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu_gerente extends JFrame {
    private JButton agregarProductosButton;
    private JButton salirButton;
    private JPanel Panel2;
    private JButton verStockButton;

    public Menu_gerente(){
        super("Menu Gerente");
        setContentPane(Panel2);
        setUndecorated(true);

        agregarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agregar_productos productos = new Agregar_productos();
                productos.setSize(400, 400);
                productos.setLocationRelativeTo(null);
                productos.setVisible(true);
                productos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });

        verStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ver_stock stock = new Ver_stock();
                stock.setSize(400, 400);
                stock.setLocationRelativeTo(null);
                stock.setVisible(true);
                stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Menu_gerente.this, "Saliendo de la app");
                dispose();
            }
        });
    }
}
