package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


@SuppressWarnings("ALL")
class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel user_name = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField user_nameTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");

    LoginFrame() {
        //calling the methods below
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        user_name.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        user_nameTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(user_name);
        container.add(passwordLabel);
        container.add(user_nameTextField);
        container.add(passwordField);
        container.add(loginButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = user_nameTextField.getText(); //getting text entered in the username and password field
        String pwdText = passwordField.getText();

        //Connecting to the database
        try {
            //where login = your database name, root = your username and "" = your password
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login",
                    "root", "");
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement("Select username, password FROM testLogin WHERE username=? and password=?");
            statement.setString(1,userText);
            statement.setString(2,pwdText);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dispose();
                JOptionPane.showMessageDialog(null, "You have successfully logged in!");
                checkReservation checkReservation = new checkReservation(); //navigating to new page
            }else {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        }
    }


class Login {
    public static void main(String[] a) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login");
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
