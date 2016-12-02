package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Auction;
import auctioncentral.model.Item;
import auctioncentral.model.ItemCondition;
import auctioncentral.model.ItemSize;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Hunter
 */
public class ContactAddItemView extends AbstractScreen {

    private Auction auction;

    private JLabel itemNameLabel;
    private JTextField itemNameField;
    private JLabel itemConditionLabel;
    private JComboBox<ItemCondition> itemConditionCombo;
    private JLabel itemSizeLabel;
    private JComboBox<ItemSize> itemSizeCombo;
    private JLabel itemMinBidLabel;
    private JSpinner itemMinBidSpinner;
    private JLabel itemDonorNameLabel;
    private JTextField itemDonorNameField;
    private JLabel itemDescriptionLabel;
    private JTextField itemDescriptionField;
    private JLabel itemCommentLabel;
    private JTextField itemCommentField;
    private JButton addItemButton;
    private JButton cancelButton;

    public ContactAddItemView(Auction auction) {
        this.auction = auction;

        itemNameLabel = new JLabel("Name");
        itemNameField = new JTextField(20);
        itemConditionLabel = new JLabel("Condition");
        itemConditionCombo = new JComboBox<>(ItemCondition.values());
        itemSizeLabel = new JLabel("Size");
        itemSizeCombo = new JComboBox<>(ItemSize.values());
        itemMinBidLabel = new JLabel("Minimum Bid");
        itemMinBidSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1_000_000, 1));
        itemDonorNameLabel = new JLabel("Donor name (Optional)");
        itemDonorNameField = new JTextField(40);
        itemDescriptionLabel = new JLabel("Description for bidders (Optional)");
        itemDescriptionField = new JTextField(40);
        itemCommentLabel = new JLabel("Comment for staff (Optional)");
        itemCommentField = new JTextField(40);
        addItemButton = new JButton("Add Item");
        cancelButton = new JButton("Cancel");
        itemSizeCombo.setToolTipText(Stream.of(ItemSize.values()).map(s -> s.name() + ": " + s.getDescription() + ". ").collect(Collectors.joining()));

        Box vertBox = Box.createVerticalBox();
        Box row1 = Box.createHorizontalBox();
        row1.add(itemNameLabel);
        row1.add(Box.createRigidArea(new Dimension(40, 0)));
        row1.add(Box.createGlue());
        row1.add(itemNameField);
        Box row2 = Box.createHorizontalBox();
        row2.add(itemConditionLabel);
        row2.add(Box.createGlue());
        row2.add(itemConditionCombo);
        Box row3 = Box.createHorizontalBox();
        row3.add(itemSizeLabel);
        row3.add(Box.createGlue());
        row3.add(itemSizeCombo);
        Box row4 = Box.createHorizontalBox();
        row4.add(itemMinBidLabel);
        row4.add(Box.createGlue());
        row4.add(itemMinBidSpinner);
        vertBox.add(row1);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        vertBox.add(row2);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        vertBox.add(row3);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        vertBox.add(row4);
        vertBox.add(Box.createRigidArea(new Dimension(0, 20)));
        vertBox.add(itemDonorNameLabel);
        vertBox.add(itemDonorNameField);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        vertBox.add(itemDescriptionLabel);
        vertBox.add(itemDescriptionField);
        vertBox.add(Box.createRigidArea(new Dimension(0, 5)));
        vertBox.add(itemCommentLabel);
        vertBox.add(itemCommentField);
        Box buttonRow = Box.createHorizontalBox();
        buttonRow.add(Box.createRigidArea(new Dimension(60, 0)));
        buttonRow.add(cancelButton);
        buttonRow.add(Box.createGlue());
        buttonRow.add(addItemButton);
        buttonRow.add(Box.createRigidArea(new Dimension(60, 0)));
        vertBox.add(Box.createRigidArea(new Dimension(0, 20)));
        vertBox.add(buttonRow);

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        add(vertBox);

        addItemButton.addActionListener(e -> {
            if (itemNameField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Item name required");
            } else {
                String donorName = itemDonorNameField.getText().trim().equals("") ? null : itemDonorNameField.getText();
                String description = itemDescriptionField.getText().trim().equals("") ? null : itemDescriptionField.getText();
                String comment = itemCommentField.getText().trim().equals("") ? null : itemCommentField.getText();
                auction.addItem(new Item(
                        itemNameField.getText(),
                        (ItemCondition) itemConditionCombo.getSelectedItem(),
                        (ItemSize) itemSizeCombo.getSelectedItem(),
                        (int) itemMinBidSpinner.getValue(),
                        donorName,
                        description,
                        comment)
                );
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
