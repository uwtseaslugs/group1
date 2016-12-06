package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Bidder;
import auctioncentral.model.Item;
import auctioncentral.model.LoginManager;
import auctioncentral.gui.Window;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Observable;

public class BidderPlaceBidView extends AbstractScreen {
    private final int BUTTON_X_DIM = 200;
    private final int BUTTON_Y_DIM = 20;
    private JLabel itemNameLabel, itemName, minBidLabel, minBid, itemConditionLabel, itemCondition, inputLabel;
    private JSpinner bidSpinner;
    private JButton placeBidButton, exitButton;

    public BidderPlaceBidView(Item item, Window w) {
        super(w);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);

        int minBidAmt = item.getMinimumBid();
        itemNameLabel = new JLabel("Item Name");
        itemName = new JLabel(item.getName());
        itemConditionLabel = new JLabel("Item Condition");
        itemCondition = new JLabel(item.getCondition().toString());
        minBidLabel = new JLabel("Minimum Bid");
        minBid = new JLabel(Integer.toString(minBidAmt));
        inputLabel = new JLabel("Enter Bid Amount");
        bidSpinner = new JSpinner(new SpinnerNumberModel(minBidAmt, minBidAmt, 1_000_000, 1));

        placeBidButton = new JButton("Place Bid");
        placeBidButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));

        exitButton = new JButton("Exit Auction Central");
        exitButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;

        add(itemNameLabel, c);
        add(itemName, c);
        add(itemCondition, c);
        add(itemConditionLabel, c);
        add(minBid, c);
        add(minBidLabel, c);
        add(inputLabel, c);
        add(bidSpinner, c);
        add(placeBidButton, c);

        placeBidButton.addActionListener(a -> {
            if (JOptionPane.showConfirmDialog(null, "Confirm Bid", "Do you want to place bid?", JOptionPane.YES_NO_OPTION) ==
                    JOptionPane.YES_OPTION){
                item.placeBid((Bidder) LoginManager.inst().getCurrentUser(), new BigDecimal((int) bidSpinner.getValue()));
                getRoot().popScreen();
            }

        });

        add(exitButton, c);
        exitButton.addActionListener(a -> System.exit(0));
    }

}