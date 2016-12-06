package auctioncentral.gui.bidder;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class BidderHomeView extends BidderView {
    private JButton viewAuctionsButton, viewPreviousBids;

    public BidderHomeView() {
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

        viewAuctionsButton.addActionListener(a -> getRoot().addScreen(new BidderAuctionsView()));
        viewPreviousBids.addActionListener(a -> getRoot().addScreen(new BidderHomeView()));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
