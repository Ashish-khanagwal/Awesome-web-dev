package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Welcome welcome = new Welcome(); //calling the Welcome class
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when the user clicks on exit(X), the program ends.
        Container content = welcome.getContentPane();
        Color myColor = new Color(128, 128, 128, 255); //setting the background color of the welcome page
        content.setBackground(myColor);
        welcome.setVisible(true); //setting the visibility of the welcome page
        welcome.setSize(600, 500); //Setting the size of the Container (JFrame)
    }
}