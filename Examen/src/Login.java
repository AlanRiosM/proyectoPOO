import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    private JButton ingresarButton;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JPanel Panel1;

    public Login(){
        super("Login");
        setContentPane(Panel1);
        setUndecorated(true);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = textField1.getText();
                String password = new String(passwordField1.getPassword());

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minimarket", "root", "123456");

                    String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, user);
                    statement.setString(2, password);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(Login.this, "Bienvenido");

                        String rol = resultSet.getString("rol");
                        switch (rol) {
                            case "gerente":
                                Menu_gerente gerente = new Menu_gerente();
                                gerente.setSize(400, 400);
                                gerente.setLocationRelativeTo(null);
                                gerente.setVisible(true);
                                gerente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                dispose();
                                break;
                            case "cliente":
                                Menu_cliente cliente = new Menu_cliente();
                                cliente.setSize(400, 400);
                                cliente.setLocationRelativeTo(null);
                                cliente.setVisible(true);
                                cliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                dispose();
                                break;
                            default:
                                JOptionPane.showMessageDialog(Login.this, "Rol no válido.");
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(Login.this, "Usuario o contraseña incorrectos.");
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Login.this, "Error al intentar acceder a la base de datos.");
                }
            }
        });
    }

}
