package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Auction;
import auctioncentral.model.ItemCondition;
import auctioncentral.model.ItemSize;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class ContactAddItemView extends AbstractScreen {

    private Auction auction;

    private JTextField itemNameField;
    private JComboBox<ItemCondition> itemConditionCombo;
    private JComboBox<ItemSize> itemSizeCombo;
    private JSpinner itemMinBidSpinner;
    private JTextField itemDonorNameField;
    private JTextField itemDescriptionField;
    private JTextField itemCommentField;
    private JButton addItemButton;
    private JButton cancelButton;

    public ContactAddItemView(Auction auction) {
        this.auction = auction;

        itemNameField = new JTextField(20);
        itemConditionCombo = new JComboBox<>(ItemCondition.values());
        itemSizeCombo = new JComboBox<>(ItemSize.values());
        itemMinBidSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1_000_000, 1));
        itemDonorNameField = new JTextField(40);
        itemDescriptionField = new JTextField(40);
        itemCommentField = new JTextField(40);
        addItemButton = new JButton("Add Item");
        cancelButton = new JButton("Cancel");

        add(itemNameField);
        add(itemConditionCombo);
        add(itemSizeCombo);
        add(itemMinBidSpinner);
        add(itemDonorNameField);
        add(itemDescriptionField);
        add(itemCommentField);
        add(addItemButton);
        add(cancelButton);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
