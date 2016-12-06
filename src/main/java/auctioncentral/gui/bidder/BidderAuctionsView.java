package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Auction;
import auctioncentral.model.Calendar;
import auctioncentral.gui.Window;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;


public class BidderAuctionsView extends AbstractScreen {
    private final int BUTTON_X_DIM = 200;
    private final int BUTTON_Y_DIM = 20;
    private JButton submit, exitButton;
    private List<Auction> aList;
    private DefaultListModel listModel;
    private JList list;
    private Auction selectedAuction;
    private JScrollPane listScroll;

    public BidderAuctionsView(Window w) {
        super(w);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);

        submit = new JButton("Select Auction");
        submit.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));
        exitButton = new JButton("Exit Auction Central");
        exitButton.setPreferredSize(new Dimension(BUTTON_X_DIM, BUTTON_Y_DIM));

        aList = Calendar.inst().getAuctionsPastDate(new Date());
        listModel = new DefaultListModel();

        for (int i = 0; i < aList.size(); i++){
            listModel.add(i, aList.get(i).getDate().toString());
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(15);
        listScroll = new JScrollPane(list);

        c.gridwidth = GridBagConstraints.REMAINDER;
        add(Box.createVerticalGlue());
        add(listScroll, c);
        add(Box.createRigidArea(new Dimension(0, 50)), c);

        add(submit, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);

        list.addListSelectionListener(event -> {
            if (list.getSelectedIndex() != -1)
                selectedAuction = aList.get(list.getSelectedIndex());
        });
        submit.addActionListener(a -> {
            if (selectedAuction != null)
                getRoot().addScreen(new BidderAuctionItemsView(selectedAuction, w));
            else
                JOptionPane.showMessageDialog(this, "No Auction Selected");
        });

        add(exitButton, c);
        exitButton.addActionListener(a -> System.exit(0));

    }

}