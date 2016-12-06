package auctioncentral.gui.bidder;

import auctioncentral.model.Auction;
import auctioncentral.model.Item;
import auctioncentral.model.ItemTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class BidderAuctionItemsView extends BidderView {
    private JButton submit;
    private JTable itemTable;
    private Item item;
    JScrollPane tableScroll;

    public BidderAuctionItemsView(Auction auction) {
        setDisplay();
        submit = new JButton("Select Item");
        itemTable = new JTable(new ItemTableModel(auction));
        itemTable.setPreferredScrollableViewportSize(new Dimension(100, 200));
        itemTable.setFillsViewportHeight(true);
        itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemTable.setRowSelectionAllowed(true);
        itemTable.getColumnModel().getColumn(0).setMinWidth(75);
        itemTable.getColumnModel().getColumn(1).setMinWidth(75);
        itemTable.getColumnModel().getColumn(2).setMinWidth(75);

        tableScroll = new JScrollPane(itemTable);
        setGrid(2,1);
        setWeight(1.0,1.0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridwidth = 2;
        gbl.setConstraints(tableScroll, c);
        setGrid(3,4);
        setWeight(0.0,0.0);
        gbl.setConstraints(submit, c);

        add(tableScroll);
        add(submit);
        itemTable.getSelectionModel().addListSelectionListener(event ->
                item = auction.getItems().get(itemTable.getSelectedRow()));
        submit.addActionListener(a -> getRoot().addScreen(new BidderPlaceBidView(item)));

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}