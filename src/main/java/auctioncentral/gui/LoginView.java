package auctioncentral.gui;

import java.awt.*;
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

        button.addActionListener(a -> System.out.println(textField.getText()));
    }

    @Override
    public void update(Observable o, Object p) {
        
    }
}
