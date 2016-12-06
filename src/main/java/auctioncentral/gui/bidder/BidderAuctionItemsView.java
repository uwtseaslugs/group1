package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.Window;
import auctioncentral.model.*;

import javax.swing.*;
import java.awt.*;

public class BidderAuctionItemsView extends AbstractScreen {
    private final int BUTTON_X_DIM = 200;
    private final int BUTTON_Y_DIM = 20;
    private JButton submitButton, exitButton, backButton;
    private JTable itemTable;
    private Item item;
    JScrollPane tableScroll;

    public BidderAuctionItemsView(Auction auction, Window w) {
        super(w);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);

        submitButton = new JButton("Select Item");
        submitButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));
        backButton = new JButton("Back to Available Auctions");
        backButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));
        exitButton = new JButton("Exit Auction Central");
        exitButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));

        itemTable = new JTable(new ItemTableModel(auction));
        itemTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
        itemTable.setFillsViewportHeight(true);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemTable.setRowSelectionAllowed(true);
        itemTable.getColumnModel().getColumn(0).setMinWidth(50);
        itemTable.getColumnModel().getColumn(1).setMinWidth(50);
        itemTable.getColumnModel().getColumn(2).setMinWidth(50);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;

        tableScroll = new JScrollPane(itemTable);
        add(tableScroll, c);
        add(Box.createRigidArea(new Dimension(0, 50)), c);
        itemTable.getSelectionModel().addListSelectionListener(event ->
                item = auction.getItems().get(itemTable.getSelectedRow()));

        add(submitButton, c);
        add(Box.createRigidArea(new Dimension(0, 20)), c);
        submitButton.addActionListener(a -> {
            if (item == null ) {
                JOptionPane.showMessageDialog(this, "No Item Selected!!");
            } else if (item.getBid((Bidder) LoginManager.inst().getCurrentUser()) != null){
                if (JOptionPane.showConfirmDialog(null, "Do you want to cancel your bid ?", "Cancel Bid",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    item.removeBid((Bidder) LoginManager.inst().getCurrentUser());
                    getRoot().addScreen(new BidderAuctionItemsView(auction, w));
                }
            } else
                getRoot().addScreen(new BidderPlaceBidView(auction, itemTable.getSelectedRow(), w));

        });

        add(backButton, c);
        add(Box.createRigidArea(new Dimension(0, 10)), c);
        backButton.addActionListener(a -> getRoot().addScreen(new BidderAuctionsView(w)));

        add(exitButton, c);
        exitButton.addActionListener(a -> System.exit(0));

    }

}