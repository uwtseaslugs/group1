package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Bidder;
import auctioncentral.model.LoginManager;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

public class BidderHomeView extends AbstractScreen {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private JButton viewAuctionsButton, viewPreviousBids, exitButton;
    private JLabel userLabel, user, dateLabel, auctionLabel, auctionNumberLabel;

    public BidderHomeView() {
        viewAuctionsButton = new JButton("View upcoming auctions");
        viewPreviousBids = new JButton("View Previous Bids");
        exitButton = new JButton("Exit Auction Central");
        user = new JLabel(((Bidder) LoginManager.getInstance().getCurrentUser()).getName());
        userLabel = new JLabel("Currently logged in as a Bidder");
        dateLabel = new JLabel(dateTimeFormatter.format(LocalDateTime.now()));
        auctionNumberLabel = new JLabel("Total Number of Upcoming Auctions: ");

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        gbl.setConstraints(user, c);
        c.gridx = 1;
        gbl.setConstraints(userLabel, c);
        c.gridx = 0;
        c.gridy = 1;
        gbl.setConstraints(dateLabel, c);
        c.gridx = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        gbl.setConstraints(auctionNumberLabel, c);

        c.gridx = 2;
        c.weighty = 0.0;
        c.weightx = 0.0;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridy = 1;
        gbl.setConstraints(viewAuctionsButton, c);
        c.gridy = 2;
        gbl.setConstraints(viewPreviousBids, c);
        c.gridy = 3;
        gbl.setConstraints(exitButton, c);


        add(user);
        add(userLabel);
        add(dateLabel);
        add(auctionNumberLabel);
        add(viewAuctionsButton);
        add(viewPreviousBids);
        add(exitButton);

        exitButton.addActionListener(a -> System.exit(0));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
