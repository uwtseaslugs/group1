package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.Window;
import auctioncentral.model.Auction;
import auctioncentral.model.Item;
import auctioncentral.model.ItemTableModel;

import javax.swing.*;
import java.awt.*;

public class BidderAuctionItemsView extends AbstractScreen {
    private final int BUTTON_X_DIM = 200;
    private final int BUTTON_Y_DIM = 20;
    private JButton submit, exitButton;
    private JTable itemTable;
    private Item item;
    JScrollPane tableScroll;

    public BidderAuctionItemsView(Auction auction, Window w) {
        super(w);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);

        submit = new JButton("Select Item");
        submit.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));
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
        itemTable.getSelectionModel().addListSelectionListener(event ->
                item = auction.getItems().get(itemTable.getSelectedRow()));

        add(submit, c);
        submit.addActionListener(a -> {
            if (item != null)
                getRoot().addScreen(new BidderPlaceBidView(item, w));
            else
                JOptionPane.showMessageDialog(this, "No Item Selected");
        });

        add(exitButton, c);
        exitButton.addActionListener(a -> System.exit(0));

    }

}