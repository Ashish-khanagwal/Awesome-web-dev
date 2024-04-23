package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class creditCard extends JFrame {
    private JLabel cardTypeL, cardNumL, cardHolderL, cardExpDateL, accountNumL;
    private JTextField cardNumTF, cardHolderTF, cardExpDateTF, accountNumTF ;
    private JComboBox cardTypesJC;
    private JButton confirm;
    String cardOptions[] = {"Master Card", "Debit Card", "Credit Card", "Visa"};

    public creditCard(){
        super("Validate");

        //create a new JPanel
        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(20, 20, 20, 20);


        //assign values to components
        cardHolderL = new JLabel("Card Holder: ");
        cardHolderTF = new JTextField(15);


        cardNumL = new JLabel("Card Number: ");
        cardNumTF = new JTextField(15);

        cardExpDateL = new JLabel("Expiry Date: ");
        cardExpDateTF = new JTextField(15);

        accountNumL = new JLabel("Account Number");
        accountNumTF = new JTextField(15);

        cardTypeL = new JLabel("Card Type: ");
        cardTypesJC = new JComboBox(cardOptions);

        confirm = new JButton("Confirm");

        //add components to panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(cardHolderL, constraints);

        constraints.gridx = 1;
        newPanel.add(cardHolderTF, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(cardNumL, constraints);

        constraints.gridx = 1;
        newPanel.add(cardNumTF, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(cardExpDateL, constraints);

        constraints.gridx = 1;
        newPanel.add(cardExpDateTF, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(accountNumL, constraints);

        constraints.gridx = 1;
        newPanel.add(accountNumTF, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        newPanel.add(cardTypeL, constraints);

        constraints.gridx = 1;
        newPanel.add(cardTypesJC, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(confirm, constraints);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saved!");
                dispose();
            }
        });

//      set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Card Details")
        );

//      add panel to frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);

    }
}
