package auctioncentral.gui;

import auctioncentral.model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class LoginView extends AbstractScreen {

    private JLabel label;
    private JTextField textField;
    private JButton button;

    public LoginView() {
        label = new JLabel("Enter username:");
        textField = new JTextField(20);
        button = new JButton("Login");

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        gbl.setConstraints(label, c);
        gbl.setConstraints(textField, c);
        gbl.setConstraints(button, c);
        add(label);
        add(textField);
        add(button);

        button.addActionListener(a -> {
            User user = LoginManager.getInstance().getUser(textField.getText());
            if (user != null) {
                LoginManager.getInstance().setCurrentUser(user);
                if (user instanceof Staff) {

                } else if (user instanceof Contact) {

                } else if (user instanceof Bidder) {

                }
            } else {

            }
        });
    }

    @Override
    public void update(Observable o, Object p) {
        
    }
}
