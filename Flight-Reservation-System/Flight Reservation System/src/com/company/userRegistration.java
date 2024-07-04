package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


class RegisterFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("REGISTER");


    RegisterFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(loginButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = userTextField.getText();
        //noinspection deprecation
        String pwdText = passwordField.getText();

        //connecting to the database
        try {
            //where testlogin = your table name, login = your database name, root = your username and "" = your password
            String query = "INSERT INTO `testlogin`(`username`, `password`) VALUES (?, ?)";
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login",
                    "root", "");
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
            statement.setString(1,userText);
            statement.setString(2,pwdText);
            JOptionPane.showMessageDialog(null, "Registration Successful!");
            statement.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Something went wrong");

        }
    }
}


class Register {
    public static void main(String[] a) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Registration Form");
        frame.setBounds(10, 10, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }
}
