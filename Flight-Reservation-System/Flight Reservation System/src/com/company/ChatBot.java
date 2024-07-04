package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ChatBot extends JFrame {
    //Typing Bar
    private JTextField inputBar = new JTextField();

    //Chat Output Area
    private JTextArea chatOutput = new JTextArea();


    public ChatBot() {
        //Creating the Frame
        this.setSize(600, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // HIDE_ON_CLOSE to prevent ChatBot from stopping the entire program when closed


        // Setting GUI title
        this.setTitle("Customer Service ChatBot");
        botOutput("Hello!, my name is Alice," + "\n" + "I'm the Flight Connect chat bot" + "\n" + "\n" + "How can I help you today?" + "\n" + "\n");



        // creating inputBar
        inputBar.setLocation(2, 540);
        inputBar.setSize(580, 30);

        //inputBar Action Event
        inputBar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String userInput = inputBar.getText();
                chatOutput.append("You: " + userInput + "\n");

                if(userInput.contains("cancel")){
                    botOutput("Please Enter your ID number and reservation date" + "\n" + "\n");
                }
                else if(userInput.contains("change")){
                    botOutput("Please Enter your ID number and new flight date" + "\n" + "\n");

                }
                else if(userInput.contains("trouble")|| userInput.contains("booking")){
                    botOutput("Please call 09037384629 for quick response" + "\n" + "\n");

                }
                else if(userInput.contains("speak")|| userInput.contains("representative")){
                    botOutput("A rep will be right with you!" + "\n" + "\n");

                }
                else if(userInput.contains("password")|| userInput.contains("username")){
                    botOutput("please Enter your ID number and email address" + "\n" + "\n");

                } else{
                    botOutput("Sorry, I don't understand you" + "\n" + "\n");
                }

                inputBar.setText("");
            }
        });

        //chatOutput
        chatOutput.setLocation(15, 5);
        chatOutput.setSize(560, 510);
        chatOutput.setLineWrap(true);
        chatOutput.setEditable(false);

        //Frame items
        this.add(inputBar);
        this.add(chatOutput);
    }

    public void botOutput(String s){
        chatOutput.append("Alice: " + s + "\n");
    }

    public static void main(String[] args){
        new ChatBot();
    }

}
