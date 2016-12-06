package auctioncentral.gui.bidder;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Calendar;
import auctioncentral.model.LoginManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Office on 12/3/2016.
 */
public abstract class BidderView extends AbstractScreen{
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private JButton exitButton;
    private JLabel userLabel, user, dateLabel, auctionLabel, auctionNumberLabel;
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    public void setDisplay(){
        user = new JLabel((LoginManager.inst().getCurrentUser()).getName());
        userLabel = new JLabel("Currently logged in as a Bidder");
        dateLabel = new JLabel(dateTimeFormatter.format(LocalDateTime.now()));
        auctionLabel = new JLabel("Total Number of Upcoming Auctions: ");
        auctionNumberLabel =  new JLabel(Calendar.inst().getAuctionsPastDate(new Date()).size() + "");
        exitButton = new JButton("Exit Auction Central");

        setLayout(gbl);
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        setGrid(0,0);
        gbl.setConstraints(user, c);
        setGrid(0,1);
        gbl.setConstraints(userLabel, c);
        setGrid(1,0);
        gbl.setConstraints(dateLabel, c);
        setGrid(1,1);
        gbl.setConstraints(auctionLabel, c);
        setGrid(1,2);
//        setWeight(0.5,0.5);
        gbl.setConstraints(auctionNumberLabel, c);
//        setWeight(0.0,0.0);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        setGrid(5,4);
        gbl.setConstraints(exitButton, c);

        add(user);
        add(userLabel);
        add(dateLabel);
        add(auctionNumberLabel);
        add(auctionLabel);
        add(exitButton);

        exitButton.addActionListener(a -> System.exit(0));

    }

    public void setGrid(int y, int x){
        c.gridy = y;
        c.gridx = x;
    }

    public void setWeight(double yWeight,double xWeight){
        c.weighty = yWeight;
        c.weightx = xWeight;
    }

}
