package auctioncentral.gui.staff;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.Window;
import auctioncentral.model.Calendar;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
public class StaffAdminMenu extends AbstractScreen {
    private JButton addMaxAuctions;
    private JButton returnToHome;
    private JTextField maxAuctions;
    private JLabel EnterMax;
    private JLabel currentMaxAuctions;
    private int numOfMaxAuctions;
    public StaffAdminMenu() {

        setLayout(new FlowLayout());

        currentMaxAuctions = new JLabel("Current Max Auctions allowed: " + Calendar.inst().getMaxAuctions());
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
            JOptionPane.showMessageDialog(this, "Successfully added " + numOfMaxAuctions +" to max amount of auctions.");


        });

        returnToHome.addActionListener(e -> {
            getRoot().addScreen(new StaffHomeView());
        });


    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
