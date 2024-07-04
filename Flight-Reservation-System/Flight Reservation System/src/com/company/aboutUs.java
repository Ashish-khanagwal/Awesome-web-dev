package com.company;

import javax.swing.*;
import java.awt.*;

class aboutUs extends JFrame {

    aboutUs(){
        setTitle("About Us");
        setPreferredSize(new Dimension(800, 800));
        setMinimumSize(new Dimension(600, 700));

        JLabel info = new JLabel("<html><h1>About Flight Connect</h1>" +
                "<p>Flight Connect is an online travel agency that helps today’s business and leisure travellers search and book the best flight options with all your favourite airlines.\n" +
                "\n" +
                "Now based in Nasarawa, Flight Connect operates in 8 countries with offices in other core markets including Egypt and Dubai. Our travel family consists of 170 staff spread across 8 cities, all passionate about making travel simple in Africa and the Middle East.\n" +
                "\n" +
                "With a huge focus on affordable travel and simplifying the travel booking experience for our customers, register now to find some of the lowest fares around, pay quickly and safely online with your preferred payment method and you’re off!</p>" +
                "<br><br></html>");
        add(info);
        JLabel space = new JLabel("");
        add(space);
        JButton chat_bot = new JButton("Contact us");
        chat_bot.setSize(100,65);
        add(chat_bot);

//      calling the class "ChatBot" by adding an action listener to the button "chat_bot"
        chat_bot.addActionListener(e -> {
            ChatBot chatBot = new ChatBot();
            Container content = chatBot.getContentPane();
            content.setVisible(true);
        });

    }
}
