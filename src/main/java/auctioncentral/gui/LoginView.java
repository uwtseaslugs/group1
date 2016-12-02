package auctioncentral.gui;

import auctioncentral.model.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class LoginView extends AbstractScreen {

    private JLabel enterUsernamelabel;
    private JTextField usernameField;
    private JButton loginButton;

    public LoginView() {
        enterUsernamelabel = new JLabel("Enter username:");
        usernameField = new JTextField(20);
        loginButton = new JButton("Login");

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(enterUsernamelabel, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(usernameField, c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(loginButton, c);

        loginButton.addActionListener(a -> {
            User user = LoginManager.getInstance().getUser(usernameField.getText());
            if (user != null) {
                LoginManager.getInstance().setCurrentUser(user);
                if (user instanceof Staff) {

                } else if (user instanceof Contact) {

                } else if (user instanceof Bidder) {

                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username");
            }
        });
    }

    @Override
    public void update(Observable o, Object p) {
        
    }
}