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
        itemsPanel.setLayout(new GridLayout(auction.getItems().size() + 2, 2, 10, 10));
        itemsPanel.add(new JLabel("Item Name"));
        itemsPanel.add(new JLabel("Min Bid"));
        itemsPanel.add(new JLabel(""));
        itemsPanel.add(new JLabel(""));
        for (Item item : auction.getItems()) {
            itemsPanel.add(new JLabel(item.getName()));
            itemsPanel.add(new JLabel("$" + item.getMinimumBid()));
        }

        add(itemsPane);
        itemsPane.setPreferredSize(new Dimension(400, 250));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
