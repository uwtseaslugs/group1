/**
 * @author Jason
 *
 *
 */
package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.*;
import auctioncentral.gui.Window;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class BidderPlaceBidView extends AbstractScreen {
    private final int BUTTON_X_DIM = 200;
    private final int BUTTON_Y_DIM = 20;
    private JLabel itemNameLabel, minBidLabel, itemConditionLabel, inputLabel;
    private JSpinner bidSpinner;
    private JButton placeBidButton, exitButton;
    private Auction auction;

    public BidderPlaceBidView(Auction auction, int index, Window w) {
        super(w);
        this.auction = auction;
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);
        Item item = (auction.getItems()).get(index);

        int minBidAmt = item.getMinimumBid();
        itemNameLabel = new JLabel("Item Name:          " + item.getName());
        itemConditionLabel = new JLabel("Item Condition:     " + item.getCondition().toString());
        minBidLabel = new JLabel("Minimum Bid:       $"  + Integer.toString(minBidAmt));
        inputLabel = new JLabel("Enter Bid Amount:");
        bidSpinner = new JSpinner(new SpinnerNumberModel(minBidAmt, minBidAmt, 1_000_000, 1));

        placeBidButton = new JButton("Place Bid");
        placeBidButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));

        exitButton = new JButton("Back");
        exitButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;

        add(itemNameLabel, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(itemConditionLabel, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(minBidLabel, c);
        add(Box.createRigidArea(new Dimension(0, 10)), c);
        add(inputLabel, c);
        add(bidSpinner, c);
        add(Box.createRigidArea(new Dimension(0, 50)), c);
        add(placeBidButton, c);
        add(Box.createRigidArea(new Dimension(0, 10)), c);
        placeBidButton.addActionListener(a -> {
            if (JOptionPane.showConfirmDialog(null, "Do you want to bid $" + bidSpinner.getValue()
                            + " for " + item.getName() + "?",
                    "Confirm Bid", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                item.placeBid((Bidder) LoginManager.inst().getCurrentUser(), new BigDecimal((int) bidSpinner.getValue()));
                getRoot().addScreen(new BidderAuctionItemsView(auction, w));
            }
        });

        add(exitButton, c);
        exitButton.addActionListener(a -> getRoot().addScreen(new BidderAuctionItemsView(auction, getRoot())));
    }

}