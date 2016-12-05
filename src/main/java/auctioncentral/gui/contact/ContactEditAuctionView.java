package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Auction;
import auctioncentral.model.Calendar;
import auctioncentral.model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class ContactEditAuctionView extends AbstractScreen {

    private Auction auction;

    private JScrollPane itemsPane;
    private JPanel itemsPanel;
    private JButton addItemButton;
    private JButton homeButton;
    private JButton cancelAuctionButton;
    private JButton removeItemButton;
    private JTextField removeItemText;
    private JLabel removeItemLabel;

    private int indexOfItem;

    public ContactEditAuctionView(Auction auction) {
        this.auction = auction;
        itemsPanel = new JPanel();
        itemsPane = new JScrollPane(itemsPanel);
        itemsPanel.setLayout(new GridLayout(auction.getItems().size() + 2, 2, 10, 10));
        itemsPanel.add(new JLabel("Item Name"));
        itemsPanel.add(new JLabel("Min Bid"));
        itemsPanel.add(new JLabel(""));
        itemsPanel.add(new JLabel(""));
        updateItems();

        addItemButton = new JButton("Add new Item");
        addItemButton.addActionListener(e -> {
            getRoot().addScreen(new ContactAddItemView(auction));
        });

        homeButton = new JButton("Back");
        homeButton.addActionListener(e -> {
            getRoot().addScreen(new ContactHomeView());
        });

        cancelAuctionButton = new JButton("Cancel Auction");
        cancelAuctionButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Warning! Are you sure you want to cancel this auction?") == JOptionPane.OK_OPTION) {
                if (Calendar.inst().canCancelAuction(auction)) {
                    Calendar.inst().cancelAuction(auction);
                    JOptionPane.showMessageDialog(this, "Auction cancelled successfully");
                    getRoot().addScreen(new ContactHomeView());
                } else {
                    JOptionPane.showMessageDialog(this, "Auctions can only be cancelled if they are " + Calendar.CANCEL_MAX_DAYS_AWAY +
                            "+ days away");
                }
            }

        });
        removeItemText = new JTextField(3);
        removeItemLabel = new JLabel("Enter Index of Item to be removed: ");
        removeItemButton = new JButton("Remove Item");
        removeItemButton.addActionListener(e -> {
            String num = removeItemText.getText();
            indexOfItem = Integer.parseInt(num);

            if (JOptionPane.showConfirmDialog(this, "Warning! Are you sure you want to remove item# " + indexOfItem
                + " from this Auctiom") == JOptionPane.OK_OPTION) {
                if(auction.canRemoveItem(auction.getItems().get(indexOfItem))){
                    auction.removeItem(auction.getItems().get(indexOfItem));
                    updateItems();
                    JOptionPane.showMessageDialog(this, "Item has been removed successfully");
                }else{
                    JOptionPane.showMessageDialog(this, "Auctions can only be cancelled if they are " + Calendar.CANCEL_MAX_DAYS_AWAY +
                            "+ days away");
                }
            }

        });

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        add(itemsPane, c);
        add(Box.createRigidArea(new Dimension(0, 10)), c);
        c.gridwidth = 1;
        add(homeButton, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        add(addItemButton, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(Box.createRigidArea(new Dimension(0, 40)), c);
        add(cancelAuctionButton, c);

        c.anchor = GridBagConstraints.SOUTH;
        add(removeItemLabel);
        add(removeItemText);
        add(removeItemButton,c);



        itemsPane.setPreferredSize(new Dimension(400, 250));
    }

   public void updateItems(){
       for (Item item : auction.getItems()) {
           itemsPanel.add(new JLabel(item.getName()));
           itemsPanel.add(new JLabel("$" + item.getMinimumBid()));
       }
   }
    @Override
    public void update(Observable o, Object arg) {

    }
}
