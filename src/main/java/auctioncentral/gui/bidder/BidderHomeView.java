package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;

import javax.swing.*;
import java.util.Observable;

public class BidderHomeView extends AbstractScreen {

    private JButton viewAuctionsButton;



    public BidderHomeView() {
        viewAuctionsButton = new JButton("View upcoming auctions");

        add(viewAuctionsButton);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
