package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.StatusBorder;
import auctioncentral.model.Auction;
import auctioncentral.model.Calendar;
import auctioncentral.model.Item;

import javax.swing.*;

import auctioncentral.gui.Window;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Observable;

/**
 * @author Hunter, Jon
 */
public class ContactEditAuctionView extends AbstractScreen {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh a");

    private Auction auction;

    private JLabel auctionLabel;
    private JScrollPane itemsPane;
    private JPanel itemsPanel;
    private JButton addItemButton;
    private JButton homeButton;
    private JButton cancelAuctionButton;
    private JButton removeItemButton;
    private JTextField removeItemText;
    private JLabel removeItemLabel;

    private int indexOfItem;

    public ContactEditAuctionView(Auction auction, Window w) {
        super(w);
        this.auction = auction;
        auctionLabel = new JLabel("Editing auction on " + dateFormat.format(auction.getDate()));
        itemsPane = new JScrollPane(itemsPanel);
        updateItems();

        addItemButton = new JButton("Add new Item");
        addItemButton.addActionListener(e -> {
                getRoot().addScreen(new ContactAddItemView(auction, getRoot()));
        });

        homeButton = new JButton("Back");
        homeButton.addActionListener(e -> {
                getRoot().addScreen(new ContactHomeView(getRoot()));
        });

        cancelAuctionButton = new JButton("Cancel Auction");
        cancelAuctionButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Warning! Are you sure you want to cancel this auction?") == JOptionPane.OK_OPTION) {
                if (Calendar.inst().canCancelAuction(auction)) {
                    Calendar.inst().cancelAuction(auction);
                    JOptionPane.showMessageDialog(this, "Auction cancelled successfully");
                    getRoot().addScreen(new ContactHomeView(getRoot()));
                } else {
                    JOptionPane.showMessageDialog(this, "Auctions can only be cancelled if they are " + Calendar.CANCEL_MIN_DAYS_AWAY +
                            "+ days away");
                }
            }

        });
        removeItemText = new JTextField(3);
        removeItemLabel = new JLabel("Enter Index of Item to be removed: ");
        removeItemButton = new JButton("Remove Item");
        removeItemButton.addActionListener(e -> {
            String num = removeItemText.getText();
            try {
                indexOfItem = Integer.parseInt(num);
                if (indexOfItem < 0 || indexOfItem >= auction.getItems().size()) throw new NumberFormatException();
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "Invalid item number");
                return;
            }

            if (JOptionPane.showConfirmDialog(this, "Warning! Are you sure you want to remove item# " + indexOfItem
                + " from this Auctiom") == JOptionPane.OK_OPTION) {
                if(auction.canRemoveItem(auction.getItems().get(indexOfItem))){
                    auction.removeItem(auction.getItems().get(indexOfItem));
                    updateItems();
                    JOptionPane.showMessageDialog(this, "Item has been removed successfully");
                }else{
                    JOptionPane.showMessageDialog(this, "Items can only be removed if auction is " + Auction.REMOVE_ITEM_MIN_DAYS_AWAY +
                            "+ days away");
                }
            }

        });

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        add(auctionLabel, c);
        add(Box.createRigidArea(new Dimension(0, 10)), c);
        add(itemsPane, c);
        add(Box.createRigidArea(new Dimension(0, 10)), c);
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        add(homeButton, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        add(addItemButton, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        add(Box.createRigidArea(new Dimension(0, 10)), c);
        add(cancelAuctionButton, c);
        add(Box.createRigidArea(new Dimension(0, 10)), c);

        c.gridwidth = 1;

        c.anchor = GridBagConstraints.LINE_END;
        add(removeItemLabel, c);
        add(removeItemText, c);
        add(removeItemButton,c);
        
        itemsPane.setPreferredSize(new Dimension(400, 250));
    }

   public void updateItems(){
       itemsPanel = new JPanel();
       itemsPane.setViewportView(itemsPanel);
       itemsPanel.setLayout(new GridLayout(auction.getItems().size() + 1, 3, 10, 10));
       itemsPanel.add(new JLabel("<html><u>Id</u></html>"));
       itemsPanel.add(new JLabel("<html><u>Item Name</u></html>"));
       itemsPanel.add(new JLabel("<html><u>Min Bid</u></html>"));
       int id = 0;
       for (Item item : auction.getItems()) {
           itemsPanel.add(new JLabel("" + id));
           itemsPanel.add(new JLabel(item.getName()));
           itemsPanel.add(new JLabel("$" + item.getMinimumBid()));
           id++;
       }
   }
}
