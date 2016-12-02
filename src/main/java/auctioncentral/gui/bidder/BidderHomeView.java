package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;

import javax.swing.*;
import java.util.Observable;

public class BidderHomeView extends AbstractScreen {

    private JButton viewAuctionsButton;
    private JButton exitButton;

    public BidderHomeView() {
        viewAuctionsButton = new JButton("View upcoming auctions");
        exitButton = new JButton("Exit Auction Central");

        add(viewAuctionsButton);
        add(exitButton);

        exitButton.addActionListener(a -> System.exit(0));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
