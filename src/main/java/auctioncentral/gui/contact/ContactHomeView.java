package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;

import javax.swing.*;
import java.util.Observable;

public class ContactHomeView extends AbstractScreen {

    private JButton editAuctionButton;
    private JButton addAuctionButton;
    private JButton exitButton;

    public ContactHomeView() {
        editAuctionButton = new JButton("Edit upcoming auction");
        addAuctionButton = new JButton("Add new auction");
        exitButton = new JButton("Exit Auction Central");

        add(editAuctionButton);
        add(addAuctionButton);
        add(exitButton);

        exitButton.addActionListener(a -> System.exit(0));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
