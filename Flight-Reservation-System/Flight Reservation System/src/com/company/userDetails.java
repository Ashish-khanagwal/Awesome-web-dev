package com.company;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

public class userDetails extends JFrame {

    //creating object of the JComponents below
    public JLabel fullName, addressLabel, contactLabel, classTypeLabel, paymentLabel, flightTypeLabel, travelDate, returnDate, currentLabel, destinationLabel, seatLabel, airLineLabel;
    public JTextField fullNameTF, addressTF, contactTF, seatTF;
    public JComboBox classTypeJC, paymentJC, flightTypeJC, currentCityJC, destinationCityJC, airLineTypeJC;
    public JDatePanelImpl travelDatePanel, returnDatePanel;
    public JDatePickerImpl travelDatePicker, returnDatePicker;
    public JButton total, close, submit;
    Random random = new Random();
    FlightDuration flightDuration = new FlightDuration();
    public int Rid = 100000000 + random.nextInt(999999999); //generating random ID numbers
    public int total_cost;

    public userDetails() {
        setTitle("User/Payment Details");
        setPreferredSize(new Dimension(900, 600));
        setMinimumSize(new Dimension(700, 500));
        setSize(700,750);
        setLayout(new GridLayout(0,1));

        airLineLabel = new JLabel("Choose Airline:");
        add(airLineLabel);
        String[] airLines = {"Air Peace(APK)", "Allied Air(AJK)", "Dana Air(DAN)", "Max Air(NGL)", "Ibom Air(IAN)", "Arik Air(ARA)", "Azman Air(AZM)", "Kabo Air(QNK)", "Aero Contractors(ACN)"};
        airLineTypeJC = new JComboBox(airLines);
        add(airLineTypeJC);

        fullName = new JLabel("Enter Full name:");
        add(fullName);
        fullNameTF = new JTextField();
        add(fullNameTF);

        addressLabel = new JLabel("Enter Home Address:");
        add(addressLabel);
        addressTF = new JTextField();
        add(addressTF);

        seatLabel = new JLabel("Enter number of passengers:");
        add(seatLabel);
        seatTF = new JTextField();
        add(seatTF);

        contactLabel = new JLabel("Enter Phone Number:");
        add(contactLabel);
        contactTF = new JTextField();
        add(contactTF);

        currentLabel = new JLabel("From:");
        add(currentLabel);
        String[] currentCities = {"Abuja(ABV)", "Lagos(LOS)", "Enugu(ENU)", "Port Harcourt(PHC)", "Calabar(CBQ)", "Kaduna(KAD)", "Sokoto(SKO)", "Johannesburg(JNB)", "Owerri(QOW)", "Banjul(BJL)", "Accra(ACC)", "Kano(KAN)", "Delhi(DEL)", "Uyo(QUO)", "FreeTown(FNA)", "Dubai(Sharjah(SHJ))", "Benin City(BNI)"};
        currentCityJC = new JComboBox(currentCities);
        add(currentCityJC);

        destinationLabel = new JLabel("To:");
        add(destinationLabel);
        String[] destinationCities = {"Abuja(ABV)", "Lagos(LOS)", "Owerri(QOW)", "Enugu(ENU)", "Sokoto(SKO)","Kaduna(KAD)","Port Harcourt(PHC)", "Calabar(CBQ)","Dubai(Sharjah(SHJ))", "Uyo(QUO)", "Banjul(BJL)", "Benin City(BNI)", "Delhi(DEL)", "FreeTown(FNA)", "Johannesburg(JNB)", "Accra(ACC)"};
        destinationCityJC = new JComboBox(destinationCities);
        add(destinationCityJC);

        classTypeLabel = new JLabel("Choose Class Type:");
        add(classTypeLabel);
        String[] classes = {"Economy", "Business", "First"};
        classTypeJC = new JComboBox(classes);
        add(classTypeJC);

        flightTypeLabel = new JLabel("Choose Flight Type:");
        add(flightTypeLabel);
        String[] flightTypes = {"Return", "One way"};
        flightTypeJC = new JComboBox(flightTypes);
        add(flightTypeJC);

        travelDate = new JLabel("Enter Travel Date ");
        add(travelDate);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        travelDatePanel = new JDatePanelImpl(model, p);
        travelDatePicker = new JDatePickerImpl(travelDatePanel, new DateLabelFormatter());
        add(travelDatePicker);

        returnDate = new JLabel("Enter Return Date (skip if one way) ");
        add(returnDate);
        UtilDateModel model1 = new UtilDateModel();
        Properties p1 = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        returnDatePanel = new JDatePanelImpl(model1, p1);
        returnDatePicker = new JDatePickerImpl(returnDatePanel, new DateLabelFormatter());
        add(returnDatePicker);


        paymentLabel = new JLabel("Choose Payment Type:");
        add(paymentLabel);
        String[] paymentType = {"Cash", "Credit Card"};
        paymentJC = new JComboBox(paymentType);
        add(paymentJC);

        JLabel space = new JLabel("<html><br><br></html>");
        add(space);

        total = new JButton("View Total");
        add(total);
        submit = new JButton("Submit");
        add(submit);
        close = new JButton("Close");
        add(close);
        ButtonListener buttonListener = new ButtonListener();
        submit.addActionListener(buttonListener);

        paymentJC.addActionListener(e -> {
            String cardDetails = (String) paymentJC.getSelectedItem();
            if (cardDetails == "Credit Card") { //calling the creditCard class if user selects the Credit Card option
                new creditCard().setVisible(true);
            }
        });

        close.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(null, "Cancel Reservation?", "Confirm Dialog", JOptionPane.YES_NO_OPTION);
            switch (answer) {
                case JOptionPane.YES_OPTION:
                    dispose(); //if yes, close current window
                    break;
                case JOptionPane.NO_OPTION:
                    break; //if no, keep current window
            }
        });

