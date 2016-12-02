package auctioncentral.gui.staff;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Calendar;

import javax.swing.*;
import java.util.Observable;
public class StaffAdminMenu extends AbstractScreen {
    private JButton addMaxAuctions;
    private JButton returnToHome;
    private JTextField maxAuctions;
    private JLabel EnterMax;
    private int numOfMaxAuctions;
    public StaffAdminMenu() {
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
        });



    }

    @Override
    public void update(Observable o, Object arg) {

    }
}