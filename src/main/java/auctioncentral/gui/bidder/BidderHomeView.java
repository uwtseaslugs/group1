package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.Window;
import javax.swing.*;
import java.awt.*;

public class BidderHomeView extends AbstractScreen {
    private JButton viewAuctionsButton, viewPreviousBidsButton, exitButton;
    private final int BUTTON_X_DIM = 200;
    private final int BUTTON_Y_DIM = 20;

    public BidderHomeView(Window w) {
        super(w);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);

        viewAuctionsButton = new JButton("View upcoming auctions");
        viewAuctionsButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));
        viewPreviousBidsButton = new JButton("View Previous Bids");
        viewPreviousBidsButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));
        exitButton = new JButton("Exit Auction Central");
        exitButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));

        c.gridwidth = GridBagConstraints.REMAINDER;
        add(viewAuctionsButton, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(viewPreviousBidsButton, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);

        viewAuctionsButton.addActionListener(a -> getRoot().addScreen(new BidderAuctionsView(w)));
        viewPreviousBidsButton.addActionListener(a -> getRoot().addScreen(new BidderHomeView(w)));

        add(exitButton, c);
        exitButton.addActionListener(a -> System.exit(0));
    }
}
