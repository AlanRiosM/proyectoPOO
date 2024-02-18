import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ver_stock extends JFrame{
    private JTable table1;
    private JPanel Panel5;
    private JButton menuButton;

    public Ver_stock(){
        super("Ver Stock");
        setContentPane(Panel5);
        setUndecorated(true);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minimarket", "root", "123456");

            String sql = "SELECT * FROM productos";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            DefaultTableModel model = new DefaultTableModel();

            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                model.addColumn(metaData.getColumnName(columnIndex));
            }

            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = resultSet.getObject(i + 1);
                }
                model.addRow(rowData);
            }

            resultSet.close();
            statement.close();
            connection.close();

            table1.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos");
        }

        menuButton.addActionListener(new ActionListener() {
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
