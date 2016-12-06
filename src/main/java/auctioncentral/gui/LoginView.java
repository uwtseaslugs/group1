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
 * @author Hunter
 */
public class LoginView extends AbstractScreen {

    private JLabel enterUsernamelabel;
    private JTextField usernameField;
    private JButton loginButton, registerButton;

    public LoginView(Window w) {
        super(w);
        enterUsernamelabel = new JLabel("Enter username:");
        usernameField = new JTextField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(enterUsernamelabel, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(usernameField, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(loginButton, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(registerButton, c);

        loginButton.addActionListener(a -> {
            User user = LoginManager.inst().getUser(usernameField.getText());
            if (user != null) {
                LoginManager.inst().setCurrentUser(user);
                if (user instanceof Staff) {
                    getRoot().addScreen(new StaffHomeView(w));
                } else if (user instanceof Contact) {
                    getRoot().addScreen(new ContactHomeView(w));
                } else if (user instanceof Bidder) {
                    getRoot().addScreen(new BidderHomeView(w));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username");
            }
        });

        registerButton.addActionListener(a -> getRoot().addScreen(new RegisterView(getRoot())));
    }
}
