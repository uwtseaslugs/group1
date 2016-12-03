package auctioncentral.gui.staff;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.Window;
import auctioncentral.model.Calendar;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Observable;
public class StaffAdminMenu extends AbstractScreen {
    private JButton addMaxAuctions;
    private JButton returnToHome;
    private JTextField maxAuctions;
    private JLabel EnterMax;
    private JLabel currentMaxAuctions;
    private int numOfMaxAuctions;
    private int currentNumMaxAuctions;
    private int currentAuctions;

    public StaffAdminMenu() {

        updateAuctions();

        setLayout(new FlowLayout());

        currentMaxAuctions = new JLabel("<html>Current Auctions: " +  currentAuctions + "<br>Current Max Auctions allowed:<html> " + currentNumMaxAuctions);
        add(currentMaxAuctions);

        EnterMax = new JLabel("Enter max auctions: ");
        add(EnterMax);

        maxAuctions = new JTextField(3);
        add(maxAuctions);

        addMaxAuctions = new JButton("Add Max Auctions");
        add(addMaxAuctions);

        returnToHome = new JButton("Return Home");
        add(returnToHome);

        addMaxAuctions.addActionListener(e -> {
            String num = maxAuctions.getText();
            numOfMaxAuctions = Integer.parseInt(num);
            Calendar.inst().addMaxAuctions(numOfMaxAuctions);
            updateAuctions();
            if (currentNumMaxAuctions < currentAuctions) {
                JOptionPane.showMessageDialog(this, "<html>Maximum Auctions is less than current auctions. <br>Clients will be unable to add new auctions until current auctions are completed<html>");
            }
            JOptionPane.showMessageDialog(this, "Successfully added " + numOfMaxAuctions +" to max amount of auctions.");
            currentMaxAuctions.setText("<html>Current Auctions: " + currentAuctions + "<br>Current Max Auctions allowed:<html> " + currentNumMaxAuctions);


        });

        returnToHome.addActionListener(e -> {
            getRoot().addScreen(new StaffHomeView());
        });


    }

    public void updateAuctions(){
        currentNumMaxAuctions = Calendar.inst().getMaxAuctions();
        currentAuctions = Calendar.inst().getAuctionsPastDate(new Date()).size();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
