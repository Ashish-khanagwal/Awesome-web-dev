package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JFrame { // 'extends' means we're 'sharing' the properties defined in the Main class (e.g, BgColor, size etc.)
    private JLabel header;
    private JButton bookFlight, about_us, login, register; //creating object of JLabel and JButton

    public Welcome(){
        setTitle("Flight Connect"); //setting the title of the frame
        setLayout(new FlowLayout()); //setting a horizontal layout

//      setting size of the JFrame
        setPreferredSize(new Dimension(820,600));
        setMinimumSize(new Dimension(550, 350));
        setMaximumSize(new Dimension(830,600));
        setResizable(false);

        header = new JLabel("<html><span style='color: white;'><br><br><br>Welcome To Flight Connect!<br><br></span></html>");
        header.setFont(header.getFont().deriveFont(40.0f));
        header.setBounds(220, 5, 150, 40);
        add(header);
        //adding the buttons to the frame
        about_us = new JButton("About Us");
        add(about_us);
        bookFlight = new JButton("Book A Flight");
        add(bookFlight);
        login = new JButton("Login");
        add(login);
        register = new JButton("Register");
        add(register);

//      calling the class "aboutUs" by adding an action listener to the button "about_us"
        about_us.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutUs aboutUs = new aboutUs();
                Container content = aboutUs.getContentPane();
                Color myColor = new Color(168, 164, 162, 255);
                content.setBackground(myColor);
                content.setLayout(new GridLayout(2,2));
                aboutUs.setResizable(true);
                aboutUs.setVisible(true);
            }
        });

//      calling the classes by adding action listeners to the buttons

        bookFlight.addActionListener(e -> {
            userDetails det = new userDetails();
            Container content = det.getContentPane();
            Color mycolor = new Color(168, 164, 162, 255);
            det.setSize(700, 750);
            content.setBackground(mycolor);
            content.setLayout(new GridLayout(0, 1));
            det.setVisible(true);
        });

        login.addActionListener(e -> {
            LoginFrame loginFrame = new LoginFrame();
            Container content = loginFrame.getContentPane();
            loginFrame.setVisible(true);
        });

        register.addActionListener(e -> {
            RegisterFrame registerFrame = new RegisterFrame();
            Container content = registerFrame.getContentPane();;
            registerFrame.setVisible(true);
        });
    }
}