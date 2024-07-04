package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class checkReservation implements ActionListener {

    JFrame frame1;//creating object of first JFrame
    JLabel nameLabel;//creating object of JLabel
    JTextField nameTextField;//creating object of JTextfield
    JButton fetchButton;//creating object of JButton
    JButton resetButton;//creating object of JButton

    JFrame frame2;//creating object of second JFrame
    DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
    JTable table;//Creating object of JTable
    Connection connection;//Creating object of Connection class
    Statement statement;//Creating object of Statement class
    int flag=0;


    checkReservation() {

        frame1 = new JFrame();
        frame1.setTitle("Search Reserved Flights");//setting the title of first JFrame
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setting default close operation
        GridBagLayout bagLayout = new GridBagLayout();//creating object of GridBagLayout
        GridBagConstraints bagConstraints = new GridBagConstraints();//creating object of GridBagConstraints
        frame1.setSize(500, 300);//setting the size of first JFrame
        frame1.setLayout(bagLayout);//setting the layout to GridBagLayout of first JFrame

        bagConstraints.insets = new Insets(15, 40, 0, 0);//Setting the padding between the components and neighboring components

        //Setting the property of JLabel and adding it to the first JFrame
        nameLabel = new JLabel("Enter Name");
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        frame1.add(nameLabel, bagConstraints);

        //Setting the property of JTextfield and adding it to the first JFrame
        nameTextField = new JTextField(15);
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 0;
        frame1.add(nameTextField, bagConstraints);

        //Setting the property of JButton(Fetch Data) and adding it to the first JFrame
        fetchButton = new JButton("Fetch Data");
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.ipadx = 60;
        frame1.add(fetchButton, bagConstraints);

        //Setting the property of JButton(Reset Data) and adding it to the second JFrame
        resetButton = new JButton("Reset Data");
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 1;
        frame1.add(resetButton, bagConstraints);

        //adding ActionListener to both buttons
        fetchButton.addActionListener(this);
        resetButton.addActionListener(this);


        frame1.setVisible(true);//Setting the visibility of First JFrame
        frame1.validate();//Performing relayout of the First JFrame


    }

    public static void main(String[] args) {
        new checkReservation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fetchButton) {

            String userName = nameTextField.getText().toString();//getting text of username text field and storing it in a String variable
            frameSecond(userName);//passing the text value to frameSecond method

        }
        if (e.getSource() == resetButton) {
            nameTextField.setText("");//resetting the value of username text field
        }

    }


    public void frameSecond(String userName) {

        //setting the properties of second JFrame
        frame2 = new JFrame("Results");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(600, 600);

        //Setting the properties of JTable and DefaultTableModel
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));
        defaultTableModel.addColumn("Name");
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Mode_of_payment");
        defaultTableModel.addColumn("Flying_to");




        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");//Crating connection with database
            statement = connection.createStatement();//crating statement object
            String query = "select * from reserved_flight where Name = '" + userName + "'";//Storing MySQL query in A string variable
            ResultSet resultSet = statement.executeQuery(query);//executing query and storing result in ResultSet


            while (resultSet.next()) {

                //Retrieving details from the database and storing it in the String variables
                String name = resultSet.getString("Name");
                String id = resultSet.getString("ID");
                String mode = resultSet.getString("Mode_of_payment");
                String destination = resultSet.getString("Flying_to");
                if (userName.equalsIgnoreCase(name)) {
                    flag = 1;
                    defaultTableModel.addRow(new Object[]{name, id, mode, destination});//Adding row in Table
                    frame2.setVisible(true);//Setting the visibility of second Frame
                    frame2.validate();
                    break;
                }

            }

            if (flag == 0) {
                JOptionPane.showMessageDialog(null, "User does not exist!");//When invalid username is entered
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}