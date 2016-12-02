package auctioncentral.gui;

import auctioncentral.gui.bidder.BidderHomeView;
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
        gbl.setConstraints(enterUsernamelabel, c);
        gbl.setConstraints(usernameField, c);
        gbl.setConstraints(loginButton, c);
        add(enterUsernamelabel);
        add(usernameField);
        add(loginButton);

        loginButton.addActionListener(a -> {
            User user = LoginManager.getInstance().getUser(usernameField.getText());
            if (user != null) {
                LoginManager.getInstance().setCurrentUser(user);
                if (user instanceof Staff) {

                } else if (user instanceof Contact) {

                } else if (user instanceof Bidder) {
                    Window frame = (Window) SwingUtilities.getRoot(this);
                    frame.addScreen(new BidderHomeView());
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
