import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Agregar_productos extends JFrame {
    private JComboBox<String> comboBox1;
    private JPanel Panel4;
    private JTextField textField1;
    private JButton regresarAlMenuButton;
    private JButton ingresarProductosButton;

    public Agregar_productos(){
        super("Agregar Productos");
        setContentPane(Panel4);
        setUndecorated(true);


        ingresarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String productoSeleccionado = (String) comboBox1.getSelectedItem();
                int cantidadAgregar = Integer.parseInt(textField1.getText());

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minimarket", "root", "123456");
                    String sql = "UPDATE Productos SET stock = stock + ? WHERE nombre = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, cantidadAgregar);
                    statement.setString(2, productoSeleccionado);
                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(Agregar_productos.this, "Stock actualizado correctamente.");
                        textField1.setText("");
                    } else {
                        JOptionPane.showMessageDialog(Agregar_productos.this, "No se pudo actualizar el stock del producto.");
                    }

                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Agregar_productos.this, "Error al conectar con la base de datos.");
                }
            }
        });

        regresarAlMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_gerente gerente = new Menu_gerente();
                gerente.setSize(400, 400);
                gerente.setLocationRelativeTo(null);
                gerente.setVisible(true);
                gerente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }
        });
    }
}
