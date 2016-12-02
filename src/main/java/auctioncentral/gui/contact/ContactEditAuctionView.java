package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Auction;
import auctioncentral.model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class ContactEditAuctionView extends AbstractScreen {

    private Auction auction;

    private JScrollPane itemsPane;
    private JPanel itemsPanel;

    public ContactEditAuctionView(Auction auction) {
        this.auction = auction;

        itemsPanel = new JPanel();
        itemsPane = new JScrollPane(itemsPanel);
        itemsPanel.setLayout(new GridLayout(auction.getItems().size(), 2));
        for (Item item : auction.getItems()) {
            itemsPanel.add(new JLabel(item.getName()));
            itemsPanel.add(new JLabel("" + item.getMinimumBid()));
        }

        add(itemsPane);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
