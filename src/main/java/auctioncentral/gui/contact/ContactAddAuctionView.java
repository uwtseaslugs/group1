package auctioncentral.gui.contact;


import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.StatusBorder;
import auctioncentral.model.Auction;
import auctioncentral.model.Calendar;
import auctioncentral.model.Contact;
import auctioncentral.model.LoginManager;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Jon
 */
public class ContactAddAuctionView extends AbstractScreen {

    private Auction newAuction;

    private JLabel titleLabel;
    private JLabel auctionTitle;
    private JLabel CommentLabel;
    private JLabel numOfItemsLabel;

    private JTextField dateText;
    private JComboBox<String> hourCombo;
    private JComboBox<String> amPmCombo;
    private JTextField commentsText;
    private JTextField numOfItemsText;

    private JButton confirmButton;
    private JButton returnHomeButton;

    private Date dateParsed;
    private String date;
    private String comments;
    private String tempItems;
    private Integer amountOfItems;


    public ContactAddAuctionView(){
        titleLabel = new JLabel("Requesting new auction");
        auctionTitle = new JLabel("Date and Time of Auction: (MM/DD/YYYY)");
        hourCombo = new JComboBox<>(IntStream.range(1, 13).mapToObj(a -> "" + a).collect(Collectors.toList()).toArray(new String[0]));
        amPmCombo = new JComboBox<>(new String[]{"AM", "PM"});
        CommentLabel = new JLabel("Comments (Optional):  ");
        numOfItemsLabel = new JLabel("Approximate number of Items (Optional): ");

        dateText = new JTextField(10);
        commentsText = new JTextField(10);
        numOfItemsText = new JTextField(3);

        confirmButton = new JButton("Request Auction");
        returnHomeButton = new JButton("Back");

        confirmButton.addActionListener(e -> {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh a");
            date = dateText.getText();
            comments = commentsText.getText();
            tempItems = numOfItemsText.getText();
            if (tempItems.trim().equals("")) {
                amountOfItems = null;
            } else {
                try {
                    amountOfItems = Integer.parseInt(tempItems);
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(this, "Number of items must be a positive number");
                    return;
                }
            }
            try{
                dateParsed = format.parse(date + " " + hourCombo.getSelectedItem() + " " + amPmCombo.getSelectedItem());
            }catch (ParseException a) {
                JOptionPane.showMessageDialog(this,"Please enter date in valid format.");
                return;
            }
            try {
                newAuction = new Auction((Contact) LoginManager.inst().getCurrentUser(), dateParsed, comments, amountOfItems);
            } catch (IllegalArgumentException b) {
                JOptionPane.showMessageDialog(this,"Error inputting information.");
                return;
            }
            if(!Calendar.inst().canAddAuctionYear(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because you have had an Auction within the past year.");
            }else if(!Calendar.inst().canAddAuctionDailyLimit(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because there are already 2 auction scheduled for this date.");
            }else if(!Calendar.inst().canAddAuctionMaxInFuture(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because we do not schedule further than one month ahead.");
            }else if(!Calendar.inst().canAddAuctionMaxLimit(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because we have reached our maximum scheduled auctions.");
            }else if(!Calendar.inst().canAddAuctionNonprofitLimit(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because you already have an auction in the system.");
            }else if(!Calendar.inst().canAddAuctionMinInFuture(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because you need to schedule your auction at least 1 week ahead.");
            }else{
                JOptionPane.showMessageDialog(this,"Successfully added your Auction on: " + date);
                Calendar.inst().addAuction(newAuction);
                getRoot().addScreen(new StatusBorder(new ContactHomeView()));
            }
        });
        returnHomeButton.addActionListener(e -> {
            getRoot().addScreen(new StatusBorder(new ContactHomeView()));
        });

        Box vertBox = Box.createVerticalBox();
        Box row1 = Box.createHorizontalBox();
        row1.add(auctionTitle);
        row1.add(Box.createRigidArea(new Dimension(30,0)));
        row1.add(Box.createGlue());
        row1.add(dateText);
        row1.add(hourCombo);
        row1.add(amPmCombo);
        Box row2 = Box.createHorizontalBox();
        row2.add(CommentLabel);
        row2.add(Box.createGlue());
        row2.add(commentsText);
        Box row3 = Box.createHorizontalBox();
        row3.add(numOfItemsLabel);
        row3.add(Box.createRigidArea(new Dimension(205, 0)));
        row3.add(Box.createGlue());
        row3.add(numOfItemsText);
        vertBox.add(titleLabel);
        vertBox.add(Box.createRigidArea(new Dimension(0, 20)));
        vertBox.add(row1);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        vertBox.add(row2);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        vertBox.add(row3);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        Box buttonRow = Box.createHorizontalBox();
        buttonRow.add(Box.createRigidArea(new Dimension(60, 0)));
        buttonRow.add(returnHomeButton);
        buttonRow.add(Box.createGlue());
        buttonRow.add(confirmButton);
        buttonRow.add(Box.createRigidArea(new Dimension(60, 0)));
        vertBox.add(Box.createRigidArea(new Dimension(0, 20)));
        vertBox.add(buttonRow);

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        add(vertBox);

    }
    @Override
    public void update(Observable o, Object arg) {

    }
}
