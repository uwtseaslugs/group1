package auctioncentral.gui.bidder;

import auctioncentral.gui.Window;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.util.Observable;

public class BidderHomeView extends BidderView {
    private JButton viewAuctionsButton, viewPreviousBids;

    public BidderHomeView(Window w) {
            super(w);
        setDisplay();
        viewAuctionsButton = new JButton("View upcoming auctions");
        viewPreviousBids = new JButton("View Previous Bids");

        c.anchor = GridBagConstraints.LAST_LINE_END;
        setGrid(3,4);
        gbl.setConstraints(viewAuctionsButton, c);
        setGrid(4,4);
        gbl.setConstraints(viewPreviousBids, c);
        setWeight(1.0,1.0);

        add(viewAuctionsButton);
        add(viewPreviousBids);

        viewAuctionsButton.addActionListener(a -> getRoot().addScreen(new BidderAuctionsView(w)));
        viewPreviousBids.addActionListener(a -> getRoot().addScreen(new BidderHomeView(w)));
    }
}
