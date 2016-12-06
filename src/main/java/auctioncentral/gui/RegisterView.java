package auctioncentral.gui;

import auctioncentral.gui.bidder.BidderHomeView;
import auctioncentral.gui.contact.ContactHomeView;
import auctioncentral.gui.staff.StaffHomeView;
import auctioncentral.model.*;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.*;
import javax.swing.*;

/**
 * @author Viveret
 */
public class RegisterView extends AbstractScreen {

    private JLabel enterUsernamelabel, nameLbl, typeLbl;
    private JTextField usernameField, nameField;
    private JButton submitBtn, backButton;

    public RegisterView(Window w) {
        super(w);
        enterUsernamelabel = new JLabel("Enter username:");
        nameLbl = new JLabel("Enter name:");
        typeLbl = new JLabel("Choose type:");
        usernameField = new JTextField(20);
        nameField = new JTextField(20);

        JRadioButton staffButton = new JRadioButton("Staff");
        JRadioButton contactButton = new JRadioButton("Contact");
        JRadioButton bidderButton = new JRadioButton("Bidder");
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(staffButton);
        group.add(contactButton);
        group.add(bidderButton);
        
        submitBtn = new JButton("Submit");
        backButton = new JButton("Back");

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(enterUsernamelabel, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(usernameField, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(nameLbl, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(nameField, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(typeLbl, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(staffButton, c);
        add(contactButton, c);
        add(bidderButton, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(submitBtn, c);

        submitBtn.addActionListener(a -> {
                String name, username;
                name = nameField.getText();
                username = usernameField.getText();
                if (staffButton.isSelected()) {
                    LoginManager.inst().setCurrentUser(new Staff(username, name));
                    getRoot().popScreen();
                    getRoot().addScreen(new StaffHomeView(w));
                } else if (contactButton.isSelected()) {
                    // new ContactNonprofitNameMenu(username, name).show();
                    getRoot().popScreen();
                    getRoot().addScreen(new ContactHomeView(w));
                } else if (bidderButton.isSelected()) {
                    LoginManager.inst().setCurrentUser(new Bidder(username, name));
                    getRoot().popScreen();
                    getRoot().addScreen(new BidderHomeView(w));
                } else {
                    JOptionPane.showMessageDialog(this, "Must select type");
                }
        });

        backButton.addActionListener(a -> getRoot().popScreen());
    }
}
