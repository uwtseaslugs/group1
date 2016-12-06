package auctioncentral.gui.bidder;

import auctioncentral.model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class BidderPlaceBidView extends BidderView {
    private JLabel itemNameLabel, itemName, minBidLabel, minBid, itemConditionLabel, itemCondition, inputLabel;
    private JSpinner bidSpinner;
    private JButton placeBidButton;

    public BidderPlaceBidView(Item item) {
        setDisplay();
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

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        setGrid(2,1);
        gbl.setConstraints(itemNameLabel, c);
        setGrid(2,2);
        gbl.setConstraints(itemName, c);

        setGrid(3,1);
        gbl.setConstraints(itemConditionLabel, c);
        setGrid(3,2);
        gbl.setConstraints(itemCondition, c);

        setGrid(4,1);
        gbl.setConstraints(minBidLabel, c);
        setGrid(4,2);
        gbl.setConstraints(minBid, c);
        setGrid(5,1);
        gbl.setConstraints(inputLabel, c);
        setGrid(5,2);
        gbl.setConstraints(bidSpinner, c);
        setGrid(5,3);
        gbl.setConstraints(placeBidButton, c);

        setWeight(1.0,1.0);

        add(itemNameLabel);
        add(itemName);
        add(itemCondition);
        add(itemConditionLabel);
        add(minBid);
        add(minBidLabel);
        add(inputLabel);
        add(bidSpinner);
        add(placeBidButton);

        placeBidButton.addActionListener(a -> getRoot().popScreen());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}