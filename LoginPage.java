import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DatabaseLoginPage {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";// Your own Database should add.
    private static final String DB_USER = "root";// Your Database user
    private static final String DB_PASSWORD = "Enter password"; // input your own database password

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page");
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(6, 1, 10, 10)); // Adjusted layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the components
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JLabel statusLabel = new JLabel("");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align status

        // Add action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passText.getPassword());

                if (authenticateUser(username, password)) {
                    statusLabel.setText("Login Successful!");
                    statusLabel.setForeground(Color.GREEN);
                } else {
                    statusLabel.setText("Invalid Username or Password");
                    statusLabel.setForeground(Color.RED);
                }
            }
        });

        // Add components to the frame
        frame.add(userLabel);
        frame.add(userText);
        frame.add(passLabel);
        frame.add(passText);
        frame.add(loginButton);
        frame.add(statusLabel);

        frame.setVisible(true);
    }

    private static boolean authenticateUser(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
