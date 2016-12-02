package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;

import javax.swing.*;
import java.util.Observable;

public class ContactHomeView extends AbstractScreen {

    private JButton editAuctionButton;
    private JButton addAuctionButton;

    public ContactHomeView() {
        editAuctionButton = new JButton("Edit auction");
        addAuctionButton = new JButton("Add new auction");

        add(editAuctionButton);
        add(addAuctionButton);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
