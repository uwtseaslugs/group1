package auctioncentral.gui.bidder;

import auctioncentral.model.Auction;
import auctioncentral.model.Calendar;

import auctioncentral.gui.Window;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class BidderAuctionsView extends BidderView {
    private JButton submit;
    private List<Auction> aList;
    private DefaultListModel listModel;
    private JList list;
    private Auction selectedAuction;
    private JScrollPane listScroll;

    public BidderAuctionsView(Window w) {
        super(w);
        setDisplay();
        submit = new JButton("Select Auction");
        aList = Calendar.inst().getAuctionsPastDate(new Date());
        listModel = new DefaultListModel();

        for (int i = 0; i < aList.size(); i++){
            listModel.add(i, aList.get(i).getDate().toString());
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(15);

        listScroll = new JScrollPane(list);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        setGrid(2,1);
        gbl.setConstraints(listScroll, c);
        setGrid(3,4);
        gbl.setConstraints(submit, c);

        add(listScroll);
        add(submit);

        list.addListSelectionListener(event -> selectedAuction = aList.get(list.getSelectedIndex()));
        submit.addActionListener(a -> getRoot().addScreen(new BidderAuctionItemsView(selectedAuction, w)));
    }
}
