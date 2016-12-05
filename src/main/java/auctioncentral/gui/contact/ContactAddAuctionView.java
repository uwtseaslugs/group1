package auctioncentral.gui.contact;


import auctioncentral.gui.AbstractScreen;
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

/**
 * Created by Jon
 */
public class ContactAddAuctionView extends AbstractScreen {

    private Auction newAuction;

    private JLabel auctionTitle;
    private JLabel DateLabel;
    private JLabel CommentLabel;
    private JLabel numOfItemsLabel;

    private JTextField dateText;
    private JTextField commentsText;
    private JTextField numOfItemsText;

    private JButton ComfirmButton;
    private JButton returnHomeButton;

    private Date dateParsed;
    private String dateAndTime;
    private String comments;
    private String tempItems;
    private int amountOfItems;


    public ContactAddAuctionView(){
        auctionTitle = new JLabel("Date and Time of Auction: (MM/DD/YYYY HH [AM/PM])");
        CommentLabel = new JLabel("Enter in comments for auction: ");
        numOfItemsLabel = new JLabel("Enter in the approximate number of Items in the autcion: ");

        dateText = new JTextField(40);
        commentsText = new JTextField(40);
        numOfItemsText = new JTextField(40);

        ComfirmButton = new JButton("Request Auction");
        returnHomeButton = new JButton("Return Home");

        ComfirmButton.addActionListener(e -> {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh a");
            dateAndTime = dateText.getText();
            comments = commentsText.getText();
            tempItems = numOfItemsText.getText();
            amountOfItems = Integer.parseInt(tempItems);
            try{
                dateParsed = format.parse(dateAndTime);
            }catch (ParseException a) {
                JOptionPane.showMessageDialog(this,"Please enter date in valid format.");

            }
            try {
                newAuction = new Auction((Contact) LoginManager.inst().getCurrentUser(), dateParsed, comments, amountOfItems);
            } catch (IllegalArgumentException b) {
                JOptionPane.showMessageDialog(this,"Error inputting information. Press Ok to return to previous menu.");
            }
            if(!Calendar.inst().canAddAuctionYear(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because you have had an Auction within the past year.");
            }else if(!Calendar.inst().canAddAuctionDailyLimit(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because you their is already 2 auction scheduled for this date.");
            }else if(!Calendar.inst().canAddAuctionMaxInFuture(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because we do not schedule further than one month ahead.");
            }else if(!Calendar.inst().canAddAuctionMaxLimit(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because we have reached our maximum scheduled auctions.");
            }else if(!Calendar.inst().canAddAuctionNonprofitLimit(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because you already have an auction in the system.");
            }else if(!Calendar.inst().canAddAuctionMinInFuture(newAuction)){
                JOptionPane.showMessageDialog(this,"Your Auction request is denied because you need to schedule your auction 1 week ahead.");
            }else{
                JOptionPane.showMessageDialog(this,"Sucessfully added your Auction on :" + dateAndTime);
                Calendar.inst().addAuction(newAuction);
                getRoot().addScreen(new ContactHomeView());
            }

        });
        returnHomeButton.addActionListener(e -> {
            getRoot().addScreen(new ContactHomeView());
        });

        Box vertBox = Box.createVerticalBox();
        Box row1 = Box.createHorizontalBox();
        row1.add(auctionTitle);
        row1.add(Box.createRigidArea(new Dimension(0,5)));
        row1.add(Box.createGlue());
        row1.add(dateText);
        Box row2 = Box.createHorizontalBox();
        row2.add(CommentLabel);
        row2.add(Box.createGlue());
        row2.add(commentsText);
        Box row3 = Box.createHorizontalBox();
        row3.add(numOfItemsLabel);
        row3.add(Box.createGlue());
        row3.add(numOfItemsText);
        vertBox.add(row1);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        vertBox.add(row2);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        vertBox.add(row3);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        Box buttonRow = Box.createHorizontalBox();
        buttonRow.add(Box.createRigidArea(new Dimension(60, 0)));
        buttonRow.add(ComfirmButton);
        buttonRow.add(Box.createGlue());
        buttonRow.add(returnHomeButton);
        buttonRow.add(Box.createRigidArea(new Dimension(60, 0)));
        vertBox.add(Box.createRigidArea(new Dimension(0, 20)));
        vertBox.add(buttonRow);

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        add(vertBox);

    }

    public void update(Observable o, Object arg) {

    }
}
