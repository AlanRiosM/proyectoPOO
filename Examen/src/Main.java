import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        login.setSize(400, 400);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}