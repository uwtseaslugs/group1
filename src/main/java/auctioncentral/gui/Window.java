package auctioncentral.gui;

import java.util.*;
import javax.swing.*;
import java.awt.CardLayout;

public class Window extends JFrame {
    private Deque<AbstractScreen> myScreenHistory;
    
    public Window() {
        super("Auction Central");
        myScreenHistory = new LinkedList<AbstractScreen>();
        initView();
    }

    private void initView() {
    }

    public void popScreen() {
        myScreenHistory.pop();
        switchScreen(myScreenHistory.peek());
    }

    public void addScreen(AbstractScreen screen) {
        myScreenHistory.push(screen);
        switchScreen(screen);
    }

    private void switchScreen(AbstractScreen screen) {
        setContentPane(screen);
    }
}
