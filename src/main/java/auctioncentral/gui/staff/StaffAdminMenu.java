package auctioncentral.gui.staff;
/**
 * @author Jon
 */
import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.Window;
import auctioncentral.model.Calendar;
import auctioncentral.model.LoginManager;
import auctioncentral.model.Staff;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
public class StaffAdminMenu extends AbstractScreen {
    private JButton addMaxAuctions;
    private JButton returnToHome;
    private JTextField maxAuctions;
    private JLabel EnterMax;
    private JLabel currentMaxAuctions;
    private JLabel StaffName;
    private JLabel DateC;
    private static DateTimeFormatter date = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private int numOfMaxAuctions;
    private int currentNumMaxAuctions;
    private int currentAuctions;



    public StaffAdminMenu(Window w) {
        super(w);
        updateAuctions();
//        StaffName = new JLabel(((Staff) LoginManager.inst().getCurrentUser()).getName());
        currentMaxAuctions = new JLabel("<html> Current Auctions: " + currentAuctions + "<br>Current Max Auctions allowed: " + currentNumMaxAuctions);
//        DateC = new JLabel(date.format(LocalDateTime.now()));
        EnterMax = new JLabel("Enter max auctions: ");
        maxAuctions = new JTextField(3);
        addMaxAuctions = new JButton("Change Max Auctions");
        returnToHome = new JButton("Return Home");

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
//        gbl.setConstraints(StaffName, c);
        c.gridy = 1;
//        gbl.setConstraints(currentMaxAuctions,c);
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 1.0;
//        gbl.setConstraints(DateC,c);

        c.weighty = 0.0;
        c.weightx = 0.0;
//        c.anchor = GridBagConstraints.CENTER;
//        c.gridx = 1;
//        c.gridy = 3;
//        gbl.setConstraints(EnterMax,c);
//        c.gridx = 2;
//        gbl.setConstraints(maxAuctions,c);
//        c.gridx = 3;
//        gbl.setConstraints(addMaxAuctions,c);

        c.weighty = 0.0;
        c.weightx = 0.0;
        c.gridx = 3;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LAST_LINE_END;
//        gbl.setConstraints(returnToHome,c);

        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(currentMaxAuctions, c);
//        add(StaffName);
//        add(DateC);
        add(Box.createRigidArea(new Dimension(0, 60)), c);
        add(EnterMax,c);
        add(maxAuctions,c);
        add(Box.createRigidArea(new Dimension(0, 60)), c);
        add(addMaxAuctions,c);
        add(Box.createRigidArea(new Dimension(0, 5)), c);
        add(returnToHome,c);


        addMaxAuctions.addActionListener(e -> {
            String num = maxAuctions.getText();
            try {
                numOfMaxAuctions = Integer.parseInt(num);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "Input must be a number");
                return;
            }
            if (numOfMaxAuctions < currentAuctions) {
                JOptionPane.showMessageDialog(this, "<html>Maximum Auctions is less than current auctions. <br>Clients will be unable to add new auctions until current auctions are completed.<html>");
            }
            if (numOfMaxAuctions < 0) {
                JOptionPane.showMessageDialog(this, "<html>Maximum Auctions has been set to less than 0. <br>Changed to 0 maximum Auctions.<html>");
            } else {
                JOptionPane.showMessageDialog(this, "Successfully changed to " + numOfMaxAuctions + " max amount of auctions.");
            }
            Calendar.inst().changeMaxAuctions(numOfMaxAuctions);
            updateAuctions();
            currentMaxAuctions.setText("<html>Current Auctions: " + currentAuctions + "<br>Current Max Auctions allowed:<html> " + currentNumMaxAuctions);

        });

        returnToHome.addActionListener(e -> {
                getRoot().addScreen(new StaffHomeView(getRoot()));
        });


    }
    public void updateAuctions(){
        currentNumMaxAuctions = Calendar.inst().getMaxAuctions();
        currentAuctions = Calendar.inst().getAuctionsPastDate(new Date()).size();
    }
}