//      action listener for the "view total" button
        total.addActionListener(e -> {
//              declaring new variables for the value entered by the user into the JTextFields and then changing to numbers to use for calculation
            try {
                int seatNum = Integer.parseInt(seatTF.getText());
                String classChoice = (String) classTypeJC.getSelectedItem();
                int flightPrice = 0;
                int flightPriceTotal = 0;


//              The switch-case code block below is used to check what class of flight is selected and print prices based on the option
                switch (classChoice) {
                    case "Economy":
                        flightPrice = 40_000; //Underscore(_) makes value more readable and does not affect the program
                        flightPriceTotal = (flightPrice * seatNum);
                        break;
                    case "Business":
                        flightPrice = 125_000;
                        flightPriceTotal = (flightPrice * seatNum);
                        break;
                    case "First":
                        flightPrice = 200_000;
                        flightPriceTotal = (flightPrice * seatNum);
                        break;
                }
//              decimal format: used to display the total cost in a money format.
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                total_cost = flightPriceTotal;
                JOptionPane.showMessageDialog(null, "TOTAL:\n<html><p><i>All transactions are in Naira(N).</i></p><br></html>\n" +
                        "Flight Charge(for a seat): " + (decimalFormat.format(flightPrice))  + "\n" + "Total Cost of flight is: " + decimalFormat.format(flightPriceTotal) + "\n\n");
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "You have entered either a blank or non-numeric value. Please try again.");
            }
        });
    }

    //   The class below requires the user to fill all fields in the form
    public boolean validateInfo() {
        StringBuilder errorText = new StringBuilder();

        if (fullNameTF.getText().length() == 0) {
            errorText.append("Please fill this field!");
            JOptionPane.showMessageDialog(null, "Please Enter Your Name!");
            fullNameTF.setBackground(Color.RED);
        }
        if (addressTF.getText().length() == 0) {
            errorText.append("Please fill this field!\n");
            JOptionPane.showMessageDialog(null, "Please Enter Your Address!");
            addressTF.setBackground(Color.RED);
        }
        if (contactTF.getText().length() == 0) {
            errorText.append("Please fill this field!\n");
            JOptionPane.showMessageDialog(null, "Please Enter Your Phone Number!");
            contactTF.setBackground(Color.RED);
        }
        if (seatTF.getText().length() == 0) {
            errorText.append("Please fill this field!\n");
            JOptionPane.showMessageDialog(null, "Please Enter Number Of Passengers!");
            seatTF.setBackground(Color.RED);
        }
        if (travelDatePicker ==null){
            errorText.append("Please fill this field!\n");
            travelDatePicker.setBackground(Color.RED);
        }

        return errorText.length() == 0;
    }

    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }

    //    The class below is an action listener for the "submit" button.
    public class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            String firstName = fullNameTF.getText();
            String address = addressTF.getText();
            String phoneNumber = contactTF.getText();
            String seatTextField = seatTF.getText();
            String airLines = (String) airLineTypeJC.getSelectedItem();
            String current_city = (String) currentCityJC.getSelectedItem();
            String destination_city = (String) destinationCityJC.getSelectedItem();
            String date = travelDatePicker.getJFormattedTextField().getText();
            String flightType = (String) flightTypeJC.getSelectedItem();
            String class_Type = (String) classTypeJC.getSelectedItem();
            String paymentOption = (String) paymentJC.getSelectedItem();
            int totalCost = total_cost;
            int R_id = Rid;
            DecimalFormat decimalFormat = new DecimalFormat("########");

            JButton source = (JButton) evt.getSource();
            boolean valid = validateInfo();
            if (valid) {
                if (source == submit) { //checking the database if the flight details entered are available
                    try {
                        //where login = your database name, root = your username and "" = your password
                        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login",
                                "root", "");
                        PreparedStatement statement = (PreparedStatement) connection.prepareStatement("SELECT `airline`, `current_city`, `destination`, `flight_class`, `flight_type`, `travel_date`, `number_of_seats` FROM `flight_info` WHERE airline=? and current_city=? and destination=? and flight_class=? and flight_type=? and travel_date=? and number_of_seats=?");
                        statement.setString(1,airLines);
                        statement.setString(2,current_city);
                        statement.setString(3,destination_city);
                        statement.setString(4,class_Type);
                        statement.setString(5,flightType);
                        statement.setString(6,date);
                        statement.setString(7,seatTextField);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            dispose();
                            JOptionPane.showMessageDialog(null, "Flight is available!");
                            //creating the receipt
                            int answer = JOptionPane.showConfirmDialog(null, "<html><h1 style='color: red; text-transform: uppercase; font-family:monospace;'>RECEIPT</h1><p style='font-size:8px;' ><strong><i>Keep the ID-number as it will be required on check in to confirm reservation</i></strong></p></html>\n\n" +
                                            "ID: " + decimalFormat.format(Rid) + "\n" + "Airline: " + airLines + "\n" + "Class Type: " + class_Type + "\n" + "Full Name: " + firstName + "\n" + "Flying From: " + current_city + "\n" + "Flying To: " + destination_city +
                                            "\n" + "Address: " + address + "\n" + "Contact: " + phoneNumber + "\n" + "Number of passengers: " + seatTextField + "\n"
                                            + "Flight Date: " + date + "\n" + "Flight Type: " + flightType + "\n" + "Flight duration: " + flightDuration.durationOfFlight()
                                            + "\n" +"Time of flight: " + flightDuration.timeOfFlight() + "\n" + "Payment Choice: " + paymentOption + "\n\n<html><p><i> If the details above are correct, select YES </i></p></html>\n\n"
                                    , "Receipt", JOptionPane.YES_NO_OPTION);
                            switch (answer) {
                                case JOptionPane.YES_OPTION: //if yes, save details to the database
                                    try {
                                        String query = "INSERT INTO `reserved_flight`(`ID`, `airline`, `Name`, `Class_type`, `Flying_From`, `Flying_to`, `Address`, `Phone_Number`, `Number_of_seats`, `Date`, `Flight_Type`, `Flight_Duration`, `Time_of_flight`, `Mode_of_payment`, `Total_cost`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                        connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
                                        statement = (PreparedStatement) connection.prepareStatement(query);
                                        statement.setString(1,String.valueOf(R_id));
                                        statement.setString(2,airLines);
                                        statement.setString(3,firstName);
                                        statement.setString(4,class_Type);
                                        statement.setString(5,current_city);
                                        statement.setString(6,destination_city);
                                        statement.setString(7,address);
                                        statement.setString(8,String.valueOf(phoneNumber));
                                        statement.setString(9,seatTextField);
                                        statement.setString(10,date);
                                        statement.setString(11,flightType);
                                        statement.setString(12,String.valueOf(flightDuration.durationOfFlight()));
                                        statement.setString(13,String.valueOf(flightDuration.timeOfFlight()));
                                        statement.setString(14,paymentOption);
                                        statement.setString(15,String.valueOf(totalCost));
                                        JOptionPane.showMessageDialog(null, "Saved!");
                                        statement.executeUpdate();
                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(null, "Something went wrong");
                                    }
                                    dispose();
                                    break;
                                case JOptionPane.NO_OPTION:
                                    break;
                            }

                        }else {
                            JOptionPane.showMessageDialog(null, "Flight is not available!" + "\n" + "This may be due to a travel ban");
                        }

                    } catch (NumberFormatException | SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Please fill all the fields!");
                    }
                }
            }
        }
    }
}
